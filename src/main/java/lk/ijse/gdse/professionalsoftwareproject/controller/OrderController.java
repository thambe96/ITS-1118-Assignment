package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.CustomerBO;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.OrderBO;
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.ProductBO;
import lk.ijse.gdse.professionalsoftwareproject.db.DBConnection;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrderDetailsDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.OrdersDTO;
import lk.ijse.gdse.professionalsoftwareproject.dto.ProductDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.CartTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.*;

public class OrderController implements Initializable {


    @FXML
    private Label lblOrderId;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private Label lblCustname;

    @FXML
    private Label lblDate;

    @FXML
    private ComboBox<String> cmbPrdId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private ComboBox<Integer> cmbQty;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableColumn<?, ?> colOrdId;

    @FXML
    private TableColumn<?, ?> colPrdID;

    @FXML
    private TableColumn<?, ?> colPrdName;

    @FXML
    private TableColumn<?, ?> colQtyInCart;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblTotal;

    private double totalPrice;


    private String productNameInCart;

    private ObservableList<CartTM> cartTMObservableList = FXCollections.observableArrayList();

    private String orderIdJasperReport;
    private double totalPriceJasperReport;


    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(CUSTOMER_BO);                                //new CustomerBOImpl();
    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(PRODUCT_BO);                                                                                           //new ProductBOImpl();
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(ORDER_BO);                                                                                      //new OrderBOImpl();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValues();


        try {
            refreshOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void refreshOrder() throws SQLException, ClassNotFoundException {

        String orderId = orderBO.getNextOrderID();

        //lblOrderId.setText(new OrderDAOImpl().getNextId()); // this line needs to be changed

        lblOrderId.setText(orderId);
        lblDate.setText(LocalDate.now().toString());
        loadQtyCombox();
        loadCustCombox();
        loadProductCombox();

        cmbCustId.getSelectionModel().clearSelection();
        cmbPrdId.getSelectionModel().clearSelection();
        cmbQty.getSelectionModel().clearSelection();
        lblCustname.setText("");
        lblQtyOnHand.setText("");
        lblUnitPrice.setText("");
        lblTotal.setText("");
        totalPrice = 0;
        cartTMObservableList.clear();
        tblCart.refresh();

    }




    private void loadCustCombox() throws SQLException, ClassNotFoundException {

//        CustomerDAO customerDAO = new CustomerDAOImpl();  // this line needs to be changed
//
//        ArrayList<CustomerDTO> customerList = customerDAO.getAll();
//
//        ArrayList<String> custIds = new ArrayList<>();  //new CustomerDAOImpl().getAllCustomerIds();

//        for (CustomerDTO customerDTO : customerList) {
//            custIds.add(customerDTO.getCustomerId());
//        }

        ArrayList<String> custIds = orderBO.getAllCustomerIDs();
        ObservableList<String> observableListCutIds = FXCollections.observableArrayList();
        for (String custId : custIds) {
            observableListCutIds.add(custId);
        }
        cmbCustId.setItems(observableListCutIds);
    }

    private void loadProductCombox() throws SQLException, ClassNotFoundException {


//        ProductDAO productDAO = new ProductDAOImpl(); // this line needs to be changed
//        ArrayList<ProductDTO> productList = productDAO.getAll();
//        ArrayList<String> prdIds = new ArrayList<>();             //new ProductDAOImpl().getAllProductIds();
//
//        for (ProductDTO productDTO : productList) {
//            prdIds.add(productDTO.getProductId());
//        }

        ArrayList<String> prdIds = orderBO.getAllProductIDs();
        ObservableList<String> observableListPrdIds = FXCollections.observableArrayList();
        for (String prdId : prdIds) {
            observableListPrdIds.add(prdId);
        }
        cmbPrdId.setItems(observableListPrdIds);
    }

    private void loadQtyCombox() throws SQLException {
        ArrayList<Integer> qtys = new ArrayList<>();
        ObservableList<Integer> observableListQtys = FXCollections.observableArrayList();
        for (int i = 1; i < 21; i++) {
            qtys.add(i);
        }
        observableListQtys.setAll(qtys);
        cmbQty.setItems(observableListQtys);
    }

    @FXML
    void cmdCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String custId = cmbCustId.getValue();
        CustomerDTO customerDTO = customerBO.findCustomer(custId);
        if (customerDTO != null) {
            lblCustname.setText(customerDTO.getCustomerName());
        }

    }

    @FXML
    void cmdProductOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = productBO.findProduct(cmbPrdId.getValue());
        if (productDTO != null) {
            lblQtyOnHand.setText(String.valueOf(productDTO.getQtyOnHand()));
            lblUnitPrice.setText(String.valueOf(productDTO.getPrice()));
            productNameInCart = productDTO.getProductName();
        }


    }




    private void setCellValues() {

        colOrdId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPrdID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colPrdName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQtyInCart.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("cartButton"));

        tblCart.setItems(cartTMObservableList);

    }

    @FXML
    void btnAddToCart(ActionEvent event) {

        System.out.println("this is add to cart button");
        String orderId = lblOrderId.getText();
        String productId = cmbPrdId.getValue();
        //String prodcutName = "No name for now";
        //String custId = cmbCustId.getValue();

        int quantityForCart = cmbQty.getValue();

        int qtyOnHand = Integer.parseInt(lblQtyOnHand.getText());

        if (quantityForCart > qtyOnHand) {
            new Alert(Alert.AlertType.INFORMATION, "In sufficient Stock").show();
            return;
        }

        double price = Double.parseDouble(lblUnitPrice.getText()) * quantityForCart;

        for (CartTM cartTM : cartTMObservableList) {
            if (cartTM.getProductId().equals(productId)) {
                int newQuantity = cartTM.getQuantity() + quantityForCart;
                cartTM.setQuantity(newQuantity);
                cartTM.setPrice(Double.parseDouble(lblUnitPrice.getText()) * newQuantity);
                tblCart.refresh();

                cmbPrdId.getSelectionModel().clearSelection();
                cmbQty.getSelectionModel().clearSelection();
                lblQtyOnHand.setText("");
                lblUnitPrice.setText("");

                return;
            }
        }

        Button btnRemove = new Button("Remove");

        CartTM newCartTM = new CartTM(
                orderId,
                productId,
                productNameInCart,
                quantityForCart,
                price,
                btnRemove
        );

        btnRemove.setOnAction(actionEvent -> {
            cartTMObservableList.remove(newCartTM);
            tblCart.refresh();
        } );

        cartTMObservableList.add(newCartTM);




        cmbPrdId.getSelectionModel().clearSelection();
        cmbQty.getSelectionModel().clearSelection();
        lblQtyOnHand.setText("");
        lblUnitPrice.setText("");


    }


    @FXML
    void placeOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "No cart to place").show();
            return;
        }

        if (cmbCustId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "No customer to place").show();
            return;
        }

        if (lblTotal.getText().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please Calculate The Total Price!!").show();
            return;
        }


        /*
            //-->> Order DTO
        private String orderId;
        private String date;
        private String customerId;
        private double totalAmountSpend;
        private ArrayList<OrderDetailsDTO> orderDetails;

             //-->> OrderDetailsDTO


        private String productId;
        private String orderId;
        private int qty;
        private double price;

         */


        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

        String orderId = lblOrderId.getText();
        String date = lblDate.getText();
        String custId = cmbCustId.getValue();

        for (CartTM cartTM : cartTMObservableList) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    cartTM.getProductId(),
                    orderId,
                    cartTM.getQuantity(),
                    cartTM.getPrice()
            );
            orderDetailsDTOS.add(orderDetailsDTO);
        }

        OrdersDTO ordersDTO = new OrdersDTO(
                orderId,
                date,
                custId,
                totalPrice,
                orderDetailsDTOS
        );


        /*call the placeOrder(orderDTO) method of OrderBOImpl class in bo layer*/

        boolean isOrderSaved = orderBO.placeOrder(ordersDTO);                        //new OrderDAOImpl().save(ordersDTO);

        orderIdJasperReport = lblOrderId.getText();
        totalPriceJasperReport = totalPrice;

        if (isOrderSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Order Saved").show();
            refreshOrder();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Order Not Saved").show();
        }







    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshOrder();
    }

    @FXML
    void calculateTotalOnAction(ActionEvent event) {
        // this is for temporory
        for (CartTM cartTM : cartTMObservableList) {
            totalPrice += cartTM.getPrice();
        }
        lblTotal.setText(String.valueOf(totalPrice));
    }


    //----------------------------

    @FXML
    private Button btnReceipt;

    @FXML
    void generateReceiptOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/Blank_A4.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Order_Id", orderIdJasperReport);
            parameters.put("P_Total", totalPriceJasperReport);



            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
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





}
