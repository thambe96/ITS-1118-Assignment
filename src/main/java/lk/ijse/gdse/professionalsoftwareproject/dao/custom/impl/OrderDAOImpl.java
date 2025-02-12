package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDAO;
import lk.ijse.gdse.professionalsoftwareproject.db.DBConnection;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<OrdersDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet list = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<OrdersDTO> orderList = new ArrayList<>();
        while (list.next()) {
            OrdersDTO order = new OrdersDTO();
            order.setOrderId(list.getString(1));
            order.setCustomerId(list.getString(2));
            order.setDate(list.getString(3));
            order.setTotalAmountSpend(list.getInt(4));
            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public String getNextId() throws SQLException {
        ResultSet rstOrderId = SQLUtil.execute("select orderId from orders order by orderId desc  limit 1");
        if (rstOrderId.next()) {
            String productId = rstOrderId.getString(1);
            String subString = productId.substring(2);
            int value = Integer.parseInt(subString) + 1;
            return String.format("OR%03d", value);
        } else {
            return "OR001";
        }
    }


    public boolean save(OrdersDTO ordersDTO) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isOrderDTOSaved = SQLUtil.execute("insert into orders values (?, ?, ?, ?)",
                ordersDTO.getOrderId(),
                ordersDTO.getDate(),
                ordersDTO.getCustomerId(),
                ordersDTO.getTotalAmountSpend()
            );

            if (isOrderDTOSaved) {
                boolean isOrderDetailsSaved = new OrderDetialsDAOImpl().save(ordersDTO.getOrderDetails());
                if (isOrderDetailsSaved) {
                    connection.commit();
                    return true;
                }

            }

            connection.rollback();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }




    }

    @Override
    public boolean update(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrdersDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public int findTodaysOrders(String date) throws SQLException {
        int count = 0;
        ResultSet rstOrdCount = SQLUtil.execute("select count(*) from orders where date = ? group by date;", date);
        if (rstOrdCount.next()) {
            count = Integer.parseInt(rstOrdCount.getString(1));
        }

        return count;
    }

    public Map<String, Integer> findEligibleCustomersOnSpend(double totOfSpend) throws SQLException {

       // ResultSet resultSet = CrudUtil.execute(, totOfSpend);
        String sql = "Select customerId,sum(totalAmountSpent)from orders group by customerId having sum(totalAmountSpent) > ? order by sum(totalAmountSpent) limit 3";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, totOfSpend);
        ResultSet resultSet = preparedStatement.executeQuery();




        Map<String, Integer> eligibleCustomers = new HashMap<String, Integer>();

        while (resultSet.next()) {
            eligibleCustomers.put(resultSet.getString(1), resultSet.getInt(2));
        }

        return eligibleCustomers;
    }




}
