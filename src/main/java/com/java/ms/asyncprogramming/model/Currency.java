package com.java.ms.asyncprogramming.model;


import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
@Data
public class Currency {


    static List<Currency> currencies = new ArrayList<>();
    private String name;
    private String shortName;
    private ConcurrentMap<String,Double> exchangeValue = new ConcurrentHashMap<>();

    public Currency(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }
    public static void init(){

        currencies.add(new Currency("US Dollar","USD"));
        currencies.add( new Currency("Euro", "EUR") );
        currencies.add( new Currency("British Pound", "GBP") );
        currencies.add( new Currency("Swiss Franc", "CHF") );
        currencies.add( new Currency("Chinese Yuan Renminbi", "CNY") );
        currencies.add( new Currency("Japanese Yen", "JPY") );

        for(Currency currency:currencies)
            currency.defaultValues();
    }

    public static Double init(String source,String target){
        init();
        for(Currency cur:currencies)
            if(cur.getShortName().equals(source))
                return cur.getExchangeValue().get(target);
        return -1.0;
    }


    private void defaultValues() {
        String currency = this.name;
        switch (currency){
            case "US Dollar":
                this.exchangeValue.put("USD",1.0);
                this.exchangeValue.put("EUR", 0.93);
                this.exchangeValue.put("GBP", 0.66);
                this.exchangeValue.put("CHF", 1.01);
                this.exchangeValue.put("CNY", 6.36);
                this.exchangeValue.put("JPY", 123.54);
                break;
            case "Euro":
                this.exchangeValue.put("USD", 1.073);
                this.exchangeValue.put("EUR", 1.00);
                this.exchangeValue.put("GBP", 0.71);
                this.exchangeValue.put("CHF", 1.08);
                this.exchangeValue.put("CNY", 6.83);
                this.exchangeValue.put("JPY", 132.57);
                break;
            case "British Pound":
                this.exchangeValue.put("USD", 1.51);
                this.exchangeValue.put("EUR", 1.41);
                this.exchangeValue.put("GBP", 1.00);
                this.exchangeValue.put("CHF", 1.52);
                this.exchangeValue.put("CNY", 9.60);
                this.exchangeValue.put("JPY", 186.41);
                break;
            case "Swiss Franc":
                this.exchangeValue.put("USD", 0.99);
                this.exchangeValue.put("EUR", 0.93);
                this.exchangeValue.put("GBP", 0.66);
                this.exchangeValue.put("CHF", 1.00);
                this.exchangeValue.put("CNY", 6.33);
                this.exchangeValue.put("JPY", 122.84);
                break;
            case "Chinese Yuan Renminbi":
                this.exchangeValue.put("USD", 0.16);
                this.exchangeValue.put("EUR", 0.15);
                this.exchangeValue.put("GBP", 0.11);
                this.exchangeValue.put("CHF", 0.16);
                this.exchangeValue.put("CNY", 1.00);
                this.exchangeValue.put("JPY", 19.41);
                break;
            case "Japanese Yen":
                this.exchangeValue.put("USD", 0.008);
                this.exchangeValue.put("EUR", 0.007);
                this.exchangeValue.put("GBP", 0.005);
                this.exchangeValue.put("CHF", 0.008);
                this.exchangeValue.put("CNY", 0.051);
                this.exchangeValue.put("JPY", 1.000);
                break;
        }
    }


}
