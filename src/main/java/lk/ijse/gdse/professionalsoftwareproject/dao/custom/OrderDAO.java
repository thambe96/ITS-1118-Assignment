package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.gdse.professionalsoftwareproject.dao.CrudDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.db.DBConnection;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface OrderDAO extends CrudDAO<OrdersDTO> {


    public int findTodaysOrders(String date) throws SQLException;
    public Map<String, Integer> findEligibleCustomersOnSpend(double totOfSpend) throws SQLException;


}
