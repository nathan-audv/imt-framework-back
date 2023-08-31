package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/dishes")
public interface DishResources {

    @GetMapping
    public void getDishes();
    @PostMapping
    public void createDish(@RequestParam String image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam String Category);
}
