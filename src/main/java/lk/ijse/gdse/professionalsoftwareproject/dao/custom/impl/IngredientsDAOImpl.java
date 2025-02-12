package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.IngredientsDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.IngredientsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientsDAOImpl implements IngredientsDAO {


    public ArrayList<String> getSupplierIds() throws SQLException {

        ResultSet rstSupIds = SQLUtil.execute("select suplierId from supplier");
        ArrayList<String> supplierIds = new ArrayList<>();
        while (rstSupIds.next()) {
            supplierIds.add(rstSupIds.getString(1));
        }
        return supplierIds;
    }

    public boolean save(IngredientsDTO ingredientsDTO) throws SQLException {
        return SQLUtil.execute("insert into ingredient values (?, ?, ?, ?)",
                    ingredientsDTO.getIngredientId(),
                    ingredientsDTO.getIngredientName(),
                    ingredientsDTO.getSupplierId(),
                    ingredientsDTO.getQuantity()
                );
    }

    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("select ingredientId from ingredient order by ingredientId desc limit 1");
        System.out.println("This is ingredient Id generation method");
        if (rst.next()) {
            System.out.println(rst.getString(1));
            String id = rst.getString(1);
            String subString = id.substring(1);
            int val = Integer.parseInt(subString) + 1;
            return String.format("I%03d", val);
        } else {
            return "I001";
        }
    }


    public ArrayList<IngredientsDTO> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from ingredient");
        ArrayList<IngredientsDTO> ingredientsDTOs = new ArrayList<>();
        while (rst.next()) {
            IngredientsDTO ingredientsDTO = new IngredientsDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
            ingredientsDTOs.add(ingredientsDTO);
        }
        return ingredientsDTOs;
    }

    public ArrayList<String> getAllSupplierNames(ArrayList<String> supIds) throws SQLException {
        ArrayList<String> supplierNames = new SupplierDAOImpl().getSupplierNames(supIds);
        return supplierNames;
    }


    public boolean update(IngredientsDTO ingredientsDTO) throws SQLException {
        return SQLUtil.execute("update ingredient set qty = ? where ingredientId = ?",
                ingredientsDTO.getQuantity(),
                ingredientsDTO.getIngredientId()
        );
    }

    @Override
    public IngredientsDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String ingredientId) throws SQLException {
        return SQLUtil.execute("delete from ingredient where ingredientId = ?", ingredientId);
    }


}
