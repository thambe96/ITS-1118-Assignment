package lk.ijse.gdse.professionalsoftwareproject.dao.custom.impl;

import lk.ijse.gdse.professionalsoftwareproject.dao.custom.CustomerDAO;
import lk.ijse.gdse.professionalsoftwareproject.dto.CustomerDTO;
import lk.ijse.gdse.professionalsoftwareproject.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from customer");

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Customer Name
                    rst.getString(3),  // DOB
                    rst.getString(4),  // Address
                    rst.getString(5)   // Email
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }


    public boolean delete(String customerId) throws SQLException {
        return SQLUtil.execute("delete from customer where custId=?", customerId);
    }

    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("select custId from customer order by custId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }


    public boolean save(CustomerDTO customerDTO) throws SQLException {
        return SQLUtil.execute(
                "insert into customer values (?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getDob(),
                customerDTO.getAddress(),
                customerDTO.getEmail()
        );
    }


    public boolean update(CustomerDTO customerDTO) throws SQLException {
        return SQLUtil.execute(
                "update customer set name=?, dob=?, address=?, email=? where custId=?",
                customerDTO.getCustomerName(),
                customerDTO.getDob(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getCustomerId()
        );
    }



    /*

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select custId from customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }
        return customerIds;
    }

     */

    public CustomerDTO find(String customerId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from customer where custId=?", customerId);
        if (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            return customerDTO;
        }
        return null;
    }

    public int findOutBirthdayCount(String date) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT name FROM customer WHERE DATE_FORMAT(dob, '%m-%d') = ?", date);
        if (rst.next()) {
            return Integer.parseInt(rst.getString(1));
        }
        return 0;
    }





}
