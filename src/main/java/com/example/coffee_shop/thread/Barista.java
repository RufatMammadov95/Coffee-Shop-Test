package com.example.coffee_shop.thread;

import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.model.Status;

public class Barista implements Runnable {

	private String name;
	private CoffeeShop coffeeShop;
	private int maxOrders;

	public Barista(String name, CoffeeShop coffeeShop, int maxOrders) {
		this.name = name;
		this.coffeeShop = coffeeShop;
		this.maxOrders = maxOrders;
	}

	@Override
	public void run() {
		for (int i = 0; i < maxOrders; i++) {

			Order order = coffeeShop.waitForOrder();

			if (order == null)
				continue;

			order.setStatus(Status.PREPARING);

			System.out.println(
					name + " is preparing order #" + order.getOrderId() + " for " + order.getCustomer().getName());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			order.setStatus(Status.READY);

			System.out.println(name + " finished order #" + order.getOrderId());
		}
	}
}
