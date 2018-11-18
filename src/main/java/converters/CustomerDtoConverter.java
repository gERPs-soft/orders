package converters;

import dto.CustomerDto;
import entities.Customer;
import org.springframework.stereotype.Component;
import java.util.function.Function;

/**
 * Created by szypows_local on 27.10.2018.
 */
@Component
public class CustomerDtoConverter implements Function<Customer, CustomerDto> {

    @Override
    public CustomerDto apply(Customer customer) {
        CustomerDto customerdto = new CustomerDto();
        return customerdto;
    }
}
