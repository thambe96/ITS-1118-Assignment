package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.DashboardControllerBO;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ProductDAOImpl;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.SheduleDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.DASHBOARD_BO;

public class DeshboardController implements Initializable {


    @FXML
    private AnchorPane content;

    @FXML
    private Label lblNewProducts;

    @FXML
    private Label lblShedule;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblProductsRunningOut;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    private String date;

    @FXML
    private Button btnRefresh;

    DashboardControllerBO dashboardControllerBO = (DashboardControllerBO) BOFactory.getBoFactory().getBO(DASHBOARD_BO);


    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingUpTimeAndDate();
        try {
            refreshDashboardPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void settingUpTimeAndDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        date = currentDate.format(formatter);

        lblDate.setText(currentDate.toString());
        LocalTime currentTime = LocalTime.now();
        lblTime.setText(currentTime.toString());
    }

    private void settingUPTodaysOrderCount() throws SQLException {
        int count = dashboardControllerBO.setupTodaysOrderCount(lblDate.getText());                                                 //new OrderDAOImpl().findTodaysOrders(lblDate.getText());
        String orderCount = Integer.toString(count);
        lblSales.setText(orderCount);
    }

    private void settingUpRunningOutOfProductCount() throws SQLException {
        lblProductsRunningOut.setText(Integer.toString(

                dashboardControllerBO.setupRunningOutProductCount()
//                new ProductDAOImpl().
//                        findRunningOutProductCount()
                )
        );
    }

    private void settingShedulecount() throws SQLException {
        lblShedule.setText(Integer.toString(
                dashboardControllerBO.setupTodaysSheduleCount(lblDate.getText())
                /*new SheduleDAOImpl().findSheduleCount(lblDate.getText())*/
        ));
    }

    private void settingUpBirthDayCount() throws SQLException {
        lblNewProducts.setText(Integer.toString(

                dashboardControllerBO.findBirthdayCount(lblDate.getText())
               // new CustomerDAOImpl().findOutBirthdayCount(date)

                ));
    }

    @FXML
    void refreshDashBoardOnAction(ActionEvent event) throws SQLException {
        refreshDashboardPage();
    }

    private void refreshDashboardPage() throws SQLException {
        settingUPTodaysOrderCount();
        settingUpRunningOutOfProductCount();
        settingShedulecount();
        settingUpBirthDayCount();
    }


}
