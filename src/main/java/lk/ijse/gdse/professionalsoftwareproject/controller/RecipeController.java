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
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.RecipeBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.RecipeDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.RecipeTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.RECIPE_BO;

public class RecipeController implements Initializable {


    @FXML
    private Label lblRecipeId;

    @FXML
    private TextField txtRecipeName;

    @FXML
    private TextField txtRecipeDesc;

    @FXML
    private Button btnRcpSave;

    @FXML
    private Button btnRcpUpdate;

    @FXML
    private Button btnRcpDelete;

    @FXML
    private Button btnRcpReset;

    @FXML
    private TableView<RecipeTM> tblRecipe;

    @FXML
    private TableColumn<?, ?> colRecipeId;

    @FXML
    private TableColumn<?, ?> colRecipeName;

    @FXML
    private TableColumn<?, ?> colRecipeDescription;


    RecipeBO recipeBO = (RecipeBO) BOFactory.getBoFactory().getBO(RECIPE_BO);                                                //new RecipeBOImpl();


    @FXML
    void btnDeleteRecipeOnAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You want to Delete this recipe ..!!", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isRecipeDeleted = false;
            try {
                isRecipeDeleted = recipeBO.deleteRecipe(lblRecipeId.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (isRecipeDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Recipe Deleted Successfully!!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Recipe Deletion Failed!!").show();
            }

        }
    }

    @FXML
    void btnRecipeReset(ActionEvent event) throws SQLException {
        refreshRecipeTable();
    }

    @FXML
    void btnSaveRecipeOnActon(ActionEvent event) throws SQLException {
        RecipeDTO recipeDTO = new RecipeDTO(
            lblRecipeId.getText(),
            txtRecipeName.getText(),
            txtRecipeDesc.getText()
        );

        boolean isSaved = false;
        try {
            isSaved = recipeBO.saveRecipe(recipeDTO);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION,"Recipe Saved Successfully.......!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Recipe Saving Failed.......!!").show();
        }
    }

    @FXML
    void btnUpdateRecipeOnAction(ActionEvent event) throws SQLException {

        RecipeDTO recipeDTO = new RecipeDTO(
                lblRecipeId.getText(),
                txtRecipeName.getText(),
                txtRecipeDesc.getText()
        );

        boolean isUpdated = false;
        try {
            isUpdated = recipeBO.updateRecipe(recipeDTO);
            refreshRecipeTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION,"Recipe Updated Successfully.......!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Recipe Updated Failed.......!!").show();
        }


    }

    @FXML
    void onClickTable(MouseEvent event) {

        RecipeTM recipeTM = (RecipeTM) tblRecipe.getSelectionModel().getSelectedItem();
        lblRecipeId.setText(recipeTM.getRecipeId());
        txtRecipeName.setText(recipeTM.getRecipeName());
        txtRecipeDesc.setText(recipeTM.getRecipeDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeCellValueFactory();

        try {
            refreshRecipeTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void initializeCellValueFactory() {
        colRecipeId.setCellValueFactory(new PropertyValueFactory<>("recipeId"));
        colRecipeName.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
        colRecipeDescription.setCellValueFactory(new PropertyValueFactory<>("recipeDescription"));
    }

    private void loadRecipeTable() throws SQLException {
        ArrayList<RecipeDTO> recipeDTOS = null; // create the model
        try {
            recipeDTOS = recipeBO.getAllRecipes();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<RecipeTM> recipeTMObservableList = FXCollections.observableArrayList();
        for (RecipeDTO recipeDTO : recipeDTOS) {
            RecipeTM recipeTM = new RecipeTM(
                    recipeDTO.getRecipeId(),
                    recipeDTO.getRecipeName(),
                    recipeDTO.getRecipeDescription()
            );
            recipeTMObservableList.add(recipeTM);
        }
        tblRecipe.setItems(recipeTMObservableList);

    }

    private void refreshRecipeTable() throws SQLException {
        try {
            lblRecipeId.setText(recipeBO.getNextRecipeID());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtRecipeName.setText("");
        txtRecipeDesc.setText("");
        loadRecipeTable();
    }


















}
