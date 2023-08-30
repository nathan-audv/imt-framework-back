package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.data.models.DishModel;
import imt.framework.back.imtframeworkback.data.repositories.DishRepository;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Override
    public void save (Dish dish) {
        dishRepository.save(DishModel.fromDomain(dish));
    }
}
