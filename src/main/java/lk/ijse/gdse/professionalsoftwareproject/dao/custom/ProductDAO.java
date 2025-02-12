package lk.ijse.gdse.professionalsoftwareproject.dao.custom;

import lk.ijse.gdse.professionalsoftwareproject.dao.CrudDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<ProductDTO> {

    public int findRunningOutProductCount() throws SQLException;


}
