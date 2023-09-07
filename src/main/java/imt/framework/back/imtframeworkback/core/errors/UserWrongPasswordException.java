package imt.framework.back.imtframeworkback.core.errors;

public class UserWrongPasswordException extends RuntimeException{
    public UserWrongPasswordException(String mail) {
        super("User " + mail + " password not match");
    }
}