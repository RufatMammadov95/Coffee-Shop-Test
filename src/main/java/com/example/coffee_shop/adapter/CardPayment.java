package com.example.coffee_shop.adapter;

public class CardPayment implements Payment {
	@Override
	public void pay(double amount) {
		System.out.println(String.format("Paid $%.2f using Credit Card", amount));
	}

}
