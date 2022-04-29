package com.chibs.Fraud;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud-check")
@AllArgsConstructor
public class FraudController {
    
    private final FraudCheckHistoryService fraudCheckService;


    @GetMapping(path = "/{customer_id}")
    public ResponseEntity<?> check(@PathVariable("customer_id") Long customerId) {
        return ResponseEntity.ok(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
