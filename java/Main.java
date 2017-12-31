import java.util.List;
import java.util.ArrayList;

class Main {
	public class SquareRoot {
		private int Num;
		private double Precision;

		public SquareRoot(int number, double precision){
			Num = number;
			Precision = precision;
		}

		public SquareRoot(int number){
			this(number, 0.00000001);
		}

		private double calculateEstimation(){
			int copyForCountDigits = Num;
			int countDigits = 0;

			List<Integer> divisionDigits = new ArrayList<Integer>();

			while (copyForCountDigits > 0)
			{
				divisionDigits.add(0, copyForCountDigits);

				copyForCountDigits /= 10;
				countDigits++;
			}

			if (divisionDigits.size() == 0) return 0;

			int rootBase = divisionDigits.get(0);
			int exponent = countDigits - 1;

			if (countDigits % 2 == 0)
			{
				rootBase = divisionDigits.get(1);
				exponent = countDigits - 2;
			}

			return rootBase < 10 ? 2 * Math.pow(10, exponent / 2) : 6 * Math.pow(10, exponent / 2);
		}

		public double sqrt(){
			if (Num == 0) return 0;

			double currentPrecision = 1;
			double estimation = calculateEstimation();

			while (currentPrecision > Precision)
			{
				estimation = (Num/estimation + estimation)/2;
				currentPrecision = Math.abs(estimation * estimation - Num);
			}

			return estimation;
		}
	}

    public static void main(String args[]){
		// System.out.println(new Main().new SquareRoot(2).sqrt());
		Main mainStub = new Main();
		// System.out.println(mainStub.new SquareRoot(2).sqrt());
		// System.out.println(mainStub.new SquareRoot(5).sqrt());
		// System.out.println(mainStub.new SquareRoot(81).sqrt());

		int N = 5000000;
		double[] results = new double[N];

		long start = System.nanoTime();
		for (int i = 0; i < N; i++)
	    {
	        results[i] = mainStub.new SquareRoot(i).sqrt();
	    }
		long finish = System.nanoTime();

		System.out.println("Results[4250250] = " + results[4250250]);
		System.out.println("Time elapsed: " + (finish - start)/1000000000.0);
    }
}
