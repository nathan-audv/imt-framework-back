package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import imt.framework.back.imtframeworkback.domain.requests.GetDishesReq;
import imt.framework.back.imtframeworkback.domain.usecases.dishes.AddDishUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.dishes.GetDishesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DishController implements DishResources {
    private final AddDishUseCase addDishUseCase;
    private final GetDishesUseCase getDishesUseCase;

    @Override
    public Set<Dish> getDishes(List<String> categoryFilter, String searchFilter) {
        return getDishesUseCase.command(GetDishesReq.builder()
                .categoryFilter(categoryFilter)
                .searchFilter(searchFilter)
                .build());
    }

    @Override
    public Dish createDish(String image, String title, String description, Double price, List<String> categories, List<String> allergens) {
        return addDishUseCase.command(DishReq.builder()
                .image(image)
                .title(title)
                .description(description)
                .price(price)
                .categories(categories)
                .allergens(allergens)
                .build());
    }
}
