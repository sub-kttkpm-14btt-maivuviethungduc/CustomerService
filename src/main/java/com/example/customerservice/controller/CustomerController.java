package com.example.customerservice.controller;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustomer(@RequestBody  Customer customer) {
        Customer customer1 = customerService.saveCustomer(customer);
        return customer1;
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable long id) {
        return customerService.findCustomerById(id);
    }

    @Value("${welcome}")
    private String welcome;

    @GetMapping("/")
    public String helloWorld(){return welcome;}
}
