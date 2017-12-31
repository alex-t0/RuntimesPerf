<?php
class SquareRoot {
	private $Num;
	private $Precision;
	function __construct($num, $precision = 0.00000001) {
		$this->Num = $num;
		$this->Precision = $precision;
	}

    function calculateEstimation() {
		$copyForCountDigits = $this->Num;
		$countDigits = 0;

		$divisionDigits = array();

		while ($copyForCountDigits > 0) {
			array_unshift($divisionDigits, $copyForCountDigits);
			$copyForCountDigits = intdiv($copyForCountDigits, 10);
			$countDigits++;
		}

		if (count($divisionDigits) == 0) return 0;

		$rootBase = $divisionDigits[0];
		$exponent = $countDigits - 1;
		#echo "rootBase before; ".$rootBase."\n";
		if ($countDigits % 2 == 0)
		{
			#echo "rootBase middle; ".$rootBase."\n";
			$rootBase = $divisionDigits[1];
			$exponent = $countDigits - 2;
			#echo "rootBase middle after; ".$rootBase."\n";
		}
		#echo "rootBase after; ".$rootBase."\n";

		return $rootBase < 10 ? 2 * pow(10, $exponent / 2) : 6 * pow(10, $exponent / 2);
    }

	function Sqrt(){
		if ($this->Num == 0) return 0;

		$currentPrecision = 1;
		$estimation = $this->CalculateEstimation();

		while ($currentPrecision > $this->Precision)
		{
			$estimation = ($this->Num/$estimation + $estimation)/2;
			$currentPrecision = abs($estimation * $estimation - $this->Num);
		}

		return $estimation;
	}
}

/*$testObject = new SquareRoot(2);
$result = $testObject->Sqrt();
echo $result."\n";
$testObject = new SquareRoot(100500);
$result = $testObject->Sqrt();
echo $result."\n";
$testObject = new SquareRoot(7901076544);
$result = $testObject->Sqrt();
echo $result."\n";*/

$N = 5000000;
$results = array();
$time_start = microtime(true);
for ($i = 0; $i < $N; $i++)
{
	$testObject = new SquareRoot($i);
	$result = $testObject->Sqrt();

	$results[$i] = $result;
}
$time_end = microtime(true);

echo $results[4250250]."\n";
echo "Total elapsed: ".($time_end - $time_start)."\n";
?>
