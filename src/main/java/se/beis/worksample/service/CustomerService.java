package se.beis.worksample.service;

import se.beis.worksample.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer findById(Long id) throws Exception;

    Customer createCustomer(Customer customer);
}
