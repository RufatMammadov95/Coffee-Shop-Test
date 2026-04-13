package com.example.coffee_shop.observer;

import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.strategy.PricingStrategy;

public class Customer implements Observer, Runnable {
	private String name;
	private PricingStrategy pricingStrategy;
	private CoffeeShop coffeeShop;
	private Coffee coffee;
	private int orderId;

	public Customer(String name, PricingStrategy pricingStrategy, CoffeeShop coffeeShop, Coffee coffee, int orderId) {
		this.name = name;
		this.pricingStrategy = pricingStrategy;
		this.coffeeShop = coffeeShop;
		this.coffee = coffee;
		this.orderId = orderId;
	}

	@Override
	public void run() {
		Order order = new Order(orderId, this, coffee);
		coffeeShop.placeOrder(order);
	}

	@Override
	public void update(String message) {
		System.out.println(name + ", " + message);
	}

	public String getName() {
		return name;
	}

	public PricingStrategy getPricingStrategy() {
		return pricingStrategy;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public int getOrderId() {
		return orderId;
	}
}
