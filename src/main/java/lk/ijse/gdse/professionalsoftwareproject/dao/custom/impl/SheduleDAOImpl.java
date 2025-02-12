package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.SheduleDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SheduleDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SheduleDAOImpl implements SheduleDAO {

    public String getNextId() throws SQLException {

        ResultSet rs = SQLUtil.execute("select sheduleId " +
                "from shedule " +
                "order by sheduleId " +
                "limit 1"
        );

        if (rs.next()) {
            String sheduleId = rs.getString(1);
            String subString = sheduleId.substring(2);
            int value = Integer.parseInt(subString) + 1;
            return String.format("SH%03d", value);
        } else {
            return "SH001";
        }

    }

    public ArrayList<String> getOrderIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select orderId from orders");
        ArrayList<String> orderIds = new ArrayList<>();
        while (resultSet.next()) {
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }


    public boolean save(SheduleDTO sheduleDTO)  {

        boolean result = false;

        try {
            result = SQLUtil.execute("insert into shedule values(?, ?, ?, ?)",
                        sheduleDTO.getSheduleId(),
                        sheduleDTO.getExpectedDate(),
                        sheduleDTO.getStatus(),
                        sheduleDTO.getOrderId()
                    );
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                System.out.println("Error: Unique constraint violation. The value already exists.");
            } else {
                // Handle other SQL exceptions
                System.out.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
            }

        }


        return result;
    }


    public boolean update(SheduleDTO sheduleDTO) throws SQLException {
        return SQLUtil.execute("update shedule set status =  ? where sheduleId = ?",
                sheduleDTO.getStatus(),
                sheduleDTO.getSheduleId()
                );
    }

    @Override
    public SheduleDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    public boolean deleteShedule(String sheduleId) throws SQLException {
        return SQLUtil.execute("delete from shedule where sheduleId = ?", sheduleId);
    }


    public ArrayList<SheduleDTO> getAll() throws SQLException {
        ResultSet rs = SQLUtil.execute("select * from shedule");
        ArrayList<SheduleDTO> sheduleDTOs = new ArrayList<>();
        while (rs.next()) {
            SheduleDTO sheduleDTO = new SheduleDTO();
            sheduleDTO.setSheduleId(rs.getString(1));
            sheduleDTO.setExpectedDate(rs.getString(2));
            sheduleDTO.setStatus(rs.getString(3));
            sheduleDTO.setOrderId(rs.getString(4));
            sheduleDTOs.add(sheduleDTO);

        }
        return sheduleDTOs;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public int findSheduleCount(String date) throws SQLException {
        ResultSet rst = SQLUtil.execute("select count(*) from shedule where expectedDate = ? group by expectedDate", date);
        if (rst.next()) {
            return Integer.parseInt(rst.getString(1));
        }
        return 0;
    }
























}
