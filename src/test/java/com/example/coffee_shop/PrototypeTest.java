package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.model.Status;

class OrderPrototypeTest {

	@Test
	void clone_shouldCreateDifferentObject() {

		Customer customer = new Customer("TestUser", new GoldPricing(), CoffeeShop.getInstance(), new Espresso(), 1);

		Order original = new Order(1, customer, new Espresso());
		Order clone = original.cloneOrder();

		assertNotSame(original, clone);
	}

	@Test
	void clone_shouldPreserveValues() {

		Customer customer = new Customer("TestUser", new GoldPricing(), CoffeeShop.getInstance(), new Espresso(), 1);

		Order original = new Order(1, customer, new Espresso());
		Order clone = original.cloneOrder();

		assertEquals(original.getOrderId(), clone.getOrderId());
		assertEquals(original.getStatus(), clone.getStatus());
		assertEquals(original.getPrice(), clone.getPrice());
	}

	@Test
	void clone_shouldHaveIndependentObserversList() {

		Customer customer = new Customer("TestUser", new GoldPricing(), CoffeeShop.getInstance(), new Espresso(), 1);

		Order original = new Order(1, customer, new Espresso());
		Order clone = original.cloneOrder();

		clone.setStatus(Status.READY);

		assertNotSame(original.getStatus(), clone.getStatus());
	}
}