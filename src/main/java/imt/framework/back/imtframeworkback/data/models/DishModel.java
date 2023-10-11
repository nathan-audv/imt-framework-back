package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    Integer dishId;
    String image;
    String title;
    String description;
    Double price;
    @ElementCollection
    List<String> categories;
    @ElementCollection
    List<String> allergens;

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
