package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.DiscountDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.DiscountDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscountDAOImpl implements DiscountDAO {


    public ArrayList<DiscountDTO> getAll() throws SQLException {
        ResultSet rstDicountDetails = SQLUtil.execute("select * from discount");
        ArrayList<DiscountDTO> discountDTOs = new ArrayList<>();

        while (rstDicountDetails.next()) {
            DiscountDTO discountDTO = new DiscountDTO(
                    rstDicountDetails.getString(1),
                    rstDicountDetails.getString(2),
                    rstDicountDetails.getDouble(3)
            );
            discountDTOs.add(discountDTO);
        }
        return discountDTOs;
    }

    public boolean save(DiscountDTO discountDTO) throws SQLException {

        return SQLUtil.execute("insert into discount values (?, ?, ?)",
                    discountDTO.getDiscountId(),
                    discountDTO.getDescription(),
                    discountDTO.getDiscount()
                );
    }


    public boolean update(DiscountDTO discountDTO) throws SQLException {
        return SQLUtil.execute("update discount set description = ?, amount = ?",
                    discountDTO.getDiscountId(),
                    discountDTO.getDescription(),
                    discountDTO.getDiscount()
                );
    }

    @Override
    public DiscountDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from discount where discountId = ?", id);
    }

    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select discountId from discount order by discountId desc limit 1");
        if (resultSet.next()) {
            String discountId = resultSet.getString(1);
            String subString = discountId.substring(1);
            int val = Integer.parseInt(subString) + 1;
            return String.format("D%03d", val);
        } else {
            return "D001";
        }
    }


































}
