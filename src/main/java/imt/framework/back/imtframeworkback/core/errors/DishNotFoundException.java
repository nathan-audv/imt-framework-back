package imt.framework.back.imtframeworkback.core.errors;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Integer dishId) {
        super("Dish " + dishId + " not found");
    }
}
