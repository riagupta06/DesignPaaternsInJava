package com.codingnconcepts.factorydesignpattern;

public class USDollar implements Currency {
    @Override
    public String getCode() {
        return "USD";
    }

    @Override
    public String getSign() {
        return "$";
    }
}
