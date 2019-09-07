import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.system.measureTimeMillis

class SquareRootNative constructor(private val Num: Int, private val Precision: Double = 0.00000001) {

    private fun calculateEstimation(): Int {
        var copyForCountDigits : Int = Num
        var countDigits = 0

        val divisionDigits = ArrayList<Int>()

        while (copyForCountDigits > 0) {
            divisionDigits.add(0, copyForCountDigits)

            copyForCountDigits /= 10
            countDigits++
        }

        if (divisionDigits.size == 0) return 0

        var rootBase = divisionDigits[0]
        var exponent = countDigits - 1

        if (countDigits % 2 == 0) {
            rootBase = divisionDigits[1]
            exponent = countDigits - 2
        }

        return (if (rootBase < 10) 2 * 10.0.pow(exponent / 2) else 6 * 10.0.pow(exponent / 2)).toInt()
    }

    fun sqrt(): Double {
        if (Num == 0) return 0.0

        var NumDouble : Double = Num.toDouble()

        var currentPrecision = 1.0
        var estimation : Double = calculateEstimation().toDouble()

        while (currentPrecision > Precision) {
            estimation = (NumDouble / estimation + estimation) / 2
            currentPrecision = (estimation * estimation - NumDouble).absoluteValue
        }

        return estimation
    }
}

fun main(args: Array<String>) {
    val N = 5000000
    val results = DoubleArray(N)

    val elapsed = measureTimeMillis {
        for (i in 0 until N) {
            results[i] = SquareRootNative(i).sqrt()
        }
    }

    println("Results[4250250] = " + results[4250250])
    println("Time elapsed: " + elapsed)
}


