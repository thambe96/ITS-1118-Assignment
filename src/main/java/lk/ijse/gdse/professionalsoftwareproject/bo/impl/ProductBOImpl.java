package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.ProductBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ProductDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ProductDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.PRODUCT;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(PRODUCT);                //new ProductDAOImpl();


    @Override
    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }

    @Override
    public boolean deleteProduct(String prdId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(prdId);
    }

    @Override
    public String getNextProductId() throws SQLException, ClassNotFoundException {
        return productDAO.getNextId();
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(productDTO);
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(productDTO);
    }

    @Override
    public ProductDTO findProduct(String prdId) throws SQLException, ClassNotFoundException {
        return productDAO.find(prdId);
    }
}
