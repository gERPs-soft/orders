package orders.repositories;

import orders.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by szypows_local on 18.11.2018.
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
