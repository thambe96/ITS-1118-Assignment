package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ReportsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportsDAOImpl implements ReportsDAO {

    public Map<String, Integer> getProductSellingQty() throws SQLException {

        ResultSet rs = SQLUtil.execute("select productId, count(*) from orderdetails group by productId;");
        Map<String, Integer> productSellingQty = new HashMap<>();
        while (rs.next()) {
            productSellingQty.put(rs.getString(1), rs.getInt(2));
        }
        return productSellingQty;

    }



}
