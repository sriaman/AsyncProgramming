package com.java.ms.asyncprogramming.services;

import com.java.ms.asyncprogramming.model.Currency;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ExchangeRatesService {

    public CompletableFuture<Double> getExchangeRate(String source, String target) {
        Double result = Currency.init(source, target);
        return CompletableFuture.completedFuture(result);
    }
}
