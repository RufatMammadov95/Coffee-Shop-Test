package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChatTest extends BaseTest {

    @BeforeEach
    void resetShop() {
        shop.resetForTests();
    }

    @Test
    void sendChatMessage_shouldReturnGreetingForNormalMessage() {
        String reply = shop.sendChatMessage("JUnitUser", "Hello");

        assertEquals("Hello! How can I help you today?", reply);
    }

    @Test
    void sendChatMessage_shouldProcessOrderCommand() {
        String reply = shop.sendChatMessage("JUnitUser", "order latte");

        assertEquals("Your latte order has been placed!", reply);
    }

    @Test
    void sendChatMessage_shouldHandleEmptyMessage() {
        String reply = shop.sendChatMessage("JUnitUser", "");

        assertEquals("Hello! How can I help you today?", reply);
    }

    @Test
    void sendChatMessage_shouldHandleWhitespaceMessage() {
        String reply = shop.sendChatMessage("JUnitUser", "   ");

        assertEquals("Hello! How can I help you today?", reply);
    }

    @Test
    void sendChatMessage_shouldHandleUnknownCommand() {
        String reply = shop.sendChatMessage("JUnitUser", "random text");

        assertEquals("Hello! How can I help you today?", reply);
    }

    @Test
    void sendChatMessage_shouldBeCaseInsensitiveForOrder() {
        String reply = shop.sendChatMessage("JUnitUser", "ORDER cappuccino");

        assertEquals("Your cappuccino order has been placed!", reply);
    }

    @Test
    void sendChatMessage_shouldRejectInvalidCoffeeType() {
        String reply = shop.sendChatMessage("JUnitUser", "order spaceship");

        assertEquals("Invalid order request!", reply);
    }
}