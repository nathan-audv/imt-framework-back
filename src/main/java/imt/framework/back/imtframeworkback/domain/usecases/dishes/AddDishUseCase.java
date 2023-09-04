package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.DishServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddDishUseCase implements UseCase<DishReq, Dish> {
    private final DishServiceImpl dishService;

    @Override
    public Dish command(DishReq dishReq) {
        return dishService.save(Dish.fromReq(dishReq));
    }
}
