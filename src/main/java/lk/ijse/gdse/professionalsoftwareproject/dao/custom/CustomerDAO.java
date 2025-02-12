package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import lk.ijse.gdse.professionalsoftwareproject.dao.CrudDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {

    public int findOutBirthdayCount(String date) throws SQLException;



}
