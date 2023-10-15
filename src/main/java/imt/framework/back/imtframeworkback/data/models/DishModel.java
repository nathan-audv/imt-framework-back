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
    @Id
    @GeneratedValue
    @Column(name = "dish_id")
    private Integer dishId;
    private String image;
    private String title;
    private String description;
    private Double price;
    @ElementCollection
    private List<String> categories;
    @ElementCollection
    private List<String> allergens;

    public static DishModel fromDomain(Dish dish) {
        return DishModel.builder()
                .dishId(dish.getId())
                .image(dish.getImage())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .categories(dish.getCategories())
                .allergens(dish.getAllergens())
                .build();
    }
}
