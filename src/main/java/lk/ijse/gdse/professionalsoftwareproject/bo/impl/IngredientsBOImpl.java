package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.IngredientsBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.IngredientsDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.IngredientsDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.IngredientsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.INGREDIENT;

public class IngredientsBOImpl implements IngredientsBO {


    //IngredientsDAO ingredientsDAO = new IngredientsDAOImpl();

    IngredientsDAO ingredientsDAO = (IngredientsDAO) DAOFactory.getDaoFactory().getDAO(INGREDIENT);


    @Override
    public ArrayList<IngredientsDTO> getAllIngredients() throws SQLException, ClassNotFoundException {
        return ingredientsDAO.getAll();
    }

    @Override
    public String getNextIngredientID() throws SQLException, ClassNotFoundException {
        return ingredientsDAO.getNextId();
    }

    @Override
    public boolean saveIngredient(IngredientsDTO ingredient) throws SQLException, ClassNotFoundException {
        return ingredientsDAO.save(ingredient);
    }

    @Override
    public boolean deleteIngredient(String ingredientID) throws SQLException, ClassNotFoundException {
        return ingredientsDAO.delete(ingredientID);
    }

    @Override
    public boolean updateIngredient(IngredientsDTO ingredient) throws SQLException, ClassNotFoundException {
        return ingredientsDAO.update(ingredient);
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException {
        return ingredientsDAO.getSupplierIds();
    }

    @Override
    public ArrayList<String> getAllSupplirNames(ArrayList<String> supplierIds) throws SQLException {
        return ingredientsDAO.getAllSupplierNames(supplierIds);
    }
}
