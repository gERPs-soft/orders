package orders.services;

import orders.dto.CustomerDto;
import orders.exceptions.CustomernotFoundException;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface CustomerService {
    public CustomerDto findCustomerById(Long id) throws CustomernotFoundException;
}
