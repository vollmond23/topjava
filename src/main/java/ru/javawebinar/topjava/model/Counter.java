package ru.javawebinar.topjava.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private static final AtomicInteger counter = new AtomicInteger();

    private Counter() {
    }

    public static int get() {
        return counter.getAndIncrement();
    }
}