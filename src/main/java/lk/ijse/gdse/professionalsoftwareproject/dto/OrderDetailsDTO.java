package lk.ijse.gdse.professionalsoftwareproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailsDTO {

    private String productId;
    private String orderId;
    private int qty;
    private double price;

}
