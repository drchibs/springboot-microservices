package com.chibs.Customer;

import com.chibs.clients.fraud.FraudClient;
import com.chibs.clients.notification.NotificationClient;
import com.chibs.clients.notification.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;

    private final RestTemplate restTemplate;


    public Optional<Customer> getCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail()).build();
        Customer newCustomer = customerRepository.saveAndFlush(customer);
        // String url = "http://FRAUD/fraud-check/{customer_id}";
        // String response = restTemplate.getForObject(url, String.class, customer.getId());

        // check fraud
        ResponseEntity<?> req = fraudClient.check(customer.getId());
        Boolean response = (Boolean) req.getBody();
        log.info("Fraud check response: {}", response);
        assert response != null;
        if (response){
            throw new IllegalStateException("Customer is not allowed to register");
        }

        //send notification
        NotificationDTO newNotification = NotificationDTO.builder()
                .title("Customer Registration")
                .message("Customer with id: " + customer.getId() + " has been registered").build();
        notificationClient.sendNotification(newNotification);

        return newCustomer;
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
