package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.thread.OrderQueue;

class OrderQueueTest {

    private Customer createCustomer() {
        return new Customer(
                "JUnitUser",
                new GoldPricing(),
                CoffeeShop.getInstance(),
                new Espresso(),
                1
        );
    }

    @Test
    void orderQueue_shouldStoreAndReturnAllOrders() {
        OrderQueue queue = new OrderQueue();
        Customer customer = createCustomer();

        for (int i = 1; i <= 5; i++) {
            queue.addOrder(new Order(i, customer, new Espresso()));
        }

        int processed = 0;

        for (int i = 1; i <= 5; i++) {
            if (queue.getOrder() != null) {
                processed++;
            }
        }

        assertEquals(5, processed);
    }

    @Test
    void orderQueue_shouldReturnNullWhenEmpty() {
        OrderQueue queue = new OrderQueue();

        assertNull(queue.getOrder());
    }

    @Test
    void orderQueue_shouldPreserveFIFOOrder() {
        OrderQueue queue = new OrderQueue();
        Customer customer = createCustomer();

        queue.addOrder(new Order(1, customer, new Espresso()));
        queue.addOrder(new Order(2, customer, new Espresso()));
        queue.addOrder(new Order(3, customer, new Espresso()));

        assertEquals(1, queue.getOrder().getOrderId());
        assertEquals(2, queue.getOrder().getOrderId());
        assertEquals(3, queue.getOrder().getOrderId());
    }
}