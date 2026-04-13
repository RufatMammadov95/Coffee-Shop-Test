package com.example.coffee_shop.decorator;

public class Sugar implements Coffee {
	private Coffee coffee;

	public Sugar(Coffee coffee) {
		this.coffee = coffee;
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + " + Sugar";
	}

	@Override
	public double getCost() {
		return coffee.getCost() + 0.2;
	}
}
