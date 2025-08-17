#include <cuda_runtime.h>
#include <iostream>
#include <chrono>
#include <cmath>

// Error checking macro for CUDA API calls
#define CUDA_CHECK(call) \
    do { \
        cudaError_t err = call; \
        if (err != cudaSuccess) { \
            std::cerr << "CUDA Error: " << cudaGetErrorString(err) << " at line " << __LINE__ << std::endl; \
            exit(EXIT_FAILURE); \
        } \
    } while (0)

// CUDA kernel for square root calculation
__global__ void sqrtKernel(int* input, double* output, int size, double precision) {
    int idx = blockIdx.x * blockDim.x + threadIdx.x;
    if (idx >= size) return;

    int num = input[idx];
    if (num == 0) {
        output[idx] = 0.0;
        return;
    }

    // CalculateEstimation logic
    int copyForCountDigits = num;
    int countDigits = 0;
    int firstTwoDigits[2] = { 0, 0 };

    while (copyForCountDigits > 0) {
        firstTwoDigits[1] = firstTwoDigits[0];
        firstTwoDigits[0] = copyForCountDigits;
        copyForCountDigits /= 10;
        countDigits++;
    }

    double estimation;
    if (firstTwoDigits[0] == 0 && firstTwoDigits[1] == 0) {
        estimation = 0.0;
    }
    else {
        int rootBase = firstTwoDigits[0];
        int exponent = countDigits - 1;

        if (countDigits % 2 == 0) {
            rootBase = firstTwoDigits[1];
            exponent = countDigits - 2;
        }

        estimation = rootBase < 10 ? 2.0 * pow(10.0, exponent / 2) : 6.0 * pow(10.0, exponent / 2);
    }

    // Sqrt logic (Newton-Raphson iteration)
    double currentPrecision = 1.0;
    while (currentPrecision > precision) {
        estimation = (num / estimation + estimation) / 2.0;
        currentPrecision = fabs(estimation * estimation - num);
    }

    output[idx] = estimation;
}

int main() {
    // Parameters
    const int N = 5000000; // Same as original
    const double precision = 0.00000001; // Same as original
    const int blockSize = 512; // CUDA threads per block
    const int gridSize = (N + blockSize - 1) / blockSize; // CUDA blocks

    // Host arrays
    int* h_input = new int[N];
    double* h_output = new double[N];

    // Initialize input array (0 to N-1)
    for (int i = 0; i < N; ++i) {
        h_input[i] = i;
    }

    // Device arrays
    int* d_input;
    double* d_output;
    CUDA_CHECK(cudaMalloc(&d_input, N * sizeof(int)));
    CUDA_CHECK(cudaMalloc(&d_output, N * sizeof(double)));

    // Copy input data to device
    CUDA_CHECK(cudaMemcpy(d_input, h_input, N * sizeof(int), cudaMemcpyHostToDevice));

    // Warm-up run
    sqrtKernel << <gridSize, blockSize >> > (d_input, d_output, N, precision);
    CUDA_CHECK(cudaDeviceSynchronize());

    // Benchmarking
    auto start = std::chrono::high_resolution_clock::now();
    sqrtKernel << <gridSize, blockSize >> > (d_input, d_output, N, precision);
    CUDA_CHECK(cudaDeviceSynchronize());
    auto finish = std::chrono::high_resolution_clock::now();

    std::chrono::duration<double> elapsed = finish - start;

    // Copy results back to host
    CUDA_CHECK(cudaMemcpy(h_output, d_output, N * sizeof(double), cudaMemcpyDeviceToHost));

    // Output results (same as original)
    std::cout.precision(17);
    std::cout << "4250250 item: " << h_output[4250250] << std::endl;
    std::cout << "Elapsed " << elapsed.count() << std::endl;

    // Cleanup
    CUDA_CHECK(cudaFree(d_input));
    CUDA_CHECK(cudaFree(d_output));
    delete[] h_input;
    delete[] h_output;

    return 0;
}
