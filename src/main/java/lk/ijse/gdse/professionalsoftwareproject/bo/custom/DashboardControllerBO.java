package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardControllerBO extends SuperBO {

    public int setupTodaysOrderCount(String date) throws SQLException;
    public int setupRunningOutProductCount() throws SQLException;
    public int setupTodaysSheduleCount(String date) throws SQLException;
    public int findBirthdayCount(String date) throws SQLException;

}
