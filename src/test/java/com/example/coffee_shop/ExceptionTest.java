package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.factory.CoffeeFactory;

class ExceptionTest {

    @Test
    void factory_shouldThrowExceptionForInvalidCoffeeType() {

        CoffeeFactory factory = new CoffeeFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCoffee("invalid_type");
        });
    }

    @Test
    void factory_shouldThrowExceptionForEmptyInput() {

        CoffeeFactory factory = new CoffeeFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCoffee("");
        });
    }

    @Test
    void factory_shouldThrowExceptionForNullInput() {

        CoffeeFactory factory = new CoffeeFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCoffee(null);
        });
    }
}