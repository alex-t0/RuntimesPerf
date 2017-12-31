#!/bin/sh

clang++ -O3 -Wall -std=c++14 ./main.cpp -o clang_a.out
g++ -O3 -Wall -std=c++14 ./main.cpp
