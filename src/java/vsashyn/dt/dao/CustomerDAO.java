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
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vsashyn.dt.model.Customer;

/**
 *
 * @author vsa
 */
public class CustomerDAO extends AbstractDAO<Integer, Customer>{

    private static final Logger LOG
            = LogManager.getLogger(CustomerDAO.class.getName());
    Connection connection;

    CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean isMember(String email, String password) {
        PreparedStatement ps;
        String sqlQuery = "SELECT idCustomer_auth FROM Customer_auth WHERE email=? and password =?";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        boolean result=false;
        try {
            result=rs.next();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Customer findEntityByEmailAndPass(String email, String password){
        PreparedStatement ps = null;
        String sqlQuery = "SELECT * FROM Customer_auth WHERE email=? and password=?";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        Customer customer =null;
        try {
            if(rs!=null) {
                rs.next();
                customer = new Customer();
                customer.setId(rs.getInt("idCustomer_auth"));
                customer.setName(rs.getString("name"));
                customer.setEmail(email);
                customer.setPassword(password);
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex){
                LOG.error(ex.getMessage());
                }
        return customer;       
        }
    
    @Override
    public Customer findEntityById(Integer id) {
        PreparedStatement ps = null;
        String sqlQuery = "SELECT * FROM Customer_auth WHERE idCustomer_auth=?";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        Customer customer =null;
        try {
            if(rs!=null) {
                rs.next();
                customer = new Customer();
                
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword("password");
                customer.setId(id);
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex){
                LOG.error(ex.getMessage());
                }
        return customer;       
        }
    

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer create(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer update(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    
