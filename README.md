# Benchmark

This is small runtimes test. It contain evaluation of square roots in 2 steps:
* rough estimation
* evaluation (5000000 times in a loop)

See more: https://en.wikipedia.org/wiki/Methods_of_computing_square_roots

# Results

| Runtime       | Time, seconds   | Implementation                |
| ------------- |:---------------:|--------------------------------
| Clang++       | 0.2216          | 10.0.0-4ubuntu1               |
| Clang++       | 0.3415          | 3.8.0-2ubuntu4                |
| GCC C++       | 0.2288          | Ubuntu 9.3.0-17ubuntu1~20.04  |
| GCC C++       | 0.3513          | 5.4.0 20160609                |
| KotlinJVM     | 0.834           | Openjdk 12.0.2, Kotlin 1.3.50 |
| Java          | 0.848           | Openjdk 12.0.2                |
| C#            | 0.968           | .Net Core 2.1.801             |
| PyPy Python   | 1.1483          | PyPy3.5 5.10.0 x86_64         |
| Typescript    | 1.676           | Nodejs 10.16.1, tsc 3.5.3     |
| PHP           | 4.9700          | 7.2.0                         |
| Python        | 11.788 (WTF!)   | 3.6.8                         |
