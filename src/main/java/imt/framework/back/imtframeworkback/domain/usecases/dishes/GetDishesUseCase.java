package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDishesUseCase implements UseCase<Void, List<Dish>> {
    private final DishService dishService;
    private final TokenService tokenService;

    @Override
    public List<Dish> command(Void request) {
        System.out.println(tokenService.getMail());
        return dishService.findAll();
    }
}
