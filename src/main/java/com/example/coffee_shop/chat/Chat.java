package com.example.coffee_shop.chat;

import com.example.coffee_shop.db.DatabaseUtil;

public class Chat {

    private String customerName;
    private String baristaName;

    public Chat(String customerName, String baristaName) {
        this.customerName = customerName;
        this.baristaName = baristaName;
    }

    public void sendMessageFromCustomer(String message) {
        System.out.println(customerName + ": " + message);
		DatabaseUtil.saveMessage(customerName, message);

        respondFromBarista(message);
    }

    private void respondFromBarista(String message) {
        String response;

        if (message.toLowerCase().contains("coffee")) {
            response = "Sure, what type of coffee would you like?";
        } else if (message.toLowerCase().contains("order")) {
            response = "Your order is being processed.";
        } else {
            response = "Hello! How can I help you?";
        }

        System.out.println(baristaName + ": " + response);
        DatabaseUtil.saveMessage(baristaName, response);
    }
}