package se.beis.worksample.service;

import se.beis.worksample.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Optional<Customer> findById(Long id);

    void addCustomer(Customer customer);
}
