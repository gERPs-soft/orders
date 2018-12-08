package orders.services.impl;

import orders.converters.CustomerDtoConverter;
import orders.dto.CustomerDto;
import orders.dto.OrderDto;
import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import orders.repositories.CustomerRepository;
import orders.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(Long id) throws CustomernotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else
            throw new CustomernotFoundException("Can't find customer with ID " + id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
