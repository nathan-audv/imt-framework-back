package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DishModel {
    @Id @GeneratedValue
    Integer id;
    String image;
    String title;
    String description;
    Double price;
    String category;

    public static DishModel fromDomain(Dish dish){
        return DishModel.builder()
                .id(dish.getId())
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .build();
    }
}
