package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.LoyalityControllerBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDAO;

import java.sql.SQLException;
import java.util.Map;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.ORDER;

public class LoyalityControllerBOImpl implements LoyalityControllerBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(ORDER);


    @Override
    public Map<String, Integer> findLoyalityEligibleListOnTotalSpending(double totalSpend) throws SQLException {
        return orderDAO.findEligibleCustomersOnSpend(totalSpend);
    }
}
