package com.example.coffee_shop.adapter;

public class PayPalPayment implements Payment {
	@Override
	public void pay(double amount) {
		System.out.println(String.format("Paid $%.2f using PayPal", amount));
	}
}
