package converters;

import dto.CustomerDto;
import entities.Customer;
import org.springframework.stereotype.Component;
import java.util.function.Function;

/**
 * Created by szypows_local on 27.10.2018.
 */
@Component
public class CustomerConverter implements Function<CustomerDto, Customer> {

    @Override
    public Customer apply(CustomerDto customerDto) {
        Customer customer = new Customer();
        return customer;
    }
}
