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
import lk.ijse.gdse.professionalsoftwareproject.bo.custom.CustomerBO;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.view.tm.CustomerTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.gdse.professionalsoftwareproject.bo.BOFactory.BOType.CUSTOMER_BO;

public class CustomerController implements Initializable {

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerName;

    @FXML
    private TableColumn<CustomerTM, String> colDob;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colEmail;


    @FXML
    private Label lblCustomerId;



    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private DatePicker txtCustomerDob;

    @FXML
    private Button btnSave;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(CUSTOMER_BO);                         //new CustomerBOImpl();




    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("This is delete customer button make the changes if there is a null text field show the erro message");


        String customerId = lblCustomerId.getText();//txtCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();



        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = customerBO.deleteCustomer(customerId);

            if (isDeleted) {
               // refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));



        try {
            //loadTableData();
            try {
                refreshPage();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }


    }

    //CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = customerBO.getAllCustomers();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();



        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getCustomerName(),
                    customerDTO.getDob(),
                    customerDTO.getAddress(),
                    customerDTO.getEmail()
            );
            customerTMS.add(customerTM);
        }

        tblCustomer.setItems(customerTMS);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String customerId = lblCustomerId.getText();
        String name = txtCustomerName.getText();
        String dob = txtCustomerDob.getValue().format(formatter);
        String address = txtCustomerAddress.getText();
        String email = txtCustomerEmail.getText();

//        [A-Za-z ]+
//        (1)
//        Pattern namePattern = Pattern.compile("^[A-Za-z ]+$");
//        boolean isValidName = namePattern.matcher(name).matches();
//        System.out.println("method 1 : "+isValidName);

//        (2)
//        System.out.println("method 2 : "+name.matches("^[A-Za-z ]+$"));

        /*
        txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: #7367F0;");
        txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");
         */





        String namePattern = "^[A-Za-z ]+$";
       // String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
      //  String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        //boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
       // boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtCustomerName.getStyle());
            txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//           return;
        }

        /*
        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
//            return;
        }
        */
        if (!isValidEmail) {
            txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid email.............");
            return;
        }


        /*
        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }
        */

        if (isValidName && isValidEmail) { //isValidNic && isValidEmail && isValidPhone) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customerId,
                    name,
                    dob,
                    address,
                    email
            );

            boolean isSaved = customerBO.saveCustomer(customerDTO);
            if (isSaved) {
                //refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }




    }


    @FXML
    void updateCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = lblCustomerId.getText();//txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String dob = txtCustomerDob.getValue().format(formatter);
        String address = txtCustomerAddress.getText();
        String email = txtCustomerEmail.getText();

        txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: #7367F0;");
       // txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
        txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: #7367F0;");
        //txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        //boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        //boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtCustomerName.getStyle());
            txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }


        /*

        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        */
        if (!isValidEmail) {
            txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: red;");
        }

        /*

        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }

        */

        if (isValidName && isValidName) {//isValidNic && isValidEmail && isValidPhone) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customerId,
                    name,
                    dob,
                    address,
                    email
            );

            boolean isUpdate = customerBO.updateCustomer(customerDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
            }
        }
    }








    public void loadNextCustomerId() throws SQLException, ClassNotFoundException {
//        customerModel.helloCustomerModel();
        System.out.println("This is where the error occurs");
        String nextCustomerId = customerBO.getNextCustomerId();
        lblCustomerId.setText(nextCustomerId);
    }


    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);

        //btnUpdate.setDisable(true);
       // btnDelete.setDisable(true);

        txtCustomerDob.setValue(null);
        txtCustomerName.setText("");
       // txtCustomerDob.setText("");
        txtCustomerAddress.setText("");
        txtCustomerEmail.setText("");
    }

    @FXML
    void resetCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }


    @FXML
    void onClickCustomerTbl(MouseEvent event) {
        CustomerTM customerTM = (CustomerTM) tblCustomer.getSelectionModel().getSelectedItem();
        lblCustomerId.setText(customerTM.getCustomerId());
        txtCustomerName.setText(customerTM.getCustomerName());
        txtCustomerAddress.setText(customerTM.getAddress());
        txtCustomerEmail.setText(customerTM.getEmail());
        txtCustomerDob.setValue(LocalDate.parse(customerTM.getDob()));

    }







}
