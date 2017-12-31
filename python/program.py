import time

class SquareRoorCalc:
    def __init__(self, num, precision = 0.00000001):
        self.num = num
        self.precision = precision

    def calculate_estimation(self):
        copy_for_count_digit = self.num
        count_digits = 0
        numbers = []
        while (copy_for_count_digit>0):
            numbers.insert(0, copy_for_count_digit)
            count_digits += 1
            copy_for_count_digit = copy_for_count_digit // 10

        if (len(numbers) == 0):
            return self.num / 2;

        root_base = numbers[0]
        exponent = count_digits - 1
        if (count_digits % 2 == 0):
            root_base = numbers[1]
            exponent = count_digits - 2
        return 2 * (10 ** (exponent/2)) if root_base < 10 else 6 * (10 ** (exponent/2))

    def sqrt(self):
        if (self.num == 0):
            return 0

        current_prec = 1
        estimation = self.calculate_estimation()
        num = self.num

        while (current_prec > self.precision):
            estimation = (num/estimation + estimation)/2
            current_prec = abs(estimation * estimation - num)

        return estimation

# print(SquareRoorCalc(7901076544).sqrt());
start = time.time()
results = []
for i in range(5000000):
    results.append(SquareRoorCalc(i).sqrt())
end = time.time()

print(len(results))
print(results[4250250])
print(end - start)
