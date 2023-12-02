package com.java.ms.asyncprogramming.controller;

import com.java.ms.asyncprogramming.services.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.java.ms.asyncprogramming.constants.CurrencyName.*;

@RestController
@RequestMapping("/exchange")
public class AsyncController {

    private final ExchangeRatesService exchangeRatesService;

    @Autowired
    public AsyncController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/{source}")
    public ResponseEntity<Map<String, Double>> getExchangeRates(@PathVariable String source) throws ExecutionException, InterruptedException {

        CompletableFuture<Double> rateFromSource1 = exchangeRatesService.getExchangeRate(source, "USD");
        CompletableFuture<Double> rateFromSource2 = exchangeRatesService.getExchangeRate(source, "EUR");
        CompletableFuture<Double> rateFromSource3 = exchangeRatesService.getExchangeRate(source, "GBP");
        CompletableFuture<Double> rateFromSource4 = exchangeRatesService.getExchangeRate(source, "CHF");
        CompletableFuture<Double> rateFromSource5 = exchangeRatesService.getExchangeRate(source, "CNY");
        CompletableFuture<Double> rateFromSource6 = exchangeRatesService.getExchangeRate(source, "JPY");


        CompletableFuture
                .allOf(rateFromSource1, rateFromSource2, rateFromSource3, rateFromSource4, rateFromSource5, rateFromSource6).thenAccept(System.out::println);

        CompletableFuture
                .anyOf(rateFromSource1, rateFromSource2, rateFromSource3, rateFromSource4, rateFromSource5, rateFromSource6)
                .thenApply(result -> (Double) result).thenAccept(System.out::println);

        Map<String, Double> map = Map.of(
                String.valueOf(USD), rateFromSource1.get(),
                String.valueOf(EUR), rateFromSource2.get(),
                String.valueOf(GBP), rateFromSource3.get(),
                String.valueOf(CHF), rateFromSource4.get(),
                String.valueOf(CNY), rateFromSource5.get(),
                String.valueOf(JPY), rateFromSource6.get());

        return ResponseEntity.ok(map);

    }

    @GetMapping("/from/{source}/to/{target}")
    public ResponseEntity<Map<String, Double>> getExchangeRate(@PathVariable String source, @PathVariable String target) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> exchangeRate = exchangeRatesService.getExchangeRate(source, target);
        return ResponseEntity.ok(Map.of(target, exchangeRate.get()));
    }
}
