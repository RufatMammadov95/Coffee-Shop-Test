package com.example.coffee_shop.thread;

import java.util.LinkedList;
import java.util.Queue;
import com.example.coffee_shop.prototype.Order;

public class OrderQueue {

	private final Queue<Order> queue = new LinkedList<>();

	public synchronized void addOrder(Order order) {
		queue.add(order);
		notifyAll();
	}

	public synchronized Order getOrder() {
		return queue.poll();
	}

	public synchronized Order waitForOrder() {
		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		return queue.poll();
	}
}
