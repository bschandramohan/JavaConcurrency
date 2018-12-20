package com.bschandramohan.learn;

public class MainOperations {
    public static long getArithmeticProgressionSum(int startNumber, int difference, long count) {
        System.out.printf("Starting ArithmeticProgression Sum with parameters startNumber=%d, difference=%d, count=%d; Thread=%s %n", startNumber, difference, count, Thread.currentThread().getName());

        // To Simulate
        int sum = startNumber;
        int previousNumber = startNumber;
        for (long i = 0; i < count - 1; i++) {
            previousNumber += difference;
            sum += previousNumber;
        }

        System.out.printf("End of ArithmeticProgression Sum with parameters startNumber=%d, difference=%d, count=%d; Final Sum=%d %n", startNumber, difference, count, sum);
        return sum;
    }

    public static long getGeometricProgressionSum(int startNumber, int factor, long count) {
        System.out.format("Starting GeometricProgression Sum with parameters startNumber=%d, factor=%d, count=%d; Thread=%s %n", startNumber, factor, count, Thread.currentThread().getName());

        int sum = startNumber;
        int previousNumber = startNumber;
        for (long i = 0; i < count - 1; i++) {
            previousNumber *= factor;
            sum += previousNumber;
        }

        System.out.printf("End of GeometricProgression Sum with parameters startNumber=%d, factor=%d, count=%d; Sum=%d %n", startNumber, factor, count, sum);
        return sum;
    }

    public static void main(String[] args) {
        System.out.printf("Sum of 0,1,2,3...; result=%s %n", getArithmeticProgressionSum(0, 1, 10));
        System.out.printf("Sum of 1,3,5,7...; result=%s %n", getArithmeticProgressionSum(1, 2, 4));
        System.out.printf("Sum of 1,2,4,8,16...; result=%s %n", getGeometricProgressionSum(1, 2, 10));
        System.out.printf("Sum of 1,3,9,27...; result=%s %n", getGeometricProgressionSum(1, 3, 4));
    }
}
