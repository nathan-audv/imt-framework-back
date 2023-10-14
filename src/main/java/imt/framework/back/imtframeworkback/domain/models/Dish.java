package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.DishModel;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Dish {
    Integer id;
    String image;
    String title;
    String description;
    Double price;
    List<String> categories;
    List<String> allergens;

    public static Dish fromReq(DishReq dish) {
        return Dish.builder()
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .categories(dish.getCategories())
                .allergens(dish.getAllergens())
                .build();
    }

    public static Dish fromData(DishModel dish) {
        return Dish.builder()
                .id(dish.getDishId())
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .categories(dish.getCategories())
                .allergens(dish.getAllergens())
                .build();
    }
}
