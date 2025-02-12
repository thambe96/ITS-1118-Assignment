package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.RecipeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecipeBO extends SuperBO {

    public ArrayList<RecipeDTO> getAllRecipes() throws SQLException, ClassNotFoundException;
    public RecipeDTO findRecipe(String id) throws SQLException, ClassNotFoundException;
    public boolean saveRecipe(RecipeDTO recipeDTO) throws SQLException, ClassNotFoundException;
    public boolean updateRecipe(RecipeDTO recipeDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteRecipe(String id) throws SQLException, ClassNotFoundException;
    public String getNextRecipeID() throws SQLException, ClassNotFoundException;




}
