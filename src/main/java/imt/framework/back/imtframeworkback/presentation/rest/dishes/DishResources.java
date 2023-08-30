package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/dishes")
public interface DishResources {

    @PostMapping
    public void createDish(@RequestParam String image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam String Category);
}
