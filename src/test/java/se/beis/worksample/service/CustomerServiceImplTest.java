package se.beis.worksample.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.exception.ResourceNotFoundException;
import se.beis.worksample.persistence.CustomerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void shouldReturnCustomerWhenCustomerExists() throws Exception {
        Customer customer = new Customer();
        customer.setId(0L);
        when(customerRepository.findById(0L)).thenReturn(Optional.of(customer));
        Customer returnedCustomer = customerService.findById(0L);
        assertEquals(customer, returnedCustomer);
    }

    @Test
    public void shouldThrowExceptionWhenCustomerDoesNotExist() {
        when(customerRepository.findById(0L)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> customerService.findById(0L), "Should have thrown a ResourceNotFoundException");
        assertEquals("Customer with id 0 not found", thrown.getMessage());
    }

}