package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.SupplierDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SupplierDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    public ArrayList<SupplierDTO> getAll() throws SQLException {

        ResultSet resultSetSpplierIds = SQLUtil.execute("select * from supplier");
        ArrayList<SupplierDTO> suppliers = new ArrayList<>();
        while (resultSetSpplierIds.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    resultSetSpplierIds.getString(1),
                    resultSetSpplierIds.getString(2),
                    resultSetSpplierIds.getString(3)
            );
            suppliers.add(supplierDTO);
        }
        return suppliers;
    }

    public boolean save(SupplierDTO supplierDTO) throws SQLException {
        return SQLUtil.execute("insert into supplier values(?, ?, ?)",
                supplierDTO.getSupplierId(),
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierMobile()
        );
    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException {
        return SQLUtil.execute("update supplier set suplierName = ?, mobile = ? where suplierId = ?",
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierMobile(),
                supplierDTO.getSupplierId()
        );
    }

    @Override
    public SupplierDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String suplierId) throws SQLException {
        return SQLUtil.execute("delete from supplier where suplierId = ?", suplierId);
    }

    public ArrayList<String> getSupplierNames(ArrayList<String> supIds) throws SQLException {

        ArrayList<String> supplierNamesList = new ArrayList<>();
        for (String supId : supIds) {
            ResultSet rstSupName = SQLUtil.execute("select suplierName from supplier where suplierId = ?", supId);
            if (rstSupName.next()) {
                supplierNamesList.add(rstSupName.getString(1));
            }
        }

        return supplierNamesList;
    }


    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select suplierId from supplier " +
                "order by suplierId " +
                "desc limit 1"
        );

        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String subString = supplierId.substring(1);
            int val = Integer.parseInt(subString) + 1;
            return String.format("S%03d", val);
        } else {
            return "S001";
        }


    }


}
