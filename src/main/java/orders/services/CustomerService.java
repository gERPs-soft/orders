package orders.services;

import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;

import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface CustomerService {
    public Customer findCustomerById(Long id) throws CustomernotFoundException;

    public List<Customer> findAllCustomers();

    public Customer saveCustomer(Customer customer);

    public Customer deleteCustomer(Long id) throws CustomernotFoundException;
}
