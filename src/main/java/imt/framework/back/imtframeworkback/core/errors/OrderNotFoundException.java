package imt.framework.back.imtframeworkback.core.errors;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Integer orderId){
        super("Order " + orderId + " not found");
    }
}
