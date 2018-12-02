package orders.services.impl;

import orders.converters.CustomerDtoConverter;
import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import orders.repositories.CustomerRepository;
import orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
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
        List<Customer> customerList = new ArrayList<>();
        // RW: as we not transform the result further, then it can be returned straight away
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
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
