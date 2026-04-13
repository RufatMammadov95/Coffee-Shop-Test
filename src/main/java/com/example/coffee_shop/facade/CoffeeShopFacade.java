package com.example.coffee_shop.facade;

import com.example.coffee_shop.adapter.Payment;
import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.decorator.Milk;
import com.example.coffee_shop.decorator.Sugar;
import com.example.coffee_shop.factory.CoffeeFactory;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;

public class CoffeeShopFacade {
	private CoffeeFactory coffeefactory;
	private static int orderCounter = 1;

	public CoffeeShopFacade() {
		this.coffeefactory = new CoffeeFactory();
	}

	public void placeOrder(String coffeeType, Customer customer, boolean addMilk, boolean addSugar, Payment payment) {
		Coffee coffee = coffeefactory.createCoffee(coffeeType);

		if (addMilk)
			coffee = new Milk(coffee);
		if (addSugar)
			coffee = new Sugar(coffee);

		Order order = new Order(orderCounter++, customer, coffee);

		order.pay(payment);

		CoffeeShop.getInstance().placeOrder(order);
	}
}
