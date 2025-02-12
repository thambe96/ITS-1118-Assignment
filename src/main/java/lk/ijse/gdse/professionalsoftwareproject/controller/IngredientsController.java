package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.IngredientsBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.IngredientsDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.IngredientsTM;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.INGREDIENT_BO;

public class IngredientsController implements Initializable {


    IngredientsBO ingredientsBO = (IngredientsBO) BOFactory.getBoFactory().getBO(INGREDIENT_BO);                                       //new IngredientsBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setIngredientsTableCellValueFactory();

        try {
            refreshIngredientPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label lblIngId;

    @FXML
    private HBox txtIngredientId;

    @FXML
    private TextField txtIngName;

    @FXML
    private ComboBox<Integer> cmbIngQty;

    @FXML
    private ComboBox<String> cmbSupId;

    @FXML
    private TableView<IngredientsTM> tblIngredient;

    @FXML
    private TableColumn<IngredientsTM, String> colIngId;

    @FXML
    private TableColumn<IngredientsTM, String> colIngName;

    @FXML
    private TableColumn<IngredientsTM, String> colSupID;

    @FXML
    private TableColumn<IngredientsTM, String> colSupName;

    @FXML
    private TableColumn<IngredientsTM, Integer> colIngQty;

    @FXML
    private Button btnIngSave;

    @FXML
    private Button btnIngUpdate;

    @FXML
    private Button btnIngDelete;

    @FXML
    private Button btnIngReset;

    @FXML
    void ingDeleteOnAction(ActionEvent event) throws SQLException {
        String ingId = lblIngId.getText();
        boolean isIngredientDeleted = false;
        try {
            isIngredientDeleted = ingredientsBO.deleteIngredient(ingId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (isIngredientDeleted) {
            refreshIngredientPage();
            new Alert(Alert.AlertType.INFORMATION, "Ingredient deleted").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Ingredient not deleted").show();
        }


    }

    @FXML
    void ingResetOnAction(ActionEvent event) throws SQLException {
        refreshIngredientPage();
    }

    @FXML
    void ingSaveOnAction(ActionEvent event) throws SQLException {

        // when no supplier is added (supplier combox is empty) handle it here

        IngredientsDTO ingredientsDTO = new IngredientsDTO(
                lblIngId.getText(),
                txtIngName.getText(),
                cmbSupId.getSelectionModel().getSelectedItem(),
                cmbIngQty.getSelectionModel().getSelectedItem()
        );

        boolean isIngDetailsSaved = false;
        try {
            isIngDetailsSaved = ingredientsBO.saveIngredient(ingredientsDTO);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isIngDetailsSaved) {
            refreshIngredientPage();
            new Alert(Alert.AlertType.INFORMATION, "Ingredient Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Ingredient Not Saved").show();
        }

    }

    @FXML
    void ingUpdateOnAction(ActionEvent event) throws SQLException {

        IngredientsDTO ingredientsDTO = new IngredientsDTO(
                lblIngId.getText(),
                txtIngName.getText(),
                cmbSupId.getSelectionModel().getSelectedItem(),
                cmbIngQty.getSelectionModel().getSelectedItem()
        );

        boolean isIngredientUpdated = false;
        try {
            isIngredientUpdated = ingredientsBO.updateIngredient(ingredientsDTO);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (isIngredientUpdated) {
            refreshIngredientPage();
            new Alert(Alert.AlertType.INFORMATION, "Ingredient Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Ingredient Not Updated").show();
        }

    }


    private void loadSupplierIdToComBox() throws SQLException {
        ObservableList<String> observableListSupIds = FXCollections.observableArrayList();


        // fix line 172
        ArrayList<String> supIds = ingredientsBO.getAllSupplierIds();
        for (String supId : supIds) {
            observableListSupIds.add(supId);
        }
        cmbSupId.setItems(observableListSupIds);

    }

    private void loadIngredientsQuantityToComboBox() throws SQLException {

        ObservableList<Integer> observableListIngQts= FXCollections.observableArrayList();
        //ArrayList<Integer> qts = new ArrayList<>(100);
//        for (int i: qts) {
//            observableListIngQts.add(i);
//        }
        for (int i = 0; i < 100; i++) {
            observableListIngQts.add(i);
        }
        cmbIngQty.setItems(observableListIngQts);

    }

    @SneakyThrows
    private void refreshIngredientPage() throws SQLException {

        lblIngId.setText(ingredientsBO.getNextIngredientID());
        txtIngName.setText("");
        cmbSupId.getSelectionModel().clearSelection();
        cmbIngQty.getSelectionModel().clearSelection();

        loadSupplierIdToComBox();
        loadIngredientsQuantityToComboBox();
        loadIngrediensTable();
    }


    private void loadIngrediensTable() throws SQLException {

        ArrayList<IngredientsDTO> ingredientsDTOS = null;
        try {
            ingredientsDTOS = ingredientsBO.getAllIngredients();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> supIds = new ArrayList<>();

        for (IngredientsDTO ingredientsDTO : ingredientsDTOS) {
            supIds.add(ingredientsDTO.getSupplierId());
        }


        // fix line 225
        //ArrayList<String> supNames = new IngredientsDAOImpl().getAllSupplierNames(supIds);
        ArrayList<String> supNames = ingredientsBO.getAllSupplirNames(supIds);


        ArrayList<IngredientsTM> ingredientsTMs = new ArrayList<>();

        int i = 0;
        for (IngredientsDTO ingredientsDTO : ingredientsDTOS) {
            IngredientsTM ingredientsTM = new IngredientsTM(
                    ingredientsDTO.getIngredientId(),
                    ingredientsDTO.getIngredientName(),
                    ingredientsDTO.getSupplierId(),
                    supNames.get(i),
                    ingredientsDTO.getQuantity()
            );
            ingredientsTMs.add(ingredientsTM);
            i++;
        }

        ObservableList<IngredientsTM> observaleListIngredientTM= FXCollections.observableArrayList();
        observaleListIngredientTM.addAll(ingredientsTMs);
        tblIngredient.setItems(observaleListIngredientTM);

    }


    private void setIngredientsTableCellValueFactory () {
        colIngId.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));
        colIngName.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colIngQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    void onClickedTblIngredients(MouseEvent event) {

        IngredientsTM ingredientsTM = (IngredientsTM) tblIngredient.getSelectionModel().getSelectedItem();
        lblIngId.setText(ingredientsTM.getIngredientId());
        txtIngName.setText(ingredientsTM.getIngredientName());
        cmbSupId.setValue(ingredientsTM.getSupplierId());
        cmbIngQty.setValue(ingredientsTM.getQuantity());

        System.out.println("onClickedTblIngredients Table");

    }


}
