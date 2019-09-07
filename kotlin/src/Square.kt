import java.util.ArrayList

class SquareRoot constructor(private val Num: Int, private val Precision: Double = 0.00000001) {

    private fun calculateEstimation(): Double {
        var copyForCountDigits = Num
        var countDigits = 0

        val divisionDigits = ArrayList<Int>()

        while (copyForCountDigits > 0) {
            divisionDigits.add(0, copyForCountDigits)

            copyForCountDigits /= 10
            countDigits++
        }

        if (divisionDigits.size == 0) return 0.0

        var rootBase = divisionDigits[0]
        var exponent = countDigits - 1

        if (countDigits % 2 == 0) {
            rootBase = divisionDigits[1]
            exponent = countDigits - 2
        }

        return if (rootBase < 10) 2 * Math.pow(10.0, (exponent / 2).toDouble()) else 6 * Math.pow(
            10.0,
            (exponent / 2).toDouble()
        )
    }

    fun sqrt(): Double {
        if (Num == 0) return 0.0

        var currentPrecision = 1.0
        var estimation = calculateEstimation()

        while (currentPrecision > Precision) {
            estimation = (Num / estimation + estimation) / 2
            currentPrecision = Math.abs(estimation * estimation - Num)
        }

        return estimation
    }
}

fun main(args: Array<String>) {
    val N = 5000000
    val results = DoubleArray(N)

    val start = System.nanoTime()
    for (i in 0 until N) {
        results[i] = SquareRoot(i).sqrt()
    }
    val finish = System.nanoTime()

    println("Results[4250250] = " + results[4250250])
    println("Time elapsed: " + (finish - start) / 1000000000.0)
}


