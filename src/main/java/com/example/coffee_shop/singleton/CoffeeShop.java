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

	private OrderQueue orderQueue;
	private CoffeeFactory coffeeFactory;

	private AtomicInteger counter;

	private CoffeeShop() {
		init();
	}

	private void init() {
		orderQueue = new OrderQueue();
		coffeeFactory = new CoffeeFactory();
		counter = new AtomicInteger(1);

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
	// TEST SUPPORT
	// =========================
	public void resetForTests() {
		orderQueue = new OrderQueue();
		coffeeFactory = new CoffeeFactory();
		counter = new AtomicInteger(1);
	}

	// =========================
	// ORDER SYSTEM
	// =========================
	public void placeOrder(Order order) {

		if (order == null || order.getCustomer() == null)
			return;

		System.out.println(order.getCustomer().getName() + " placed order #" + order.getOrderId());

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

		if (message == null || message.isBlank()) {
			return "Hello! How can I help you today?";
		}

		String trimmed = message.trim();
		String lower = trimmed.toLowerCase();

		String reply;

		if (lower.startsWith("order")) {

			String type = trimmed.substring(5).trim().toLowerCase();

			if (type.isBlank()) {
				reply = "Invalid order request!";
			} else {
				try {
					Coffee coffee = coffeeFactory.createCoffee(type);

					int id = counter.getAndIncrement();

					Order order = new Order(id, new Customer(sender, new GoldPricing(), this, coffee, id), coffee);

					placeOrder(order);

					reply = "Your " + type + " order has been placed!";

				} catch (Exception e) {
					reply = "Invalid order request!";
				}
			}

		} else {
			reply = "Hello! How can I help you today?";
		}

		DatabaseUtil.saveMessage("Barista", reply);
		System.out.println("Barista: " + reply);

		return reply;
	}
}