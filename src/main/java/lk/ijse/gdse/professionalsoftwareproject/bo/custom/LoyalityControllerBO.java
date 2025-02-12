package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;

import java.sql.SQLException;
import java.util.Map;

public interface LoyalityControllerBO extends SuperBO {

    public Map<String, Integer> findLoyalityEligibleListOnTotalSpending(double totalSpend) throws SQLException;

}
