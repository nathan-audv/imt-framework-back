package imt.framework.back.imtframeworkback.core.errors;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
