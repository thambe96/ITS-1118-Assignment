package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.RecipeBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.RecipeDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.RecipeDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.RecipeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.RECIPE;

public class RecipeBOImpl implements RecipeBO {

    RecipeDAO recipeDAO = (RecipeDAO) DAOFactory.getDaoFactory().getDAO(RECIPE);                           //new RecipeDAOImpl();

    @Override
    public ArrayList<RecipeDTO> getAllRecipes() throws SQLException, ClassNotFoundException {
        return recipeDAO.getAll();
    }

    @Override
    public RecipeDTO findRecipe(String id) throws SQLException, ClassNotFoundException {
        return recipeDAO.find(id);
    }

    @Override
    public boolean saveRecipe(RecipeDTO recipeDTO) throws SQLException, ClassNotFoundException {
        return recipeDAO.save(recipeDTO);
    }

    @Override
    public boolean updateRecipe(RecipeDTO recipeDTO) throws SQLException, ClassNotFoundException {
        return recipeDAO.update(recipeDTO);
    }

    @Override
    public boolean deleteRecipe(String id) throws SQLException, ClassNotFoundException {
        return recipeDAO.delete(id);
    }

    @Override
    public String getNextRecipeID() throws SQLException, ClassNotFoundException {
        return recipeDAO.getNextId();
    }
}
