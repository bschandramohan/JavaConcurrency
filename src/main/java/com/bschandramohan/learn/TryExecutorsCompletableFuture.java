package com.bschandramohan.learn;

import java.util.concurrent.*;

public class TryExecutorsCompletableFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // For Single Thread executor reference, check TryExecutors.java
        performOperations(Executors.newFixedThreadPool(2));
    }

    private static void performOperations(ExecutorService executorService) throws InterruptedException, ExecutionException {
        System.out.printf("\n\nMain Start; ExecutorType=%s; Thread=%s %n",executorService.toString(), Thread.currentThread().getName());

        Future<Long> sum1 = executorService.submit(() -> MainOperations.getArithmeticProgressionSum(1, 2, 100));
        Future<Long> product1 = executorService.submit(() -> MainOperations.getGeometricProgressionSum(1, 2, 10000));
        Future<Long> sum2 = executorService.submit(() -> MainOperations.getArithmeticProgressionSum(1, 2, 10));
        Future<Long> product2 = executorService.submit(() -> MainOperations.getGeometricProgressionSum(1, 2, 1001));

        executorService.shutdown();

        // Display the futures - Executor Service might not have finished
        System.out.printf("Sum1=%s; isDone()=%b %n", sum1, sum1.isDone());
        System.out.printf("Product1=%s; isDone()=%b %n", product1, product1.isDone());
        System.out.printf("Sum2=%s; isDone()=%b %n", sum2, sum2.isDone());
        System.out.printf("Product2=%s; isDone()=%b %n", product2, product2.isDone());

        // Cancel product2 computation
        System.out.printf("%nCancelling Product 2 computation; Status=%b %n", product2.cancel(true));

        System.out.printf("%nShutdown=%b Terminated=%b %n", executorService.isShutdown(), executorService.isTerminated());
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.printf("Shutdown=%b Terminated=%b %n%n", executorService.isShutdown(), executorService.isTerminated());

//        tryExecutor.singleThreadExecutor.shutdownNow();

        // Display the futures - Executor Service will have finished as we are are awaiting termination of the executorService.
        System.out.printf("Sum1=%s; isDone()=%b %n", sum1.get(), sum1.isDone());
        System.out.printf("Product1=%s; isDone()=%b %n", product1.get(), product1.isDone());
        System.out.printf("Sum2=%s; isDone()=%b; isCancelled()=%b; %n", sum2.get(), sum2.isDone(), sum2.isCancelled());
        if (!product2.isCancelled()) {
            System.out.printf("Product2=%s; isDone()=%b; isCancelled()=%b; %n", product2.get(), product2.isDone(), product2.isCancelled());
        }

        System.out.println(String.format("Main Finished; ExecutorType=%s; Thread=%s",executorService.toString(), Thread.currentThread().getName()));
    }
}
