package lk.ijse.gdse.professionalsoftwareproject.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrdersDTO {

    private String orderId;
    private String date;
    private String customerId;
    private double totalAmountSpend;
    private ArrayList<OrderDetailsDTO> orderDetails;

}
