package com.example.coffee_shop.decorator;

public class Espresso implements Coffee {
	@Override
	public String getDescription() {
		return "Espresso";
	}

	@Override
	public double getCost() {
		return 2.5;
	}
}
