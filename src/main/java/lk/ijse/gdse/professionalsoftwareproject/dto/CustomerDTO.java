package lk.ijse.gdse.professionalsoftwareproject.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class CustomerDTO {

    private String customerId;
    private String customerName;
    private String dob;
    private String address;
    private String email;


}
