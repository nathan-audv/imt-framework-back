package imt.framework.back.imtframeworkback.core.errors;

public class UserNotValidException extends RuntimeException {
    public UserNotValidException() {
        super("User is not valid");
    }
}
