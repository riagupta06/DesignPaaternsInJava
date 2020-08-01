package com.codingnconcepts.factorydesignpattern;

public class Rupee implements Currency {
    @Override
    public String getCode() {
        return "INR";
    }

    @Override
    public String getSign() {
        return "₹";
    }
}
