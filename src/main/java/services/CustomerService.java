package services;

import dto.CustomerDto;
import exceptions.CustomernotFoundException;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface CustomerService {
    public CustomerDto findCustomerById(Long id) throws CustomernotFoundException;
}
