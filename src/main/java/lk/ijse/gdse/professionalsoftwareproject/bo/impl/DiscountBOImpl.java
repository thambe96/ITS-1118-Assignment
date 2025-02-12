package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.DiscountBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.DiscountDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.DiscountDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.DISCOUNT;

public class DiscountBOImpl implements DiscountBO {

    //DiscountDAO discountDAO = new DiscountDAOImpl();


    DiscountDAO discountDAO = (DiscountDAO) DAOFactory.getDaoFactory().getDAO(DISCOUNT);

    @Override
    public ArrayList<DiscountDTO> getAllDiscountDetails() throws SQLException, ClassNotFoundException {
        return discountDAO.getAll();
    }

    @Override
    public boolean saveDiscountDetails(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException {
        return discountDAO.save(discountDTO);
    }

    @Override
    public boolean updateDiscountDetails(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException {
        return discountDAO.update(discountDTO);
    }

    @Override
    public DiscountDTO findDiscount(String id) throws SQLException, ClassNotFoundException {
        return discountDAO.find(id);
    }

    @Override
    public boolean deleteDiscountDetails(String id) throws SQLException, ClassNotFoundException {
        return discountDAO.delete(id);
    }

    @Override
    public String getNextDiscountId() throws SQLException, ClassNotFoundException {
        return discountDAO.getNextId();
    }
}
