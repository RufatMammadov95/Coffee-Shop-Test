package com.example.coffee_shop.factory;

import com.example.coffee_shop.decorator.Cappuccino;
import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.decorator.Latte;

public class CoffeeFactory {
	public Coffee createCoffee(String type) {
		if (type == null || type.isBlank()) {
			throw new IllegalArgumentException("Unknown coffee type");
		}
		if (type.equalsIgnoreCase("espresso")) {
			return new Espresso();
		} else if (type.equalsIgnoreCase("latte")) {
			return new Latte();
		} else if (type.equalsIgnoreCase("cappuccino")) {
			return new Cappuccino();
		}
		throw new IllegalArgumentException("Unknown coffee type");
	}
}
