package com.wcj.demo;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:43
 */
@Data
public class PrintInOrder2 implements Runnable {
    private static final int PRINT_COUNT = 10;

    private AtomicReference<String> atomicName;
    private static final int PRINT_THREAD = 4;

    private static final String[] THREAD_NAMES = new String[]{"A", "B", "C", "D"};

    private Thread[] threads;

    public PrintInOrder2() {
        threads = new Thread[PRINT_THREAD];
        atomicName = new AtomicReference<>(THREAD_NAMES[0]);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PrintInOrderTask2(THREAD_NAMES[i], PRINT_COUNT,THREAD_NAMES[i],THREAD_NAMES[(i+1)%threads.length],atomicName));
        }
    }

    @Override
    public void run() {
        try {
            solution();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void solution() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
