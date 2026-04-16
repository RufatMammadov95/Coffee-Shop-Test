package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.decorator.Milk;

class DecoratorTest {

    @Test
    void base_coffee_should_exist() {
        Coffee coffee = new Espresso();

        assertNotNull(coffee);
        assertEquals("Espresso", coffee.getDescription());
    }

    @Test
    void milk_should_increase_cost_and_description() {
        Coffee coffee = new Espresso();
        Coffee milkCoffee = new Milk(coffee);

        assertTrue(milkCoffee.getDescription().contains("Milk"));
        assertTrue(milkCoffee.getCost() > coffee.getCost());
    }

    @Test
    void multiple_decorators_should_stack() {
        Coffee coffee = new Espresso();
        Coffee decorated = new Milk(new Milk(coffee));

        assertTrue(decorated.getDescription().contains("Milk"));
        assertTrue(decorated.getCost() > coffee.getCost());
    }
}