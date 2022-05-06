package com.chibs.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud")
public interface FraudClient {

    @GetMapping(path = "fraud-check/{customer_id}")
    ResponseEntity<?> check(@PathVariable("customer_id") Long customerId);
}
