# Benchmark

This is small runtimes test. It contain evaluation of square roots in 2 steps:
* rough estimation
* evaluation (5000000 times in a loop)

See more: https://en.wikipedia.org/wiki/Methods_of_computing_square_roots

# Results

| Runtime       | Time, seconds   | Implementation                |
| ------------- |:---------------:|--------------------------------
| CUDA          | 0.005718        | NVidia CUDA Toolkit V13.0.48  |
| Clang++       | 0.2216          | 10.0.0-4ubuntu1               |
| GCC C++       | 0.2288          | Ubuntu 9.3.0-17ubuntu1~20.04  |
| GO            | 0.2870          | go1.20.1 linux/amd64          |
| Clang++       | 0.3415          | 3.8.0-2ubuntu4                |
| GCC C++       | 0.3513          | 5.4.0 20160609                |
| KotlinJVM     | 0.820           | Openjdk 17.0.1, Kotlin 1.6.10 |
| KotlinJVM     | 0.834           | Openjdk 12.0.2, Kotlin 1.3.50 |
| Java          | 0.8116          | Openjdk 17.0.1                |
| Java          | 0.848           | Openjdk 12.0.2                |
| C#            | 0.9             | .Net Core 6.0.101             |
| C#            | 0.968           | .Net Core 2.1.801             |
| PyPy Python   | 1.1483          | PyPy3.5 5.10.0 x86_64         |
| Typescript    | 1.676           | Nodejs 10.16.1, tsc 3.5.3     |
| PHP           | 4.2             | 8.1.2                         |
| PHP           | 4.9700          | 7.2.0                         |
| Python        | 10.2644 (WTF!)  | 3.8.10                        |
| Python        | 11.788 (WTF!)   | 3.6.8                         |
| 1cv8.3        | 78 (WTF!^2)     | 8.3.27                        |
| oscript       | 128 (WTF!^3)    | 1.9.3.15                      |
