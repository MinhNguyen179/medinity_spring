package minh_demo.demo.exceptions;

public class StudentNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public StudentNotFoundException(String message) {
        super(message);
    }
}