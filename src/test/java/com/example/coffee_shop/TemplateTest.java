package com.example.coffee_shop;

import org.junit.jupiter.api.Test;

import com.example.coffee_shop.template.EspressoMaker;
import com.example.coffee_shop.template.LatteMaker;
import com.example.coffee_shop.template.CappuccinoMaker;

class TemplateTest {

    @Test
    void template_methods_should_execute_without_errors() {

        EspressoMaker e = new EspressoMaker();
        LatteMaker l = new LatteMaker();
        CappuccinoMaker c = new CappuccinoMaker();

        e.prepareRecipe();
        l.prepareRecipe();
        c.prepareRecipe();
    }
}