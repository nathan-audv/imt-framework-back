package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@RequestMapping("/v1/dishes")
public interface DishResources {

    @GetMapping
    Set<Dish> getDishes(
            @RequestParam(required = false) List<String> categoryFilter,
            @RequestParam(required = false) String searchFilter
    );

    @PostMapping
    Dish createDish(@RequestParam String image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam List<String> categories, @RequestParam List<String> allergens);
}
