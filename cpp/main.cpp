#include <stdio.h>
#include <cmath>
#include <iostream>
#include <chrono>

class SquareRoot
{
private:
	int num;
	double precision;
	int firstTwoDigits[2] = {0, 0};
	double CalculateEstimation()
	{
		int copyForCountDigits = this->num;
		int countDigits = 0;

		// std::cout << "estimation started";

		while (copyForCountDigits > 0)
		{
  			this->firstTwoDigits[1] = this->firstTwoDigits[0];
			this->firstTwoDigits[0] = copyForCountDigits;

			copyForCountDigits /= 10;
			countDigits++;
		}

		if (this->firstTwoDigits[0] == 0 && this->firstTwoDigits[1] == 0)
			return 0;

		int rootBase = firstTwoDigits[0];
		int exponent = countDigits - 1;

		if (countDigits % 2 == 0)
		{
			rootBase = firstTwoDigits[1];
			exponent = countDigits - 2;
		}

		// std::cout << "estimation completed";

		return rootBase < 10 ? 2 * pow(10, exponent / 2) : 6 * pow(10, exponent / 2);
	};
public:
	SquareRoot(int number, double precision)
	{
		this->num = number;
		this->precision = precision;
	};

	SquareRoot(int number) : SquareRoot(number, 0.00000001)
	{
	};

	double Sqrt()
	{
		if (this->num == 0) return 0;

		// std::cout << "begin calculating";

		double currentPrecision = 1;
		double estimation = this->CalculateEstimation();

		while (currentPrecision > this->precision)
		{
			estimation = (this->num/estimation + estimation)/2;
            currentPrecision = std::abs(estimation * estimation - this->num);
		}

		return estimation;
	}
};

int main()
{
	// auto sq = SquareRoot(5);
	int N = 5000000;
    double* results = new double[N];

    auto start = std::chrono::high_resolution_clock::now();
	for (int i = 0; i < N; i++)
    {
        results[i] = SquareRoot(i).Sqrt();
    }
    auto finish = std::chrono::high_resolution_clock::now();

    std::chrono::duration<double> elapsed = finish - start;

    std::cout.precision(17);
    std::cout << "4250250 item: " << results[4250250] << std::endl;
    std::cout << "Elapsed " << elapsed.count() << std::endl;

	delete[] results;

	return 0;
}
