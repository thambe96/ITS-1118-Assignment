package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ProductDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrderDetailsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    public ArrayList<String> getDicountIds() throws SQLException {
        ArrayList<String> dicountIds = new ArrayList<>();
        ResultSet rstDicountIds = SQLUtil.execute("select discountId from discount");
        while (rstDicountIds.next()) {
            dicountIds.add(rstDicountIds.getString(1));
        }
        return dicountIds;
    }

    public ArrayList<String> getRecipeIds() throws SQLException {
        ArrayList<String> recipeIds = new ArrayList<>();
        ResultSet rstRecipeIds = SQLUtil.execute("select recipeId from recipe");
        while (rstRecipeIds.next()) {
            recipeIds.add(rstRecipeIds.getString(1));
        }
        return recipeIds;
    }

    public ArrayList<ProductDTO> getAll() throws SQLException {
        ArrayList<ProductDTO> productDTOs = new ArrayList<>();
        ResultSet rstProducts = SQLUtil.execute("select * from product");
        while (rstProducts.next()) {
            ProductDTO productDTO = new ProductDTO(
                                                    rstProducts.getString(1),
                                                    rstProducts.getString(2),
                                                    rstProducts.getString(3),
                                                    rstProducts.getDouble(4),
                                                    rstProducts.getInt(5),
                                                    rstProducts.getString(6)
                                                 );
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public boolean save(ProductDTO productDTO) throws SQLException {

        System.out.println("This is the error" +productDTO.getRecipeId());


        return SQLUtil.execute("insert into product values (?, ?, ?, ?, ?, ?)",
                                    productDTO.getProductId(),
                                    productDTO.getProductName(),
                                    productDTO.getDiscountId(),
                                    productDTO.getPrice(),
                                    productDTO.getQtyOnHand(),
                                    productDTO.getRecipeId()
                                );
    }

    public boolean update(ProductDTO productDTO) throws SQLException {
        return SQLUtil.execute("update product set " +
                                    "productName = ?, " +
                                    "dicountId = ?, " +
                                    "price = ?, " +
                                    "qtyOnHand = ?, " +
                                    "recipeId = ?" +
                                    "where productId = ?",
                                    productDTO.getProductName(),
                                    productDTO.getDiscountId(),
                                    productDTO.getPrice(),
                                    productDTO.getQtyOnHand(),
                                    productDTO.getRecipeId(),
                                    productDTO.getProductId()
                                );
    }


    public boolean delete(String productId) throws SQLException {
        return SQLUtil.execute("delete from product where productId = ?", productId);
    }

    public String getNextId() throws SQLException {
        ResultSet rstProductId = SQLUtil.execute("select productId from product order by productId desc  limit 1");
        if (rstProductId.next()) {
            String productId = rstProductId.getString(1);
            String subString = productId.substring(1);
            int value = Integer.parseInt(subString) + 1;
            return String.format("P%03d", value);
        } else {
            return "P001";
        }
    }


    /*
    public ArrayList<String> getAllProductIds() throws SQLException {
        ArrayList<String> productIds = new ArrayList<>();
        ResultSet rstProductIds = CrudUtil.execute("select productId from product");
        while (rstProductIds.next()) {
            productIds.add(rstProductIds.getString(1));
        }
        return productIds;
    }
        */

    public ProductDTO find(String productId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from product where productId = ?", productId);
        if (resultSet.next()) {
            ProductDTO productDTO = new ProductDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4), resultSet.getInt(5),
                    resultSet.getString(6)
            );
            return productDTO;
        }
        return null;
    }

    public boolean reduceProductQuantity(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        return SQLUtil.execute("update product set qtyOnHand = qtyOnHand - ? where productId = ? ",
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getProductId()
        );
    }

    public int findRunningOutProductCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select count(*) from product where qtyOnHand < 5");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }



}
