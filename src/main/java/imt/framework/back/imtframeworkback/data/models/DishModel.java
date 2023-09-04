package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> allergens;

    public static DishModel fromDomain(Dish dish){
        return DishModel.builder()
                .id(dish.getId())
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .allergens(dish.getAllergens())
                .build();
    }
}
