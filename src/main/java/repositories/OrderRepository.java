package repositories;

import entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
