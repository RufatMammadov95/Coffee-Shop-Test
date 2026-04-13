package com.example.coffee_shop.decorator;

public class Cappuccino implements Coffee {
	@Override
	public String getDescription() {
		return "Cappuccino";
	}

	@Override
	public double getCost() {
		return 3;
	}
}
