package com.example.coffee_shop.strategy;

public class RegularPricing implements PricingStrategy {
	public double calculatePrice(double basePrice) {
		return basePrice;
	}
}
