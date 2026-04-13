package com.example.coffee_shop.template;

public class EspressoMaker extends CoffeePreparationTemplate {

	@Override
	protected void brewCoffee() {
		System.out.println("Brewing Espresso coffee...");
	}

	@Override
	protected void addIngredients() {
		System.out.println("No ingredients added.");
	}
}
