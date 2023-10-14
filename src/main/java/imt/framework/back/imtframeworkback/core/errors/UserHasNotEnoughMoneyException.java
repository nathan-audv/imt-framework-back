package imt.framework.back.imtframeworkback.core.errors;

public class UserHasNotEnoughMoneyException extends RuntimeException {
    public UserHasNotEnoughMoneyException(Integer user) {
        super("User " + user + " has not enough money to pay");
    }
}
