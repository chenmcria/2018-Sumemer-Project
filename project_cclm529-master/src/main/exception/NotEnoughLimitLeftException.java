package exception;

public class NotEnoughLimitLeftException extends Exception {
    public NotEnoughLimitLeftException(String message) {
        super(message);
    }
}
