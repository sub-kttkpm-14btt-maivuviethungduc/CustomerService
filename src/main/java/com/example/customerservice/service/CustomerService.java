package com.example.customerservice.service;


import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer order) {
        return customerRepository.save(order);
    }

    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).get();
    }
}
