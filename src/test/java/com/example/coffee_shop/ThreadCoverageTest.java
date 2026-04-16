package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.thread.OrderQueue;

class ThreadCoverageTest {

    @Test
    void orderQueue_shouldHandleMultipleThreads() throws InterruptedException {

        OrderQueue queue = new OrderQueue();

        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);

        Customer customer = new Customer(
                "ThreadUser",
                new GoldPricing(),
                null,
                new Espresso(),
                1
        );

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                queue.addOrder(new Order(1, customer, new Espresso()));
                latch.countDown();
            }).start();
        }

        latch.await();

        int count = 0;
        while (queue.getOrder() != null) {
            count++;
        }

        assertEquals(threadCount, count);
    }
}