package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.IngredientsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientsBO extends SuperBO {


    public ArrayList<IngredientsDTO> getAllIngredients() throws SQLException, ClassNotFoundException;
    public String getNextIngredientID() throws SQLException, ClassNotFoundException;
    public boolean saveIngredient(IngredientsDTO ingredient) throws SQLException, ClassNotFoundException;
    public boolean deleteIngredient(String ingredientID) throws SQLException, ClassNotFoundException;
    public boolean updateIngredient(IngredientsDTO ingredient) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllSupplierIds() throws SQLException;
    public ArrayList<String> getAllSupplirNames(ArrayList<String> supplierIds) throws SQLException;





}
