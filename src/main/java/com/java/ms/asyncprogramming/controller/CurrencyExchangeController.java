package com.java.ms.asyncprogramming.controller;

import com.java.ms.asyncprogramming.model.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/async")
public class CurrencyExchangeController {

    @GetMapping("/currency/exchange/{source}/{target}")
    public Double getCurrencyExchangeDetails(@PathVariable String source,@PathVariable String target) {
        return Currency.init(source, target);

    }
}
