package lk.ijse.gdse.professionalsoftwareproject.controller;

// lk.ijse.gdse.professionalsoftwareproject.controller.DiscountDetialsController
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.DiscountBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.DiscountDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.DiscountTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.DISCOUNT_BO;

public class DiscountDetialsController implements Initializable {

    @FXML
    private Label lblDiscountId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDiscount;

    @FXML
    private Button btnRcpSave;

    @FXML
    private Button btnRcpUpdate;

    @FXML
    private Button btnRcpDelete;

    @FXML
    private Button btnRcpReset;

    @FXML
    private TableView<DiscountTM> tblDicount;

    @FXML
    private TableColumn<?, ?> colDiscountId;

    @FXML
    private TableColumn<?, ?> colDiscountDesc;

    @FXML
    private TableColumn<?, ?> colDiscount;


    DiscountBO discountBO = (DiscountBO) BOFactory.getBoFactory().getBO(DISCOUNT_BO);                                 //new DiscountBOImpl();


    @FXML
    void btnDeleteDiscountDetails(ActionEvent event) throws SQLException {
        String id = lblDiscountId.getText();
        try {
            boolean isDeleted = discountBO.deleteDiscountDetails(id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnResetDescountDetails(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreDiscountPage();
    }

    @FXML
    void btnSaveDiscount(ActionEvent event) {

        String discount = txtDiscount.getText();
        String description = txtDescription.getText();
        double amount = Double.parseDouble(txtDiscount.getText());

        DiscountDTO discountDTO = new DiscountDTO(discount, description, amount);
        boolean isDiscountSaved = false;
        try {
            try {
                isDiscountSaved = discountBO.saveDiscountDetails(discountDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (isDiscountSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Discount Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Discount Not Saved").show();
        }


    }

    @FXML
    void btnUpdateDiscountDetails(ActionEvent event) {

        String discount = txtDiscount.getText();
        String description = txtDescription.getText();
        double amount = Double.parseDouble(txtDiscount.getText());

        DiscountDTO discountDTO = new DiscountDTO(discount, description, amount);
        boolean isDiscountSaved = false;
        try {
            try {
                isDiscountSaved = discountBO.updateDiscountDetails(discountDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (isDiscountSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Discount updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Discount Not updated").show();
        }


    }

    @FXML
    void onClickTable(MouseEvent event) {
        DiscountTM discountTM = tblDicount.getSelectionModel().getSelectedItem();
        lblDiscountId.setText(discountTM.getDiscountId());
        txtDescription.setText(discountTM.getDescription());
        txtDiscount.setText(String.valueOf(discountTM.getDiscount()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCellValues();
        try {
            try {
                refreDiscountPage();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void initializeCellValues() {
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        colDiscountDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    private void loadCellData() throws SQLException, ClassNotFoundException {
        ArrayList<DiscountDTO> discountDTOS = discountBO.getAllDiscountDetails();
        ObservableList<DiscountTM> discountTMS = FXCollections.observableArrayList();

        for (DiscountDTO discountDTO : discountDTOS) {
            DiscountTM discountTM = new DiscountTM(
                    discountDTO.getDiscountId(),
                    discountDTO.getDescription(),
                    discountDTO.getDiscount()
            );
            discountTMS.add(discountTM);
        }
        tblDicount.setItems(discountTMS);
    }


    private void refreDiscountPage() throws SQLException, ClassNotFoundException {
        try {
            lblDiscountId.setText(discountBO.getNextDiscountId());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtDescription.setText("");
        txtDiscount.setText("");
        loadCellData();

    }



}
