package com.example.coffee_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;

class CoffeeShopTest extends BaseTest {

    @BeforeEach
    void resetShop() {
        shop.resetForTests();
    }

    @Test
    void getInstance_shouldAlwaysReturnSameObject() {
        CoffeeShop shop1 = CoffeeShop.getInstance();
        CoffeeShop shop2 = CoffeeShop.getInstance();

        assertSame(shop1, shop2);
    }

    @Test
    void singleton_shouldRemainSameAsBaseTestInstance() {
        CoffeeShop anotherReference = CoffeeShop.getInstance();

        assertSame(shop, anotherReference);
    }

    @Test
    void placeOrder_shouldAddOrderToQueue() {
        shop.sendChatMessage("JUnitUser", "order latte");

        Order order = shop.takeOrder();

        assertNotNull(order);
        assertEquals("JUnitUser", order.getCustomer().getName());
    }

    @Test
    void invalidOrder_shouldNotAddOrderToQueue() {
        shop.sendChatMessage("JUnitUser", "order ");

        Order order = shop.takeOrder();

        assertNull(order);
    }

    @Test
    void coffeeShop_shouldInitializeProperly() {
        assertNotNull(shop);
    }
}