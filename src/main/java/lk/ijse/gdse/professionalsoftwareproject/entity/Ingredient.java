package lk.ijse.gdse.professionalsoftwareproject.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ingredient {

    private String ingredientId;
    private String ingredientName;
    private String supplierId;
    private int qty;



}
