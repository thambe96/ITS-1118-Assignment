package lk.ijse.gdse.professionalsoftwareproject.view.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductTM {

    private String productId;
    private String productName;
    private String discountId;
    private Double price;
    private int qtyOnHand;
    private String recipeId;

}
