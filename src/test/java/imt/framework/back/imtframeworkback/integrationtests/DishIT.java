package imt.framework.back.imtframeworkback.integrationtests;

import imt.framework.back.imtframeworkback.data.models.DishModel;
import imt.framework.back.imtframeworkback.data.repositories.DishRepository;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.presentation.rest.dishes.DishController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class DishIT {
    @Autowired
    DishController dishController;

    @Autowired
    DishRepository dishRepository;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.13");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        dishRepository.deleteAll();
    }

    @Nested
    class Get {
        @Test
        void getDishesShouldReturnEmptyThem() {
            dishRepository.save(new DishModel());
            dishRepository.save(new DishModel());

            Set<Dish> res = dishController.getDishes(null, null).getBody();

            assertThat(res).hasSize(2);
        }

        @Test
        void getDishesEmptyDBShouldReturnEmptyList() {
            Set<Dish> res = dishController.getDishes(null, null).getBody();
            List<Dish> actual = dishRepository.findAll().stream().map(Dish::fromData).toList();

            assertThat(res).isEmpty();
            assertThat(actual).isEmpty();
        }
    }

    @Nested
    class Create {
        @Test
        void createDishShouldReturnItself() {
            Dish res = dishController.createDish("image", "title", "description", 1.1, List.of("test"), List.of("allergen1")).getBody();
            DishModel actual = dishRepository.findAll().get(0);

            assertThat(res.getId()).isEqualTo(actual.getDishId());
        }
    }
}
