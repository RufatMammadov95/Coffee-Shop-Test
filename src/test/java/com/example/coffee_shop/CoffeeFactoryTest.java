package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.factory.CoffeeFactory;
import com.example.coffee_shop.decorator.Coffee;

class CoffeeFactoryTest {

    @Test
    void factory_shouldCreateEspresso() {
        CoffeeFactory factory = new CoffeeFactory();

        Coffee coffee = factory.createCoffee("espresso");

        assertNotNull(coffee);
        assertEquals("Espresso", coffee.getClass().getSimpleName());
    }

    @Test
    void factory_shouldCreateLatte() {
        CoffeeFactory factory = new CoffeeFactory();

        Coffee coffee = factory.createCoffee("latte");

        assertEquals("Latte", coffee.getClass().getSimpleName());
    }

    @Test
    void factory_shouldThrowExceptionForUnknownType() {
        CoffeeFactory factory = new CoffeeFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCoffee("tea");
        });
    }
}