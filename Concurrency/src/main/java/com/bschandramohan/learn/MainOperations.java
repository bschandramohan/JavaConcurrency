package com.bschandramohan.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainOperations {
    public static long getArithmeticProgressionSum(int startNumber, int difference, long count) {
        System.out.printf("%nStarting ArithmeticProgression Sum with parameters startNumber=%d, difference=%d, count=%d; Thread=%s %n", startNumber, difference, count, Thread.currentThread().getName());

        // To Simulate
        int sum = startNumber;
        int previousNumber = startNumber;
        for (long i = 0; i < count - 1; i++) {
            previousNumber += difference;
            sum += previousNumber;
        }

        System.out.printf("End of ArithmeticProgression Sum with parameters startNumber=%d, difference=%d, count=%d; SUM_AP=%d %n", startNumber, difference, count, sum);
        return sum;
    }

    public static long getGeometricProgressionSum(int startNumber, int factor, long count) {
        System.out.printf("%nStarting GeometricProgression Sum with parameters startNumber=%d, factor=%d, count=%d; Thread=%s %n", startNumber, factor, count, Thread.currentThread().getName());

        int sum = startNumber;
        int previousNumber = startNumber;
        for (long i = 0; i < count - 1; i++) {
            previousNumber *= factor;
            sum += previousNumber;
        }

        System.out.printf("End of GeometricProgression Sum with parameters startNumber=%d, factor=%d, count=%d; SUM_GP=%d %n", startNumber, factor, count, sum);
        return sum;
    }

    public static void main(String[] args) {
        System.out.printf("Sum of 0,1,2,3...; result=%s%n", getArithmeticProgressionSum(0, 1, 10));
        System.out.printf("Sum of 1,3,5,7...; result=%s%n", getArithmeticProgressionSum(1, 2, 4));
        System.out.printf("Sum of 1,2,4,8,...; result=%s%n", getGeometricProgressionSum(1, 2, 10));
        System.out.printf("Sum of 1,3,9,27...; result=%s%n", getGeometricProgressionSum(1, 3, 4));

        // Just another way - using Streams
        List<Integer> counts = new ArrayList<>(Arrays.asList(10, 100, 20, 101));
        counts.parallelStream().forEach(value -> System.out.printf("Sum of 1,3,5,7...; result=%d %n", getArithmeticProgressionSum(1, 2, value)));
    }
}
