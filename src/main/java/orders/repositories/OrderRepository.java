package orders.repositories;

import orders.entities.Order;
import orders.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByOrderStatusNotLike(OrderStatus orderStatus);

    Optional<Order> findByIdAndOrderStatusNotLike(Long id, OrderStatus orderStatus);


}
