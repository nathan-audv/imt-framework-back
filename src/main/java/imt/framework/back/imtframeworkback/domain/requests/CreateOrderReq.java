package imt.framework.back.imtframeworkback.domain.requests;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
@Builder
public class CreateOrderReq {
    List<OrderLineReq> orderLines;
    Integer userId;
    Optional<String> address;
    String note;
}

