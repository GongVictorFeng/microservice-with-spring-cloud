package com.microservices.currency_exchange_service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("sample-api")
//    @Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
//    @CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    public String sampleApi() {
        logger.info("Sample Api call received");
//        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//        return responseEntity.getBody();
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
