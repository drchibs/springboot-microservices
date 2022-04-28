package com.chibs.Customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest){
        log.info("New Test Customer Request: {}", customerRequest);
        return ResponseEntity.ok(customerService.registerCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        log.info("Get All Customers");
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


}
