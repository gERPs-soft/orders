package orders.controller;

import orders.entities.Customer;
import orders.exceptions.CustomernotFoundException;
import orders.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by szypows_local on 30.11.2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public ResponseEntity findAllCustomers() {
        logger.info("find all customers()");
        return new ResponseEntity(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity findCustomerById(@PathVariable Long id) {
        logger.info("find Customer by id = " + id);
        try {
            return new ResponseEntity(customerService.findCustomerById(id), HttpStatus.OK);
        } catch (CustomernotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.valueOf("Can't find customer with id: " + id));
    }

    @PostMapping("/add")
    public ResponseEntity saveCustomer(@RequestBody Customer customer) {
        logger.info("save new customer()");
        if (customerService.saveCustomer(customer) != null)
            return new ResponseEntity(HttpStatus.OK);
        else {
            return new ResponseEntity(HttpStatus.valueOf("Can't save new customer"));
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        logger.info("delete customer with id = " + id);
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
