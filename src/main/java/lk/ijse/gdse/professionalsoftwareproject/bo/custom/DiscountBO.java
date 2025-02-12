package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dto.DiscountDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DiscountBO extends SuperBO {

    public ArrayList<DiscountDTO> getAllDiscountDetails() throws SQLException, ClassNotFoundException;
    public boolean saveDiscountDetails(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException;
    public boolean updateDiscountDetails(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException;
    public DiscountDTO findDiscount(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteDiscountDetails(String id) throws SQLException, ClassNotFoundException;
    public String getNextDiscountId() throws SQLException, ClassNotFoundException;






}
