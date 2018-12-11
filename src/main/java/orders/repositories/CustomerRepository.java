package orders.repositories;

import orders.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by szypows_local on 18.11.2018.
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByIdAndActiveTrue(Long id);

    List<Customer> findAllByActiveTrue();

}
