package imt.framework.back.imtframeworkback.presentation.rest.favorites;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/favorites")
public interface FavoriteResources {
    @GetMapping
    List<Dish> getFavorites(@RequestParam Integer userId);

    @PutMapping
    void updateFavorites(@RequestParam Integer userId, @RequestParam Integer dishId);
}
