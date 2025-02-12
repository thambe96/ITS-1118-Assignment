package lk.ijse.gdse.professionalsoftwareproject.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private String productId;
    private String productName;
    private String discountId;
    private double price;
    private int qtyOnHand;
    private String recipeId;



}
