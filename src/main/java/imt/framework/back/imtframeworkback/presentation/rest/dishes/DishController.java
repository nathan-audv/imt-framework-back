package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import imt.framework.back.imtframeworkback.domain.usecases.dishes.AddDishUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.dishes.GetDishesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DishController implements DishResources {
    private final AddDishUseCase addDishUseCase;
    private final GetDishesUseCase getDishesUseCase;

    @Override
    public void getDishes ( ) {
        getDishesUseCase.command(null);
    }

    @Override
    public void createDish (String image, String title, String description, Double price, String category, List<String> allergens) {
        addDishUseCase.command(DishReq.builder()
                .image(image)
                .title(title)
                .description(description)
                .price(price)
                .category(category)
                .allergens(allergens)
                .build());

    }
}
