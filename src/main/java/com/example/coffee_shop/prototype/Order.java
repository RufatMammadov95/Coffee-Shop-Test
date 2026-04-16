package com.example.coffee_shop.prototype;

import java.util.ArrayList;
import java.util.List;

import com.example.coffee_shop.adapter.Payment;
import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.model.Status;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.observer.Observer;

public class Order {

	private int orderId;
	private Coffee coffee;
	private double price;
	private Status status;
	private Customer customer;
	private List<Observer> observers = new ArrayList<>();

	public Order(int orderId, Customer customer, Coffee coffee) {
		this.orderId = orderId;
		this.customer = customer;
		this.coffee = coffee;

		this.price = customer.getPricingStrategy().calculatePrice(coffee.getCost());
		this.status = Status.PREPARING;

		addObserver(customer);
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void notifyObservers(String message) {
		for (Observer o : observers) {
			o.update(message);
		}
	}

	public void pay(Payment payment) {
		payment.pay(this.price);
	}

	public Order cloneOrder() {

		Coffee clonedCoffee = this.coffee;

		Order cloned = new Order(this.orderId, this.customer, clonedCoffee);

		cloned.observers = new ArrayList<>();
		cloned.addObserver(this.customer);

		cloned.status = this.status;

		return cloned;
	}

	public String getFormattedPrice() {
		return String.format("%.2f", price);
	}

	// Getters
	public int getOrderId() {
		return orderId;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public double getPrice() {
		return price;
	}

	public Status getStatus() {
		return status;
	}

	public Customer getCustomer() {
		return customer;
	}

	// Setter
	public void setStatus(Status status) {
		this.status = status;

		if (status == Status.READY) {
			notifyObservers("Your order #" + orderId + " is ready!");
		}
	}
}
