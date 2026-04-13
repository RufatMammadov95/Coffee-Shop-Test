package com.example.coffee_shop.template;

public class CappuccinoMaker extends CoffeePreparationTemplate {

	@Override
	protected void brewCoffee() {
		System.out.println("Brewing Cappuccino coffee...");
	}

	@Override
	protected void addIngredients() {
		System.out.println("Adding milk and foam.");
	}
}
