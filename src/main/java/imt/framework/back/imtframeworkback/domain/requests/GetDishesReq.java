package imt.framework.back.imtframeworkback.domain.requests;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetDishesReq {
    List<String> categoryFilter;
    String searchFilter;
}
