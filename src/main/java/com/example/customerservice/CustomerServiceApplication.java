package com.example.customerservice;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.SearchCustomer;
import com.example.customerservice.service.CustomerService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

@SpringBootApplication
public class CustomerServiceApplication {

    void displayDefaultValues() {
        RateLimiterConfig config = RateLimiterConfig.ofDefaults();

        System.out.println("Limit for period = "
                + config.getLimitForPeriod());

        System.out.println(("Refresh period = "
                + Duration.from(config.getLimitRefreshPeriod()).toNanos()));

        System.out.println("Timeout value = "
                + Duration.from(config.getTimeoutDuration()).toMillis());
    }
    void basicExample() {

        RateLimiterConfig config =
                RateLimiterConfig.
                        custom().
                        limitForPeriod(1).
                        limitRefreshPeriod(Duration.ofSeconds(1)).
                        timeoutDuration(Duration.ofSeconds(5)).build();

        RateLimiterRegistry registry
                = RateLimiterRegistry.of(config);
        RateLimiter limiter = registry.rateLimiter("flightSearchService");

        CustomerService service = new CustomerService();
        SearchCustomer request = new SearchCustomer("123", "duc");

        Supplier<List<Customer>> customerSupplier =
                RateLimiter.decorateSupplier(limiter, () -> service.searchCustomer(request));

        for (int i=0; i<3; i++) {

            System.out.println(customerSupplier.get());
        }
    }


    public static void main(String[] args) {

        CustomerServiceApplication examples = new CustomerServiceApplication();
        System.out.println("---------------------------- displayDefaultValues -------------------------------------------");
        examples.displayDefaultValues();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("----------------------------- basicExample ------------------------------------------");
        examples.basicExample();SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
