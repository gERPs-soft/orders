package orders.services.impl;

import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import orders.repositories.CustomerRepository;
import orders.services.CustomerService;
import org.springframework.stereotype.Service;

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
        Optional<Customer> customerOptional = customerRepository.findByIdAndActiveTrue(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new CustomernotFoundException("Can't find customer with ID " + id);
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        return StreamSupport.stream(customerRepository.findAllByActiveTrue().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) throws CustomernotFoundException {
        Customer customer = findCustomerById(id);
        customer.setActive(false);
        return customerRepository.save(customer);
    }
}
