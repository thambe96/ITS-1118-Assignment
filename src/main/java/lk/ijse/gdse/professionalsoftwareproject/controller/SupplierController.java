package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.SupplierBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SupplierDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.SupplierTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.SUPPLIER_BO;

public class SupplierController implements Initializable {


    @FXML
    private Label lblSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private Button btnSupplierReset;


    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<SupplierTM, String> supplierId;

    @FXML
    private TableColumn<SupplierTM, String> supplierName;

    @FXML
    private TableColumn<SupplierTM, String> supplierMobile;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(SUPPLIER_BO);                                                         //new SupplierBOImpl();



    @FXML
    void supplierDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this supplier.....!!",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isSupplierDeleted = supplierBO.deleteSupplier(lblSupplierId.getText());
            if (isSupplierDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Supplier deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Supplier could not be deleted").show();
            }
        }
    }

    @FXML
    void supplierSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        SupplierDTO supplierDTO = new SupplierDTO(
                lblSupplierId.getText(),
                txtSupplierName.getText(),
                txtMobileNumber.getText()
        );

        boolean isSaved = supplierBO.saveSupplier(supplierDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Saved Successfully.......!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Supplier Saving Failed.......!!").show();
        }

    }

    @FXML
    void supplierUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        boolean isUpdated = supplierBO.updateSupplier(new SupplierDTO(
                lblSupplierId.getText(),
                txtSupplierName.getText(),
                txtMobileNumber.getText())
        );

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Updated Successfully.......!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Supplier Update Failed.......!!").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        SupplierTM supplierTM = (SupplierTM) tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTM != null) {
            lblSupplierId.setText(supplierTM.getSupplierId());
            txtSupplierName.setText(supplierTM.getSupplierName());
            txtMobileNumber.setText(supplierTM.getSupplierMobile());

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValuesTblSupplier();

        try {
            refreshSupplierTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    //SupplierDAOImpl supplierDAOImpl = new SupplierDAOImpl();

    private void loadSupplierTable() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = supplierBO.getAllSuppliers();
        ObservableList<SupplierTM> supplierTMObservableList = FXCollections.observableArrayList();
        for (SupplierDTO supplierDTO : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getSupplierName(),
                    supplierDTO.getSupplierMobile()
            );
            supplierTMObservableList.add(supplierTM);
        }
        tblSupplier.setItems(supplierTMObservableList);

    }

    private void setCellValuesTblSupplier() {
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierMobile.setCellValueFactory(new PropertyValueFactory<>("supplierMobile"));
    }

    private void refreshSupplierTable() throws SQLException, ClassNotFoundException {
        lblSupplierId.setText(supplierBO.getNextSupplierID());
        txtSupplierName.setText("");
        txtMobileNumber.setText("");
        loadSupplierTable();
    }

    @FXML
    void supplierResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshSupplierTable();
    }




}
