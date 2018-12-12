package orders.services.impl;

import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import orders.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by szypows_local on 12.12.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void shouldReturnCustomerWithId() {
        //Given
        Long id = 1l;
        Customer customer = new Customer();
        Customer testCustomer = new Customer();
        //When
        customerService.saveCustomer(testCustomer);
        try {
            customer = customerService.findCustomerById(id);
        } catch (CustomernotFoundException e) {
            e.printStackTrace();
        }
        //Then
        assertEquals(id, customer.getId());
    }

    @Test
    public void shouldReturnMessageCustomerNotFound() {
        //Given
        Long id = 1l;
        Customer customer = new Customer();
        //When
        //Then
        try {
            customer = customerService.findCustomerById(id);
        } catch (CustomernotFoundException e) {
            assertEquals(e.getMessage(), "Can't find customer with ID 1");
        }
    }

    @Test
    public void shouldReturnListCustomers() {
        //Given
        List<Customer> customerList = new ArrayList<>();
        Customer testCustomerA = new Customer();
        Customer testCustomerB = new Customer();
        //When
        customerService.saveCustomer(testCustomerA);
        customerService.saveCustomer(testCustomerB);
        customerList = customerService.findAllCustomers();
        //Then
        assertEquals(3, customerList.size());
    }

    @Test
    public void saveCustomer() {
        //Given
        //When
        //Then
    }

    @Test
    public void deleteCustomer() {
        //Given
        //When
        //Then
    }
}