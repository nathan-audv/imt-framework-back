package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/dishes")
public interface DishResources {

    @GetMapping
    public void getDishes();
    @PostMapping
    public void createDish(@RequestParam String image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam String category, @RequestParam List<String> allergens);
}
