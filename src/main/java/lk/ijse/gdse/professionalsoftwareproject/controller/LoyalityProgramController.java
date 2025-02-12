package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.LoyalityControllerBO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.LoyalityProgramTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.LOYALITY_CONTROLLER_BO;

public class LoyalityProgramController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPropertyValuesTblLoyality();
    }

    @FXML
    private TextField txtAmountSpent;

    @FXML
    private TableView<LoyalityProgramTM> tblLoyality;

    @FXML
    private TableColumn<LoyalityProgramTM, String> colCustName;

    @FXML
    private TableColumn<LoyalityProgramTM, Double> colAmountSpent;




    @FXML
    private Button btnLoyalitySearch;


    LoyalityControllerBO loyalityControllerBO = (LoyalityControllerBO) BOFactory.getBoFactory().getBO(LOYALITY_CONTROLLER_BO);

    @FXML
    void findLoyalityPrgEligibleList(ActionEvent event) throws SQLException {
        findLoyalityPrgEligibleCustomers();
    }



    private void findLoyalityPrgEligibleCustomers() throws SQLException {

        double totalSpend = Double.parseDouble(txtAmountSpent.getText());

        ObservableList<LoyalityProgramTM> obsrLoyalityLst = FXCollections.observableArrayList();

        Map<String, Integer> elibibleCustomers = loyalityControllerBO.findLoyalityEligibleListOnTotalSpending(totalSpend);                                //new OrderDAOImpl().findEligibleCustomersOnSpend(totalSpend);

        for (Map.Entry<String, Integer> entry : elibibleCustomers.entrySet()) {
            LoyalityProgramTM loyalityProgramTM = new LoyalityProgramTM(
                    entry.getKey(),
                    entry.getValue()
            );
            obsrLoyalityLst.add(loyalityProgramTM);
        }

        tblLoyality.setItems(obsrLoyalityLst);
    }


    private void setPropertyValuesTblLoyality() {
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colAmountSpent.setCellValueFactory(new PropertyValueFactory<>("totalSpent"));
    }



}
