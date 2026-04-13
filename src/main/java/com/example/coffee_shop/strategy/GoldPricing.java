package com.example.coffee_shop.strategy;

public class GoldPricing implements PricingStrategy {
	public double calculatePrice(double basePrice) {
		return basePrice * 0.8;
	}
}
