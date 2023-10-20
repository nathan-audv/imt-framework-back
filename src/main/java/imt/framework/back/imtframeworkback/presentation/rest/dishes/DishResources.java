package imt.framework.back.imtframeworkback.presentation.rest.dishes;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@RequestMapping("/v1/dishes")
public interface DishResources {

    @GetMapping
    @Operation(summary = "Get all dishes with or without filter")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get dishes",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Dish.class))}
            )
    })
    ResponseEntity<Set<Dish>> getDishes(
            @RequestParam(required = false) List<String> categoryFilter,
            @RequestParam(required = false) String searchFilter
    );

    @PostMapping
    @Operation(summary = "Create dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Dish created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Dish.class))}
            )
    })
    ResponseEntity<Dish> createDish(@RequestParam String image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam List<String> categories, @RequestParam List<String> allergens);
}
