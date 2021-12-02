package com.example.customerservice.service;


import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.SearchCustomer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Service;


import javax.naming.Name;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer order) {
        return customerRepository.save(order);
    }

    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).get();
    }
    public List<Customer> searchCustomer(SearchCustomer request){
        System.out.println("Searching for customer"
                + "current time = " + LocalDateTime.now().format(formatter) +
                "; current thread = " + Thread.currentThread().getName());

        List<Customer> customers = Arrays.asList(
                new Customer()
        );
        System.out.println("Customer search successful");
        return customers;
    };
    }

