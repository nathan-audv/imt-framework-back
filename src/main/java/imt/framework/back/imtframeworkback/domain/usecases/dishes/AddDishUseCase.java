package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.DishServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddDishUseCase implements UseCase<DishReq, Void> {
    private final DishServiceImpl dishService;

    @Override
    public Void command (DishReq dishReq) {
        dishService.save(Dish.fromReq(dishReq));
        return null;
    }
}
