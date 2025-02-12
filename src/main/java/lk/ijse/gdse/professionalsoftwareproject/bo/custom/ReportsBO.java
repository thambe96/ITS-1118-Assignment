package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;

import java.sql.SQLException;
import java.util.Map;

public interface ReportsBO extends SuperBO {

    public Map<String, Integer> getProductSellingQuantity() throws SQLException;



}
