package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.coffee_shop.db.DatabaseUtil;

class DatabaseUtilTest {

    @BeforeEach
    void resetDatabase() {
        DatabaseUtil.clear();
    }

    @Test
    void save_and_read_chat_should_work() {

        DatabaseUtil.saveMessage("PeerUser", "Hello DB Test");

        String history = DatabaseUtil.getChatHistory();

        assertNotNull(history);
        assertTrue(history.contains("PeerUser"));
        assertTrue(history.contains("Hello DB Test"));
    }

    @Test
    void save_multiple_messages_should_accumulate() {

        DatabaseUtil.saveMessage("User1", "Msg1");
        DatabaseUtil.saveMessage("User2", "Msg2");

        String history = DatabaseUtil.getChatHistory();

        assertTrue(history.contains("User1"));
        assertTrue(history.contains("User2"));
    }

    @Test
    void empty_database_should_return_empty_or_null() {

        String history = DatabaseUtil.getChatHistory();

        assertNotNull(history);
        assertTrue(history.isBlank());
    }
}