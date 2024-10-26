package org.example.lab4.services;

import org.example.lab4.entities.Customer;
import org.example.lab4.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Long id, Customer newCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setFirstName(newCustomer.getFirstName());
        existingCustomer.setLastName(newCustomer.getLastName());
        existingCustomer.setEmail(newCustomer.getEmail());

        customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

