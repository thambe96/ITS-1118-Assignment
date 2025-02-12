package lk.ijse.gdse.professionalsoftwareproject.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Orders {

    private String orderId;
    private String date;
    private String customerId;
    private double totalAmountSpent;



}
