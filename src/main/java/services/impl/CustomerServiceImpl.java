package services.impl;

import converters.CustomerDtoConverter;
import dto.CustomerDto;
import entities.Customer;
import exceptions.CustomernotFoundException;
import repositories.CustomerRepository;
import services.CustomerService;

import java.util.Optional;

/**
 * Created by szypows_local on 18.11.2018.
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
    }

    @Override
    public CustomerDto findCustomerById(Long id) throws CustomernotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CustomerDto customerDto = customerDtoConverter.apply(customer);
            return customerDto;
        } else
            throw new CustomernotFoundException("Can't find customer with ID " + id);
    }
}
