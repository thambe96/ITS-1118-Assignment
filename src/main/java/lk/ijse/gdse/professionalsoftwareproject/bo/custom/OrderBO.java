package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;
    public String getNextOrderID() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllCustomerIDs() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllProductIDs() throws SQLException, ClassNotFoundException;
}
