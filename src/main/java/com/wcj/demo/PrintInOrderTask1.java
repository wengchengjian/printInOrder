package com.wcj.demo;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:24
 */
@Data
public class PrintInOrderTask1 implements Runnable {

    private String name;

    private int count;

    private Condition condition;

    private Lock lock;

    public PrintInOrderTask1(String name, int printCount, Condition condition, Lock lock) {
        this.name = name;
        this.count = printCount;
        this.condition = condition;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (count > 0) {
            lock.lock();
            try {
                condition.await();
                System.out.println(name);
                count--;
                condition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }
    }
}
