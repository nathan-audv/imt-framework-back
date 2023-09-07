package imt.framework.back.imtframeworkback.core.errors;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mail) {
        super("User " + mail + " not found");
    }
}
