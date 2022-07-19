package com.wcj.demo;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:45
 */
@Data
public class PrintInOrderTask2 implements Runnable {
    private String name;

    private int count;

    private String expectName;

    private String nextName;

    private AtomicReference<String> atomicName;

    public PrintInOrderTask2(String name, int printCount, String expectName, String nextName, AtomicReference<String> atomicName) {
        this.name = name;
        this.count = printCount;
        this.expectName = expectName;
        this.nextName = nextName;
        this.atomicName = atomicName;
    }

    @Override
    public void run() {
        while (count > 0) {
            // 判断是否该自己执行
            if (!atomicName.get().equals(expectName)) {
                continue;
            }
            System.out.println(name);
            count--;
            atomicName.compareAndSet(expectName,nextName);
        }

    }
}
