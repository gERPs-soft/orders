package orders.controller;

import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by szypows_local on 30.11.2018.
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public ResponseEntity findAll() {
        return new ResponseEntity(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            return new ResponseEntity(customerService.findCustomerById(id), HttpStatus.OK);
        } catch (CustomernotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.valueOf("Can't find customer with id: " + id));
    }

    @PostMapping("/add")
    public ResponseEntity saveCustomer(@RequestBody Customer customer) {
        if (customerService.saveCustomer(customer) != null)
            return new ResponseEntity(HttpStatus.OK);
        else {
            return new ResponseEntity(HttpStatus.valueOf("Can't save new customer"));
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
