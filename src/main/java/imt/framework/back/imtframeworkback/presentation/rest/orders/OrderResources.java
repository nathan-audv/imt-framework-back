package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderDetailsRes;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderResources {
    @PostMapping
    @Operation(summary = "Create order for a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderRes.class))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dish not found\t\nUser not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User hasn't enough money"
            )
    })
    ResponseEntity<OrderRes> createOrder(
            @RequestParam(required = false) String address,
            @RequestParam Integer userId,
            @RequestParam(required = false) String note,
            @RequestBody List<OrderLineReq> orderLines);

    @GetMapping
    @Operation(summary = "Get user's orders")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of user's orders",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderRes.class)))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not valid"
            )
    })
    List<OrderRes> getOrderHistory(@RequestParam Integer userId);

    @GetMapping("/details")
    @Operation(summary = "Get user's order details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get user's order details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailsRes.class))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })
    OrderDetailsRes getOrderDetails(@RequestParam Integer orderId);

    @DeleteMapping
    @Operation(summary = "Delete user order")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Get user's order details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailsRes.class))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "User not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })
    ResponseEntity<OrderRes> deleteOrder(@RequestParam Integer orderId);
}
