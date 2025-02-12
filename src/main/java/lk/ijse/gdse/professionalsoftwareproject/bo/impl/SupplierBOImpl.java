package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.SupplierBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.SupplierDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.SUPPLIER;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO)DAOFactory.getDaoFactory().getDAO(SUPPLIER);                                                   //new SupplierDAOImpl();


    @Override
    public String getNextSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextId();
    }

    @Override
    public boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierID);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierDTO);
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(supplierDTO);
    }
}
