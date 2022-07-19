package com.wcj.demo;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:16
 */
@Data
public class PrintInOrder1 implements Runnable {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    private static final int  PRINT_COUNT = 10;

    private static final int PRINT_THREAD = 4;

    private static final String[] THREAD_NAMES = new String[]{"A","B","C","D"};

    private Thread[] threads;

    public PrintInOrder1(){
        threads = new Thread[PRINT_THREAD];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PrintInOrderTask1(THREAD_NAMES[i],PRINT_COUNT,condition,lock));
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

    public void solution() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            Thread.sleep(1000);
        }
        lock.lock();
        try{
            // 通知第一个开始打印
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
