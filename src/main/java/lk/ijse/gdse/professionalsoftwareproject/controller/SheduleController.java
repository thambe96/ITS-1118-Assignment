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
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.SheduleBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.SheduleDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.SheduleTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.SHEDULE_BO;

public class SheduleController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setSheduleTblCellValues();


        try {
            nxtSheduleId();
            loadCmbxOrderId();
            refreshShedulePage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        loadCmbxStatus();
    }




    @FXML
    private Label lblSheduleId;

    @FXML
    private ComboBox<String> cmbOrderId;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private DatePicker dteShedulePicker;

    @FXML
    private Button btnSheduleSave;

    @FXML
    private Button btnSheduleUpdate;

    @FXML
    private Button btnSheduleDelete;

    @FXML
    private Button btnResetShedule;

    @FXML
    private TableView<SheduleTM> tblShedule;

    @FXML
    private TableColumn<SheduleTM, String> colSheduleId;

    @FXML
    private TableColumn<SheduleTM, String> colExpectedDate;

    @FXML
    private TableColumn<SheduleTM, String> colStatus;

    @FXML
    private TableColumn<SheduleTM, String> colOrderId;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    SheduleBO sheduleBO = (SheduleBO) BOFactory.getBoFactory().getBO(SHEDULE_BO);                                                   //new SheduleBOImpl();


    @FXML
    void delteSheduleOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {
        SheduleTM sheduleTM = (SheduleTM) tblShedule.getSelectionModel().getSelectedItem();
        lblSheduleId.setText(sheduleTM.getSheduleId());
        cmbStatus.setValue(sheduleTM.getStatus());
        cmbOrderId.setValue(sheduleTM.getOrderId());
        dteShedulePicker.setValue(LocalDate.parse(sheduleTM.getExpectedDate()));
    }

    @FXML
    void resetSheduleOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshShedulePage();
    }

    @FXML
    void saveSheduleOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        SheduleDTO shedule = new SheduleDTO(
                lblSheduleId.getText(),
                dteShedulePicker.getValue().format(formatter),
                cmbStatus.getValue(),
                cmbOrderId.getValue()
        );

        boolean isSheduleSaved = sheduleBO.saveShedule(shedule);

        if (isSheduleSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Shedule Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Shedule Not Saved").show();
        }



    }

    @FXML
    void updateSheduleOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        SheduleDTO sheduleDTO = new SheduleDTO(
                lblSheduleId.getText(),
                dteShedulePicker.getValue().format(formatter),
                cmbStatus.getValue(),
                cmbOrderId.getValue()
        );


        boolean isSheduleUPDATED = sheduleBO.updateShedule(sheduleDTO);

        if (isSheduleUPDATED) {
            new Alert(Alert.AlertType.INFORMATION, "Shedule Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Shedule Not Updated").show();
        }

    }

    private void loadCmbxStatus() {

        ArrayList<String> status = new ArrayList<>();
        status.add("processing");
        status.add("completed");

        ObservableList<String> observableListCmbStatus = FXCollections.observableArrayList();
        observableListCmbStatus.addAll(status);
        cmbStatus.setItems(observableListCmbStatus);

    }

    private void loadCmbxOrderId() throws SQLException, ClassNotFoundException {

        ArrayList<String> ordIds = sheduleBO.getOrderIds();
        ObservableList<String> observableListCmbOrdIs = FXCollections.observableArrayList();
        for (String ordId : ordIds) {
            observableListCmbOrdIs.add(ordId);
        }

        cmbOrderId.setItems(observableListCmbOrdIs);
    }



    private void nxtSheduleId() throws SQLException, ClassNotFoundException {
        lblSheduleId.setText(sheduleBO.getNextSheduleId());
    }

    private void refreshShedulePage() throws SQLException, ClassNotFoundException {
        nxtSheduleId();
        cmbStatus.getSelectionModel().clearSelection();
        cmbOrderId.getSelectionModel().clearSelection();
        dteShedulePicker.setValue(null);

        loadSheduleTbl();

    }


    private void setSheduleTblCellValues() {
        colSheduleId.setCellValueFactory(new PropertyValueFactory<>("sheduleId"));
        colExpectedDate.setCellValueFactory(new PropertyValueFactory<>("expectedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    }


    private void loadSheduleTbl() throws SQLException, ClassNotFoundException {
        ArrayList<SheduleDTO> sheduleDTOS = sheduleBO.getAllShedules();
        ObservableList<SheduleTM> observableListShedule = FXCollections.observableArrayList();


        for (SheduleDTO sheduleDTO : sheduleDTOS) {
            SheduleTM sheduleTM = new SheduleTM(
                    sheduleDTO.getSheduleId(),
                    sheduleDTO.getExpectedDate(),
                    sheduleDTO.getStatus(),
                    sheduleDTO.getOrderId()
            );
            observableListShedule.add(sheduleTM);
        }

        tblShedule.setItems(observableListShedule);

    }




}
