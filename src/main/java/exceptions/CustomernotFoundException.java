package exceptions;

/**
 * Created by szypows_local on 18.11.2018.
 */
public class CustomernotFoundException extends Exception {
    public CustomernotFoundException(String message) {
        super(message);
    }

    public CustomernotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
