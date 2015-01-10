/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vsashyn.dt.model.Customer;

/**
 *
 * @author vsa
 */
public class CustomerDao {
    Connection connection;
    
    CustomerDao(Connection connection){
        this.connection = connection;
    }
    
    public boolean isMember(Customer customer){
        PreparedStatement ps = null;
        String sqlQuery = "SELECT idCustomer_auth FROM Customer_auth WHERE email=? and password =?";
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            rs = ps.executeQuery();
        } catch (SQLException ex){
            }
        try {
            return rs.next();
        } catch (SQLException ex) {
        }
         
        return false;
    }
    
}
