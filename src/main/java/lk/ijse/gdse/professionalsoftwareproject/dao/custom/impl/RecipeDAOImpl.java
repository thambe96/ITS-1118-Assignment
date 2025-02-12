package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.RecipeDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.RecipeDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAOImpl implements RecipeDAO {


    public ArrayList<RecipeDTO> getAll() throws SQLException {

        ResultSet rstRecipies = SQLUtil.execute("select * from recipe");
        ArrayList<RecipeDTO> recipes = new ArrayList<>();
        while (rstRecipies.next()) {
            RecipeDTO recipeDTO = new RecipeDTO(
                    rstRecipies.getString(1),
                    rstRecipies.getString(2),
                    rstRecipies.getString(3)
            );
            recipes.add(recipeDTO);
        }
        return recipes;
    }

    public boolean save(RecipeDTO recipeDTO) throws SQLException {
        return SQLUtil.execute("insert into recipe values(?, ?, ?)",
                recipeDTO.getRecipeId(),
                recipeDTO.getRecipeName(),
                recipeDTO.getRecipeDescription()
        );
    }

    public boolean update(RecipeDTO recipeDTO) throws SQLException {

        /*
        System.out.println(recipeDTO.getRecipeName());
        System.out.println( recipeDTO.getRecipeDescription());
        System.out.println(recipeDTO.getRecipeId()); */

        return SQLUtil.execute("update recipe set recipeName = ?, description = ? where recipeId = ?",
                recipeDTO.getRecipeName(),
                recipeDTO.getRecipeDescription(),
                recipeDTO.getRecipeId()
        );

    }

    @Override
    public RecipeDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String recipeId) throws SQLException {
        return SQLUtil.execute("delete from recipe where recipeId = ?", recipeId);
    }

    public String getNextId() throws SQLException {

        ResultSet rstRecipe = SQLUtil.execute("select recipeId from recipe " +
                "order by recipeId " +
                "desc limit 1"
        );

        if (rstRecipe.next()) {
            String recipeId = rstRecipe.getString(1);
            String subString = recipeId.substring(1);
            int val = Integer.parseInt(subString) + 1;
            return String.format("R%03d", val);
        } else {
            return "R001";
        }

    }


}
