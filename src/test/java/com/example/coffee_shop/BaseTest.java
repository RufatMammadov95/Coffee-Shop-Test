package com.example.coffee_shop;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;

import com.example.coffee_shop.singleton.CoffeeShop;

public class BaseTest {

    protected CoffeeShop shop;

    @BeforeEach
    void setUp() {
        shop = CoffeeShop.getInstance();
        shop.resetForTests();

        assertNotNull(shop);
    }

    @AfterEach
    void tearDown() {
 
    }
}