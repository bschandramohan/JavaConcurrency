package com.bschandramohan.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TryExecutors {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); // Single Thread executed sequentially
        ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(2); // 2 Threads pool used
        ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool(); // Cached Pool - new threads created as needed, isn't closed on finish

        performOperations(singleThreadExecutor, "SingleThread");

        performOperations(fixedThreadExecutor, "FixedThread");

        performOperations(cachedThreadExecutor, "CachedThread");
    }

    private static void performOperations(ExecutorService executorService, String executorType) throws InterruptedException {
        System.out.printf("\n\nMain Start; ExecutorTypeString=%s ExecutorType=%s; Thread=%s  %n", executorType, executorService.toString(), Thread.currentThread().getName());

        executorService.submit(() -> System.out.printf("SUM_AP=%d %n", MainOperations.getArithmeticProgressionSum(1, 2, 100)));
        executorService.submit(() -> System.out.printf("SUM_GP=%d %n", MainOperations.getGeometricProgressionSum(1, 2, 10000)));
        executorService.submit(() -> System.out.printf("SUM_AP=%d %n", MainOperations.getArithmeticProgressionSum(1, 2, 10)));
        executorService.submit(() -> System.out.printf("SUM_GP=%d %n", MainOperations.getGeometricProgressionSum(1, 2, 1001)));

        executorService.shutdown();
//        tryExecutor.singleThreadExecutor.shutdownNow();
        System.out.printf("Main Finished; ExecutorTypeString=%s ExecutorType=%s; Thread=%s  %n", executorType, executorService.toString(), Thread.currentThread().getName());

        // Required to avoid confusion in reading output with other executors later
        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }
}
