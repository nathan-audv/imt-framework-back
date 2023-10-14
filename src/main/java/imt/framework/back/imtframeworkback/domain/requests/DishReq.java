package imt.framework.back.imtframeworkback.domain.requests;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DishReq {
    String image;
    String title;
    String description;
    Double price;
    String category;
    List<String> allergens;
}
