package com.example.coffee_shop.singleton;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.coffee_shop.db.DatabaseUtil;
import com.example.coffee_shop.factory.CoffeeFactory;
import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.thread.OrderQueue;
import com.example.coffee_shop.thread.Barista;

public class CoffeeShop {

    private static CoffeeShop instance;

    private final OrderQueue orderQueue;
    private final CoffeeFactory coffeeFactory;

    private final AtomicInteger counter = new AtomicInteger(1);

    private CoffeeShop() {

        orderQueue = new OrderQueue();
     
        coffeeFactory = new CoffeeFactory();

        Thread b1 = new Thread(new Barista("Messi", this, 10));
        Thread b2 = new Thread(new Barista("Ronaldo", this, 10));

        b1.start();
        b2.start();
    }

    public static synchronized CoffeeShop getInstance() {
        if (instance == null) {
            instance = new CoffeeShop();
        }
        return instance;
    }

    // =========================
    // ORDER SYSTEM
    // =========================
    public void placeOrder(Order order) {

        System.out.println(order.getCustomer().getName()
                + " placed order #" + order.getOrderId());

        orderQueue.addOrder(order);
    }

    public Order takeOrder() {
        return orderQueue.getOrder();
    }

    // =========================
    // CHAT SYSTEM
    // =========================
    public synchronized String sendChatMessage(String sender, String message) {

        DatabaseUtil.saveMessage(sender, message);
        System.out.println(sender + ": " + message);

        String lower = message.toLowerCase();
        String reply;

        if (lower.startsWith("order")) {

            String type = message.replace("order", "").trim();

            Coffee coffee = coffeeFactory.createCoffee(type);

            int id = counter.getAndIncrement();

            Order order = new Order(
                    id,
                    new Customer(sender, new GoldPricing(), this, coffee, id),
                    coffee
            );

            placeOrder(order);

            reply = "Your " + type + " order has been placed!";

        } else {

            reply = "Hello! How can I help you today?";
        }

        DatabaseUtil.saveMessage("Barista", reply);
        System.out.println("Barista: " + reply);

        return reply;
    }
}
