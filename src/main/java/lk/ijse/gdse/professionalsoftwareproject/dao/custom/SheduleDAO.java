package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import lk.ijse.gdse.professionalsoftwareproject.dao.CrudDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dto.SheduleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SheduleDAO extends CrudDAO<SheduleDTO> {

    public int findSheduleCount(String date) throws SQLException;



}
