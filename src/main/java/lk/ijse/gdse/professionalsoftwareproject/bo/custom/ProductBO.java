package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {


    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(String prdId) throws SQLException, ClassNotFoundException;
    public String getNextProductId() throws SQLException, ClassNotFoundException;
    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public ProductDTO findProduct(String prdId) throws SQLException, ClassNotFoundException;




}
