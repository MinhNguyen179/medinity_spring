package minh_demo.demo.exceptions;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public ItemNotFoundException(String message) {
        super(message);
    }
}