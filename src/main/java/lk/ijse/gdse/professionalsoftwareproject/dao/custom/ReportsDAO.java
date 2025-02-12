package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dao.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface ReportsDAO extends SuperDAO {

    public Map<String, Integer> getProductSellingQty() throws SQLException;

}
