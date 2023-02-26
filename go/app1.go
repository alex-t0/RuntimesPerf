package main

import (
	"fmt"
	"math"
	"time"
)

type SquareRoot struct {
	Num       int
	Precision float64
}

func (sr *SquareRoot) CalculateEstimation() float64 {
	Number := sr.Num
	countDigits := 0

	digits := make([]int, 0, 7)

	for Number > 0 {
		Number = Number / 10
		digits = append(digits, Number)
		countDigits++
	}

	if countDigits == 0 {
		return 0
	}

	rootBase := digits[len(digits)-1]
	exponent := countDigits - 1

	if countDigits%2 == 0 {
		rootBase = digits[len(digits)-2]
		exponent = countDigits - 2
	}

	if rootBase < 10 {
		return 2 * math.Pow(10, float64(exponent/2))
	}

	return 6 * math.Pow(10, float64(exponent/2))
}

func (sr *SquareRoot) Sqrt() float64 {
	if sr.Num == 0 {
		return 0
	}

	currentPrecision := float64(1)
	estimation := sr.CalculateEstimation()

	for currentPrecision > sr.Precision {
		estimation = (float64(sr.Num)/estimation + estimation) / 2
		currentPrecision = math.Abs(estimation*estimation - float64(sr.Num))
	}

	return estimation
}

func main() {
	N := 5000000
	results := make([]float64, N, N)

	start := time.Now()
	for i := 0; i < N; i++ {
		r := SquareRoot{Num: i, Precision: 0.00000001}
		results[i] = r.Sqrt()
	}

	elapsed := time.Since(start)
	fmt.Println(results[4250250])
	fmt.Println(elapsed)
}
