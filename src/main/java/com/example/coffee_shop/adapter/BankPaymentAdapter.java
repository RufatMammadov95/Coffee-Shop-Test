package com.example.coffee_shop.adapter;

public class BankPaymentAdapter implements Payment {

	private LegacyBankPayment legancy;

	public BankPaymentAdapter(LegacyBankPayment legancy) {
		this.legancy = legancy;
	}

	@Override
	public void pay(double amount) {
		legancy.makePayment(amount);
	}
}
