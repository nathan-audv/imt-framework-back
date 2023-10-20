package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class CreateUserUseCaseTest {

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void creatingWhileUserAlreadyExistShouldThrowAnException(){
        String userEmail = "test@test.fr";
        User user = User.builder().mail(userEmail).build();
        Integer request = 0;
        CreateUserReq createUserReq = CreateUserReq.builder().mail(userEmail).build();

        Mockito.when(userService.findByMail(userEmail)).thenReturn(Optional.of(user));
        Assertions.assertThrows(UserAlreadyExistException.class, () -> createUserUseCase.command(createUserReq));

    }
}
