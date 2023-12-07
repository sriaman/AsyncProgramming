package com.java.ms.asyncprogramming.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
//@Service
public class ExchangeRatesService {

//    @Autowired
    private final RestTemplate restTemplate;

    public ExchangeRatesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }

    @Async
    public CompletableFuture<Double> getExchangeRate(String source, String target) {
//        Double result = Currency.init(source, target);
//        return CompletableFuture.completedFuture(result);
        String api_url = "http://localhost:8080/api/async/currency/exchange/"+source+"/"+target;
        Double result = restTemplate.getForObject(api_url,Double.class);
        return CompletableFuture.completedFuture(result);
    }
}
