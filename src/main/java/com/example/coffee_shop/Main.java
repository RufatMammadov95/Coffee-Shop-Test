package com.example.coffee_shop;

import com.example.coffee_shop.adapter.CardPayment;
import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.facade.CoffeeShopFacade;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.strategy.SilverPricing;
import com.example.coffee_shop.template.CappuccinoMaker;
import com.example.coffee_shop.template.EspressoMaker;
import com.example.coffee_shop.template.LatteMaker;

public class Main {

	public static void main(String[] args) {

		// =========================
		// COFFEE SHOP INSTANCE
		// =========================
		CoffeeShop shop = CoffeeShop.getInstance();

		// =========================
		// FACADE (MAIN ENTRY POINT)
		// =========================
		CoffeeShopFacade facade = new CoffeeShopFacade();

		// =========================
		// CUSTOMERS (THREADS)
		// =========================
		Customer c1 = new Customer("Mbappe", new GoldPricing(), shop, new Espresso(), 1);

		Customer c2 = new Customer("Yamal", new SilverPricing(), shop, new Espresso(), 2);

		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);

		t1.start();
		t2.start();

		// =========================
		// ORDERS VIA FACADE
		// =========================
		facade.placeOrder("cappuccino", c1, true, true, new CardPayment());
		facade.placeOrder("espresso", c2, false, false, new CardPayment());

		// =========================
		// WAIT THREADS (IMPORTANT)
		// =========================
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// =========================
		// TEMPLATE METHOD TEST
		// =========================
		System.out.println("\n--- Template Method Test ---");

		EspressoMaker espressoMaker = new EspressoMaker();
		espressoMaker.prepareRecipe();

		LatteMaker latteMaker = new LatteMaker();
		latteMaker.prepareRecipe();

		CappuccinoMaker cappuccinoMaker = new CappuccinoMaker();
		cappuccinoMaker.prepareRecipe();
	}
}
