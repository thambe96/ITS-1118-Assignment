package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.ProductBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.ProductTM;
import lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl.ProductDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.PRODUCT_BO;

public class ProductController implements Initializable {


    @FXML
    private AnchorPane content;

    @FXML
    private Label lblPrdId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private ComboBox<String> cmbDiscountPicker;

    @FXML
    private ComboBox<Integer> cmbQty;

    @FXML
    private ComboBox<String> cmbResipePicker;

    @FXML
    private TableView<ProductTM> tblProduct;

    @FXML
    private TableColumn<?, ?> colPrdID;

    @FXML
    private TableColumn<?, ?> colPrdname;

    @FXML
    private TableColumn<?, ?> colPrdQty;

    @FXML
    private TableColumn<?, ?> colDiscountId;

    @FXML
    private TableColumn<?, ?> colRecipeId;

    @FXML
    private TableColumn<?, ?> colPrdPrice;


    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(PRODUCT_BO);                                                               //new ProductBOImpl();


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alerProduct = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?",
                    ButtonType.YES,
                    ButtonType.NO
                );
        Optional<ButtonType> buttonType = alerProduct.showAndWait();
        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            boolean isProductDeleted = productBO.deleteProduct(lblPrdId.getText());
            if(isProductDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product not deleted").show();
            }
        }

    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshProductController();
    }

    @FXML
    void btnSaveProductOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = new ProductDTO(
                lblPrdId.getText(),
                txtProductName.getText(),
                cmbDiscountPicker.getValue(),
                Double.parseDouble(txtProductPrice.getText()),
                cmbQty.getValue(),
                cmbResipePicker.getValue()
        );

        boolean isProductSaved = productBO.saveProduct(productDTO);
        if (isProductSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Product Not Saved", ButtonType.OK).showAndWait();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = new ProductDTO(
                lblPrdId.getText(),
                txtProductName.getText(),
                cmbDiscountPicker.getValue(),
                Double.parseDouble(txtProductPrice.getText()),
                cmbQty.getValue(),
                cmbResipePicker.getValue()
        );

        boolean isProductUpdated = productBO.updateProduct(productDTO);
        if (isProductUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Product updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Product not updated").show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProcutTblCellValues();

        try {
            refreshProductController();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadDiscountIds() throws SQLException {
        ArrayList<String> discountIds = new ProductDAOImpl().getDicountIds();
        ObservableList<String> observableDiscountIds = FXCollections.observableArrayList();
        observableDiscountIds.addAll(discountIds);
        cmbDiscountPicker.setItems(observableDiscountIds);
    }

    private void loadRecipeIds() throws SQLException {
        ArrayList<String> recipeIds = new ProductDAOImpl().getRecipeIds();
        ObservableList<String> observableRecipeIds = FXCollections.observableArrayList();
        observableRecipeIds.addAll(recipeIds);
        cmbResipePicker.setItems(observableRecipeIds);
    }

    private void loadQtys() {
        ArrayList<Integer> qtys = new ArrayList<>();
        ObservableList<Integer> observableQtys = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            qtys.add(i);
        }
        observableQtys.addAll(qtys);
        cmbQty.setItems(observableQtys);
    }

    private void setProcutTblCellValues() {
        colPrdID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colPrdname.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        colPrdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrdQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colRecipeId.setCellValueFactory(new PropertyValueFactory<>("recipeId"));
    }


    private void loadProductTable() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productDTOS = productBO.getAllProducts();
        ObservableList<ProductTM> observableProductDTOS = FXCollections.observableArrayList();
        for(ProductDTO productDTO : productDTOS) {
            ProductTM productTM = new ProductTM(
                    productDTO.getProductId(),
                    productDTO.getProductName(),
                    productDTO.getDiscountId(),
                    productDTO.getPrice(),
                    productDTO.getQtyOnHand(),
                    productDTO.getRecipeId()
            );
            observableProductDTOS.add(productTM);
        }
        tblProduct.setItems(observableProductDTOS);
    }

    @FXML
    void onMouseClickedOnPrdctTbl(MouseEvent event) {
        ProductTM productTM = (ProductTM) tblProduct.getSelectionModel().getSelectedItem();
        if (productTM != null) {
            lblPrdId.setText(productTM.getProductId());
            txtProductName.setText(productTM.getProductName());
            txtProductPrice.setText(productTM.getPrice() + "");
            cmbDiscountPicker.getSelectionModel().select(productTM.getDiscountId());
            cmbQty.getSelectionModel().select(productTM.getQtyOnHand());
            cmbResipePicker.getSelectionModel().select(productTM.getRecipeId());

        }
    }

    private void refreshProductController() throws SQLException, ClassNotFoundException {
        lblPrdId.setText(productBO.getNextProductId());
        txtProductName.setText("");
        txtProductPrice.setText("");
        cmbQty.getItems().clear();;
        cmbDiscountPicker.setValue("Discount");
        cmbResipePicker.setValue("Recipe");
        loadDiscountIds();
        loadRecipeIds();
        loadProductTable();
        //refreshProductController();
        loadQtys();

    }



}
