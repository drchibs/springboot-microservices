package com.chibs.Customer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;


    public Optional<Customer> getCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail()).build();
        customerRepository.saveAndFlush(customer);
        String url = "http://FRAUD/api/v1/fraud-check/{customer_id}";
        String response = restTemplate.getForObject(url, String.class, customer.getId());

        assert response != null;
        if (response.equals("false")){
            throw new IllegalStateException("Customer is not allowed to register");
        }
        return customer;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customerId) {
        customerRepository.delete(customerId);
    }
}
