package lk.ijse.gdse.professionalsoftwareproject.bo.impl;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.SheduleBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.OrderDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.SheduleDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.SheduleDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SheduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.ORDER;
import static lk.ijse.gdse.professionalsoftwareproject.dao.DAOFactory.DAOType.SHEDULE;

public class SheduleBOImpl implements SheduleBO {

    OrderDAO orderDAO = (OrderDAO)DAOFactory.getDaoFactory().getDAO(ORDER);                                     //new OrderDAOImpl();
    SheduleDAO sheduleDAO = (SheduleDAO)DAOFactory.getDaoFactory().getDAO(SHEDULE);                                                         //new SheduleDAOImpl();


    @Override
    public boolean saveShedule(SheduleDTO sheduleDTO) throws SQLException, ClassNotFoundException {
        return sheduleDAO.save(sheduleDTO);
    }

    @Override
    public boolean updateShedule(SheduleDTO sheduleDTO) throws SQLException, ClassNotFoundException {
        return sheduleDAO.update(sheduleDTO);
    }

    @Override
    public ArrayList<String> getOrderIds() throws SQLException, ClassNotFoundException {

        ArrayList<OrdersDTO> orderList = orderDAO.getAll();
        ArrayList<String> orderIds = new ArrayList<>();

        for (OrdersDTO ordersDTO : orderList) {
            orderIds.add(ordersDTO.getOrderId());
        }

        return orderIds;
    }

    @Override
    public String getNextSheduleId() throws SQLException, ClassNotFoundException {
        return sheduleDAO.getNextId();
    }

    @Override
    public ArrayList<SheduleDTO> getAllShedules() throws SQLException, ClassNotFoundException {
        return sheduleDAO.getAll();
    }
}
