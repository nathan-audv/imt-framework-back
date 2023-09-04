package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.Dish;

import java.util.List;

public interface DishService {
     Dish save(Dish dish);
     List<Dish> findAll();
}
