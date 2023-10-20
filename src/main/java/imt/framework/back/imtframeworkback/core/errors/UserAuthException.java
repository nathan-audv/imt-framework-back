package imt.framework.back.imtframeworkback.core.errors;

public class UserAuthException extends RuntimeException {
    public UserAuthException(String user) {
        super("User " + user + " auth exception");
    }
}