package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.DashboardControllerBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.CustomerDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ProductDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.SheduleDAO;

import java.sql.SQLException;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.*;

public class DashboardControllerBOImpl implements DashboardControllerBO {


    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(ORDER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(PRODUCT);
    SheduleDAO sheduleDAO = (SheduleDAO) DAOFactory.getDaoFactory().getDAO(SHEDULE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(CUSTOMER);


    @Override
    public int setupTodaysOrderCount(String date) throws SQLException {
        return orderDAO.findTodaysOrders(date);
    }

    @Override
    public int setupRunningOutProductCount() throws SQLException {
        return productDAO.findRunningOutProductCount();
    }

    @Override
    public int setupTodaysSheduleCount(String date) throws SQLException {
        return sheduleDAO.findSheduleCount(date);
    }

    @Override
    public int findBirthdayCount(String date) throws SQLException {
        return customerDAO.findOutBirthdayCount(date);
    }
}
