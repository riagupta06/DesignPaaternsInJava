package com.codingnconcepts.factorydesignpattern;

public class SGDollar implements Currency {
    @Override
    public String getCode() {
        return "SGD";
    }

    @Override
    public String getSign() {
        return "S$";
    }
}
