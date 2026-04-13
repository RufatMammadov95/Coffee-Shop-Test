package com.example.coffee_shop.template;

public abstract class CoffeePreparationTemplate {

	// Template Method
	public final void prepareRecipe() {
		heatWater();
		brewCoffee();
		addIngredients();
		pourInCup();
	}

	private void heatWater() {
		System.out.println("Heating water...");
	}

	protected abstract void brewCoffee();

	protected abstract void addIngredients();

	private void pourInCup() {
		System.out.println("Pouring into cup...");
	}
}
