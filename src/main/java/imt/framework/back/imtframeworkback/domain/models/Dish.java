package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.DishModel;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Dish {
    Integer id;
    String image;
    String title;
    String description;
    Double price;
    String category;

    public static Dish fromReq(DishReq dish){
        return Dish.builder()
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .build();
    }
}
