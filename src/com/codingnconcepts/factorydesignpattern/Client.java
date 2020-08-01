package com.codingnconcepts.factorydesignpattern;

public class Client {

    public static void main(String [] args) {
        Currency indiaCurrency = CurrencyFactory.createCurrency(Country.INDIA.toString());
        Currency singaporeCurrency = CurrencyFactory.createCurrency(Country.SINGAPORE.toString());
        Currency usaCurrency = CurrencyFactory.createCurrency(Country.USA.toString());
        //Currency canadaCurrency = CurrencyFactory.createCurrency(Country.CANADA.toString());  //Exception in thread "main" java.lang.IllegalArgumentException: No currency found for 'Canada'
        System.out.println(indiaCurrency.getCode());
        System.out.println(indiaCurrency.getSign());
        System.out.println(singaporeCurrency.getCode());
        System.out.println(singaporeCurrency.getSign());
        System.out.println(usaCurrency.getCode());
        System.out.println(usaCurrency.getSign());
    }
    //Output
    /*
    INR
    ₹
    SGD
    S$
    USD
    $
     */

}
