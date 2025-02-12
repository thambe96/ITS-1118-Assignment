package lk.ijse.gdse.professionalsoftwareproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ProductDTO {

    private String productId;
    private String productName;
    private String discountId;
    private Double price;
    private int qtyOnHand;
    private String recipeId;


}
