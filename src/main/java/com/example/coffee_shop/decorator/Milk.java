package com.example.coffee_shop.decorator;

public class Milk implements Coffee {
	private Coffee coffee;

	public Milk(Coffee coffee) {
		this.coffee = coffee;
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + " + Milk";
	}

	@Override
	public double getCost() {
		return coffee.getCost() + 0.5;
	}
}
