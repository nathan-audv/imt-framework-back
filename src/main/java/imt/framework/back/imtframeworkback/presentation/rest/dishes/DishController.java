package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import imt.framework.back.imtframeworkback.domain.usecases.AddDishUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DishController implements DishResources {
    private final AddDishUseCase addDishUseCase;

    @Override
    public void createDish (String image, String title, String description, Double price, String category) {
        addDishUseCase.command(DishReq.builder()
                .image(image)
                .title(title)
                .description(description)
                .price(price)
                .category(category)
                .build());

    }
}
