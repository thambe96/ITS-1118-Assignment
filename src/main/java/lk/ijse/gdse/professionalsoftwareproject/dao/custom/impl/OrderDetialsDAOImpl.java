package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrderDetailsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetialsDAOImpl implements OrderDetailsDAO {

    public boolean save(OrderDetailsDTO orderDetailsDTO) throws SQLException {

        boolean flag = saveOrderDetails(orderDetailsDTO);

        return flag;
    }



    private boolean saveOrderDetails(OrderDetailsDTO orderDetails) throws SQLException {
        return SQLUtil.execute("insert into orderdetails values (?, ?, ?, ?)",
                orderDetails.getProductId(),
                orderDetails.getOrderId(),
                orderDetails.getQty(),
                orderDetails.getPrice()
        );
    }


    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

//    @Override
//    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
//        return false;
//    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailsDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
