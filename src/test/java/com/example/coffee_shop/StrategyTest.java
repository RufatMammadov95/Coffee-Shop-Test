package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.strategy.SilverPricing;

class PricingStrategyTest {

    @Test
    void goldPricing_shouldCalculateConsistently() {

        GoldPricing gold = new GoldPricing();

        double price = gold.calculatePrice(10);

        assertTrue(price > 0);
        assertNotNull(price);
    }

    @Test
    void silverPricing_shouldCalculateConsistently() {

        SilverPricing silver = new SilverPricing();

        double price = silver.calculatePrice(10);

        assertTrue(price > 0);
    }

    @Test
    void gold_and_silver_should_differ() {

        GoldPricing gold = new GoldPricing();
        SilverPricing silver = new SilverPricing();

        double goldPrice = gold.calculatePrice(10);
        double silverPrice = silver.calculatePrice(10);

        assertNotEquals(goldPrice, silverPrice);
    }
}