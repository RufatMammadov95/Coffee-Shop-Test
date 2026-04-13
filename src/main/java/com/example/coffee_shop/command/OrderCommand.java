package com.example.coffee_shop.command;

import com.example.coffee_shop.adapter.Payment;
import com.example.coffee_shop.model.Status;
import com.example.coffee_shop.prototype.Order;

public class OrderCommand implements Command {
	private Order order;
	private Payment payment;

	public OrderCommand(Order order, Payment payment) {
		this.order = order;
		this.payment = payment;
	}

	@Override

	public void execute() {
		order.setStatus(Status.READY);
		order.pay(payment);
	}
}
