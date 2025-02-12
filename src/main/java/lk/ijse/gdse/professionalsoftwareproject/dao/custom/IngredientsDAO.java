package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import lk.ijse.gdse.professionalsoftwareproject.dao.CrudDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dto.IngredientsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientsDAO extends CrudDAO<IngredientsDTO> {

    public ArrayList<String> getSupplierIds() throws SQLException;
    public ArrayList<String> getAllSupplierNames(ArrayList<String> supplierIds) throws SQLException;

}
