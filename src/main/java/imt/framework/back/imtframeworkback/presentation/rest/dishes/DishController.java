package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import imt.framework.back.imtframeworkback.domain.usecases.AddDishUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishController implements DishResources {
    @Autowired
    private AddDishUseCase addDishUseCase;

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
