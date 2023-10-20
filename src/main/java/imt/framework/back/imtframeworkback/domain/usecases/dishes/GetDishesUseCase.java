package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.GetDishesReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class GetDishesUseCase implements UseCase<GetDishesReq, Set<Dish>> {
    private final DishService dishService;

    @Override
    public Set<Dish> command(GetDishesReq request) {
        String search = request.getSearchFilter();
        Set<String> catFilter = request.getCategoryFilter() != null ?
                new HashSet<>(request.getCategoryFilter()
                        .stream()
                        .map(String::toLowerCase)
                        .toList()) :
                Set.of();

        return dishService.findAll().stream().filter(dish -> {
            boolean searchBool = search == null ||
                    search.isEmpty() ||
                    Stream.of(search.split(" "))
                            .map(String::toLowerCase)
                            .allMatch((dish.getTitle().toLowerCase() + dish.getDescription().toLowerCase())::contains);
            boolean catBool = catFilter.isEmpty() ||
                    dish.getCategories()
                            .stream()
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet())
                            .containsAll(catFilter);
            return searchBool && catBool;
        }).collect(Collectors.toSet());
    }
}
