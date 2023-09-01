package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.DishServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDishesUseCase implements UseCase<Void, List<Dish>> {
    private final DishServiceImpl dishService;

    @Override
    public List<Dish> command (Void unused) {
        return dishService.findAll();
    }
}
