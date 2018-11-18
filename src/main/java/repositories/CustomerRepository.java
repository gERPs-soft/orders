package repositories;

import entities.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
