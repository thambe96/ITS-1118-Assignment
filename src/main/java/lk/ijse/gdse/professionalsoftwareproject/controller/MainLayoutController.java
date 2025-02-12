package lk.ijse.gdse.professionalsoftwareproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {


    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/Dashboard.fxml");
    }



    @FXML
    void navigateToCustomer(ActionEvent event) {
        navigateTo("/view/CustomerDetails.fxml");
    }

    @FXML
    void navigateToDashboard(ActionEvent event) {
        navigateTo("/view/Dashboard.fxml");
    }

    @FXML
    void navigatetoOders(ActionEvent event) {
        navigateTo("/view/OrdersLayout.fxml");
    }

    @FXML
    void navigateToProducts(ActionEvent event) {
        navigateTo("/view/ProductsLayout.fxml");
    }

    @FXML
    void navigateToIngredients(ActionEvent event) {
        navigateTo("/view/IngredientsLayout.fxml");
    }

    @FXML
    void navigateToLoyalityProgram(ActionEvent event) {
        navigateTo("/view/LoyalityProgramLayout.fxml");
    }

    @FXML
    void navigateToRecipe(ActionEvent event) {
        navigateTo("/view/RecipeLayout.fxml");
    }

    @FXML
    void navigateToReports(ActionEvent event) {
        navigateTo("/view/ReportsLayout.fxml");
    }

    @FXML
    void navigateToShedule(ActionEvent event) {
        navigateTo("/view/SheduleLayout.fxml");
    }

    @FXML
    void navigateToSupplerDetails(ActionEvent event) {
        navigateTo("/view/SupplierDetailsLayout.fxml");
    }


    @FXML
    void navigateToDiscountDetials(ActionEvent event) {
        navigateTo("/view/DiscountLayout.fxml");
    }

    @FXML
    private Button btnLogout;

    @FXML
    void logoutOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) content.getScene().getWindow();

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginLayout.fxml"));

        //Parent root = loader.load();

        Scene scene = new Scene(loader.load(), 960, 720);
        stage.setTitle("Login Layout!");
        stage.setScene(scene);
        stage.show();




        stage1.close();
    }






    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

//  -------- Loaded anchor edges are bound to the content anchor --------
//      (1) Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

//      (2) Bind the loaded FXML to all edges of the AnchorPane
//            AnchorPane.setTopAnchor(load, 0.0);
//            AnchorPane.setRightAnchor(load, 0.0);
//            AnchorPane.setBottomAnchor(load, 0.0);
//            AnchorPane.setLeftAnchor(load, 0.0);

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }



}
