package imt.framework.back.imtframeworkback.domain.usecases;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.DishServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDishUseCase implements UseCase<DishReq, Void> {
    @Autowired
    private DishServiceImpl dishService;

    @Override
    public Void command (DishReq dishReq) {
        dishService.save(Dish.fromReq(dishReq));
        return null;
    }
}
