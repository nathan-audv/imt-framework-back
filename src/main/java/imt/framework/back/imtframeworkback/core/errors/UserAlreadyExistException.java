package imt.framework.back.imtframeworkback.core.errors;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
