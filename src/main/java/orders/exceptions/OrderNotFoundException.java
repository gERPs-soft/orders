package orders.exceptions;

/**
 * Created by szypows_local on 26.11.2018.
 */
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
