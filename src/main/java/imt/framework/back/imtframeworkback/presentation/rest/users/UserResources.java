package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/users")
@Tag(name = "User")
public interface UserResources {
    @PostMapping
    @Operation(summary = "Create new user with unique mail")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exist"
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "User created"
            )
    })
    ResponseEntity<Void> createUser(@RequestBody CreateUserReq createUserReq);

    @PostMapping("/auth")
    @Operation(summary = "Authenticate, returns user and JWT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticate",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRes.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Authentication error"
            ),
    })
    UserRes authenticateUser(@RequestBody AuthUserReq authUserReq);

    @PutMapping
    @Operation(summary = "Update user's information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    User updateUser(
            @RequestParam Integer userId,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestBody(required = false) String password
    );
}
