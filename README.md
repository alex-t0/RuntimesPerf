# Benchmark

This is small runtimes test. It contain evaluation of square roots in 2 steps:
* rough estimation
* evaluation (5000000 times in a loop)

See more: https://en.wikipedia.org/wiki/Methods_of_computing_square_roots

# Results

| Runtime       | Time, seconds   | Implementation            |
| ------------- |:---------------:|----------------------------
| Clang++       | 0.3415          | 3.8.0-2ubuntu4            |
| GCC C++       | 0.3513          | 5.4.0 20160609            |
| Java          | 0.845           | Openjdk 11.0.4            |
| PyPy Python   | 1.1483          | PyPy3.5 5.10.0 x86_64     |
| C#            | 1.3144          | .Net Core 2.0.0           |
| Typescript    | 1.676           | Nodejs 10.16.1, tsc 3.5.3 |
| PHP           | 4.9700          | 7.2.0                     |
| Python        | 11.788 (WTF!)   | 3.6.8                     |
