package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.ReportsBO;
import lk.ijse.gdse.professionalsoftwareproject.bo.impl.ReportsBOImpl;
import lk.ijse.gdse.professionalsoftwareproject.db.DBConnection;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ReportsDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.REPORT_BO;

public class ReportController implements Initializable {

    @FXML
    private PieChart pieChrtProductSelllingQtys;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUpPieChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button btnProductReport;

    @FXML
    private LineChart<?, ?> productMovingChart;


    ReportsBO reportsBO = (ReportsBO) BOFactory.getBoFactory().getBO(REPORT_BO);                                       //new ReportsBOImpl();

    @FXML
    void generateProductReport(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/Blank_A4_Test.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }



    }

    private void setUpPieChart() throws SQLException {

//        Map<String, Integer> productSellingQty = new ReportsDAOImpl().getProductSellingQty();

        Map<String, Integer> productSellingQty = reportsBO.getProductSellingQuantity();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : productSellingQty.entrySet()) {
            //System.out.println("Product: " + entry.getKey() + ", Quantity Sold: " + entry.getValue());
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));

        }

        pieChrtProductSelllingQtys.setData(pieChartData);
        pieChrtProductSelllingQtys.setTitle("Product Selling Quantities");


    }







}
