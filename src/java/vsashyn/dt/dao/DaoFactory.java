/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vsa
 */
public class DaoFactory {
    
    private String user = "dt";
    private String pass = "dtDT123321";
    private String url = "jdbc:mysql://localhost:3306/devTeam";
    private String driver = "com.mysql.jdbc.Driver";
    
    public DaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public StaffDao getStaffDao(){
        return new StaffDao(getConnection());
    }
    
    public CustomerDao getCustomerDao(){
        return new CustomerDao(getConnection());
    }

    public   Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
        }
        return null;
    }

    public DeveloperDao getDeveloperDao(Connection connection) {
    //    return new MySqlDeveloperDao();
        return null;
    }

    public ProjectDao getProjectDao(Connection connection) {
        return new MySqlProjectDao(connection);
    }
    
}
