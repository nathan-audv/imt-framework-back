package imt.framework.back.imtframeworkback.domain.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateFavoritesReq {
    Integer userId;
    Integer dishId;
}
