package lk.ijse.gdse.professionalsoftwareproject.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    private String paymentId;
    private String date;
    private double amount;
    private String status;
    private String ordId;


}
