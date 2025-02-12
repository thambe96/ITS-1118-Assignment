package lk.ijse.gdse.professionalsoftwareproject.dao;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.CustomerDAO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ?
                daoFactory = new DAOFactory() :
                daoFactory;
    }

    public enum DAOType {
        CUSTOMER, PRODUCT, ORDER, ORDER_DETAIL, REPORT, DISCOUNT, INGREDIENT, RECIPE, SHEDULE, SUPPLIER;
    }

    public SuperDAO getDAO(DAOType type) {

        return switch (type) {
            case CUSTOMER -> new CustomerDAOImpl();
            case PRODUCT -> new ProductDAOImpl();
            case ORDER -> new OrderDAOImpl();
            case ORDER_DETAIL -> new OrderDetialsDAOImpl();
            case REPORT -> new ReportsDAOImpl();
            case DISCOUNT -> new DiscountDAOImpl();
            case INGREDIENT -> new IngredientsDAOImpl();
            case RECIPE -> new RecipeDAOImpl();
            case SHEDULE -> new SheduleDAOImpl();
            case SUPPLIER -> new SupplierDAOImpl();
            default -> null;
        };

    }






}
