package spring.exception;

public class EnquiryNotFoundException extends RuntimeException {
    public EnquiryNotFoundException(String message) {
        super(message);
    }
}
