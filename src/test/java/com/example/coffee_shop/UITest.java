package com.example.coffee_shop;

import org.junit.jupiter.api.Test;
import com.example.coffee_shop.ui.ChatApp;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    @Test
    void chat_app_should_initialize() {
        ChatApp app = new ChatApp();
        assertNotNull(app);
    }
}