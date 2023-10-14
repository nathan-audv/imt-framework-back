package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.data.models.DishModel;
import imt.framework.back.imtframeworkback.data.repositories.DishRepository;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Override
    public Dish save(Dish dish) {
        return Dish.fromData(dishRepository.save(DishModel.fromDomain(dish)));
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll().stream().map(Dish::fromData).toList();
    }

    @Override
    public Optional<Dish> findById(Integer id) {
        return dishRepository.findById(id).map(Dish::fromData);
    }
}
