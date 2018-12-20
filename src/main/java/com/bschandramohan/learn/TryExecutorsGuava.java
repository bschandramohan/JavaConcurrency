package com.bschandramohan.learn;

import com.bschandramohan.learn.util.Utils;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.*;

public class TryExecutorsGuava {
    Executor directExecutor = MoreExecutors.directExecutor();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

    // Runs in main thread
    private void runDirectExecutor() {
        directExecutor.execute(() -> MainOperations.getArithmeticProgressionSum(1, 2, 1));
    }

    private void runExitingExecutorService() {
        // Exiting Executor Service
        MoreExecutors.getExitingExecutorService((ThreadPoolExecutor) executorService, 10, TimeUnit.SECONDS);
        executorService.submit(() -> {
            while (true) {
            }
        });
    }

    private void runListenableFutureWithGet() throws ExecutionException, InterruptedException {
        //Listenable Future
        ListenableFuture listenableFuture = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 2, 10));

        //Listenable Future - GET
        System.out.printf("Done=%b Cancelled=%b %n", listenableFuture.isDone(), listenableFuture.isCancelled());
        System.out.printf("AP Sum result=%s %n", listenableFuture.get()); // Blocking call
        System.out.printf("Done=%b Cancelled=%b %n", listenableFuture.isDone(), listenableFuture.isCancelled());
    }

    private void runListenableFutureWithListener() {
        ListenableFuture listenableFuture = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 3, 10));

        // Listenable Future - AddListener to get callback on completion
        listenableFuture.addListener(() -> {
            try {
                System.out.printf("Listener invoked implying future is available; Result=%s %n", listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, directExecutor);
    }

    private void runListenableFutureWithFutureCallback() {
        ListenableFuture listenableFuture = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 4, 10));

        Futures.addCallback(listenableFuture, new FutureCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                System.out.printf("FutureCallback success invoked implying future is available; Result=%d %n", result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.printf("FutureCallback failure invoked implying exception occurred; Result=%s %n", t.getMessage());
            }
        }, directExecutor);
    }

    private void runListenableFutureWithFutureAllAsList() throws ExecutionException, InterruptedException {
        ListenableFuture listenableFuture1 = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 2, 100000000));

        ListenableFuture listenableFuture2 = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 3, 10000000));

        ListenableFuture listenableFuture3 = listeningExecutorService.submit(() ->
                MainOperations.getArithmeticProgressionSum(1, 4, 1000000000));

        Utils.logMessage("Before Futures.allAsList");
        ListenableFuture<List<Long>> listenableFutures = Futures.allAsList(listenableFuture1, listenableFuture2, listenableFuture3);
        Utils.logMessage("Before Futures.get");
        List<Long> results = listenableFutures.get(); // blocking call
        Utils.logMessage("After Futures.get");
        for(int i = 0; i < results.size(); i++) {
            System.out.printf("Sum[%d]=%d %n", i, results.get(i));
        }
    }

    private void terminate() {
        listeningExecutorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TryExecutorsGuava tryExecutorsGuava = new TryExecutorsGuava();

        tryExecutorsGuava.runDirectExecutor();

        tryExecutorsGuava.runExitingExecutorService();

        tryExecutorsGuava.runListenableFutureWithGet();

        tryExecutorsGuava.runListenableFutureWithListener();

        tryExecutorsGuava.runListenableFutureWithFutureCallback();

        tryExecutorsGuava.runListenableFutureWithFutureAllAsList();

        tryExecutorsGuava.terminate();
    }

}
