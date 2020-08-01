package com.codingnconcepts.factorydesignpattern;

public class CurrencyFactory {

    public static Currency createCurrency(String country) {

        if(country.equalsIgnoreCase(Country.SINGAPORE.toString())) {
            return new SGDollar();
        } else if(country.equalsIgnoreCase(Country.INDIA.toString())) {
            return new Rupee();
        } else if(country.equalsIgnoreCase(Country.USA.toString())) {
            return new USDollar();
        }
        throw  new IllegalArgumentException(String.format("No currency found for '%s'", country));

    }

}
