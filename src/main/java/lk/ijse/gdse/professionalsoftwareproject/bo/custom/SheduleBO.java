package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SheduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SheduleBO extends SuperBO {

    public boolean saveShedule(SheduleDTO shedule) throws SQLException, ClassNotFoundException;
    public boolean updateShedule(SheduleDTO shedule) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getOrderIds() throws SQLException, ClassNotFoundException;
    public String getNextSheduleId() throws SQLException, ClassNotFoundException;
    public ArrayList<SheduleDTO> getAllShedules() throws SQLException, ClassNotFoundException;



}
