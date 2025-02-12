package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.OrderBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.CustomerDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ProductDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ProductDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.db.DBConnection;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrderDetailsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.*;

public class OrderBOImpl implements OrderBO {


    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(ORDER);        //new OrderDAOImpl();
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(CUSTOMER);      //new CustomerDAOImpl();
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(PRODUCT);                 //new ProductDAOImpl();
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(ORDER_DETAIL);


    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {

        Connection connection = null;


        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = orderDAO.save(ordersDTO);

            System.out.println("Order DTO saved " + b1);

            if (!b1) {
                connection.rollback();
                return false;
            }

            ArrayList<OrderDetailsDTO> orderDetailsDTOS = ordersDTO.getOrderDetails();

            for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
                boolean b2 = orderDetailsDAO.save(orderDetailsDTO);
                if (!b2) {
                    connection.rollback();
                    return false;
                }
                boolean b3 = productDAO.reduceProductQuantity(orderDetailsDTO);
                if (!b3) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public String getNextOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }

    @Override
    public ArrayList<String> getAllCustomerIDs() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> customerList =  customerDAO.getAll();
        ArrayList<String> customerIDs = new ArrayList<>();
        for (CustomerDTO customerDTO : customerList) {
            customerIDs.add(customerDTO.getCustomerId());
        }
        return customerIDs;

    }

    @Override
    public ArrayList<String> getAllProductIDs() throws SQLException, ClassNotFoundException {

        ArrayList<ProductDTO> productList =  productDAO.getAll();
        ArrayList<String> productIDs = new ArrayList<>();
        for (ProductDTO productDTO : productList) {
            productIDs.add(productDTO.getProductId());
        }

        return productIDs;
    }


}
