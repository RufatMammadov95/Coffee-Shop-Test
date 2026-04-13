package com.example.coffee_shop.template;

public class LatteMaker extends CoffeePreparationTemplate {

	@Override
	protected void brewCoffee() {
		System.out.println("Brewing Latte coffee...");
	}

	@Override
	protected void addIngredients() {
		System.out.println("Adding milk.");
	}
}
