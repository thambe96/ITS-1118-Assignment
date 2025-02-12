package lk.ijse.gdse.professionalsoftwareproject.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CartTM {

    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private Button cartButton;



}
