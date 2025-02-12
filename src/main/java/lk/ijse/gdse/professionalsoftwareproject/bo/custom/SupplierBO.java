package lk.ijse.gdse.professionalsoftwareproject.bo.custom;

import lk.ijse.gdse.professionalsoftwareproject.bo.SuperBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {


    public String getNextSupplierID() throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;





}
