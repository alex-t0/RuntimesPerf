using System;
using System.Collections.Generic;

namespace myApp
{
    class Program
    {
		public class SquareRoot
		{
			private int Num;
			private double Precision;

			public SquareRoot(int number, double precision = 0.00000001)
			{
				Num = number;
				Precision = precision;
			}

			private double CalculateEstimation()
			{
				int copyForCountDigits = Num;
				int countDigits = 0;

				IList<int> divisionDigits = new List<int>();

				while (copyForCountDigits > 0)
				{
					divisionDigits.Insert(0, copyForCountDigits);

					copyForCountDigits /= 10;
					countDigits++;
				}

				if (divisionDigits.Count == 0) return 0;

				int rootBase = divisionDigits[0];
				int exponent = countDigits - 1;

				if (countDigits % 2 == 0)
				{
					rootBase = divisionDigits[1];
					exponent = countDigits - 2;
				}

				return rootBase < 10 ? 2 * Math.Pow(10, exponent / 2) : 6 * Math.Pow(10, exponent / 2);
			}

			public double Sqrt(){
				if (Num == 0) return 0;

				double currentPrecision = 1;
				double estimation = CalculateEstimation();

				while (currentPrecision > Precision)
				{
					estimation = (Num/estimation + estimation)/2;
					currentPrecision = Math.Abs(estimation * estimation - Num);
				}

				return estimation;
			}
		}

        static void Main(string[] args)
        {
			int N = 5000000;
			double[] results = new double[N];

			var start = DateTime.Now;
			for (int i = 0; i < N; i++)
			{
				results[i] = new SquareRoot(i).Sqrt();
			}
			var end = DateTime.Now;

            Console.WriteLine("results[4250250] = " + results[4250250]);
			Console.WriteLine("Total elapsed " + (end - start).TotalSeconds);
        }
    }
}
