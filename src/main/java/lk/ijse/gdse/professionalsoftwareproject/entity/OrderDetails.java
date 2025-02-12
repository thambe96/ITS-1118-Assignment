package lk.ijse.gdse.professionalsoftwareproject.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {

    private String productId;
    private String orderId;
    private int qty;
    private double price;



}
