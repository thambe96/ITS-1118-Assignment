package lk.ijse.gdse.professionalsoftwareproject.bo;

import lk.ijse.gdse.professionalsoftwareproject.bo.custom.CustomerBO;
import lk.ijse.gdse.professionalsoftwareproject.bo.impl.*;

public class BOFactory {


    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ?
                boFactory = new BOFactory() :
                boFactory;
    }

    public enum BOType {
        CUSTOMER_BO, DISCOUNT_BO, INGREDIENT_BO, ORDER_BO,
        PRODUCT_BO, RECIPE_BO, REPORT_BO, SHEDULE_BO, SUPPLIER_BO, DASHBOARD_BO
        , LOYALITY_CONTROLLER_BO;
    }

    public SuperBO getBO(BOType type) {
        return switch (type) {
            case CUSTOMER_BO -> new CustomerBOImpl();
            case DISCOUNT_BO -> new DiscountBOImpl();
            case INGREDIENT_BO -> new IngredientsBOImpl();
            case ORDER_BO -> new OrderBOImpl();
            case PRODUCT_BO -> new ProductBOImpl();
            case RECIPE_BO -> new RecipeBOImpl();
            case SHEDULE_BO -> new SheduleBOImpl();
            case SUPPLIER_BO -> new SupplierBOImpl();
            case REPORT_BO -> new ReportsBOImpl();
            case DASHBOARD_BO -> new DashboardControllerBOImpl();
            case LOYALITY_CONTROLLER_BO -> new LoyalityControllerBOImpl();
            default -> null;
        };
    }





}
