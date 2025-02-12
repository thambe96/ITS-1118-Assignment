package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.ReportsBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.ReportsDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ReportsDAOImpl;

import java.sql.SQLException;
import java.util.Map;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.REPORT;

public class ReportsBOImpl implements ReportsBO {

    ReportsDAO reportsDAO = (ReportsDAO)DAOFactory.getDaoFactory().getDAO(REPORT);                         //new ReportsDAOImpl();


    @Override
    public Map<String, Integer> getProductSellingQuantity() throws SQLException {
        return reportsDAO.getProductSellingQty();
    }
}
