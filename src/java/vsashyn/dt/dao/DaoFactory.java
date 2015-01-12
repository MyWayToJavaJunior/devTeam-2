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

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author vsa
 */
public class DaoFactory {
    
    private static DataSource dataSource;
    
//    private String user = "dt";
//    private String pass = "dtDT123321";
//    private String url = "jdbc:mysql://localhost:3306/devTeam";
//    private String driver = "com.mysql.jdbc.Driver";
//    
    public DaoFactory() {
        try {
            // Class.forName(driver);
            InitialContext context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/devTeam");
            
        } catch (NamingException ex) {
        }
    }
    
    public StaffDao getStaffDao(){
        return new StaffDao(getConnection());
    }
    
    public CustomerDao getCustomerDao(){
        return new CustomerDao(getConnection());
    }

    public   Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
        }
        return conn;
    }

    public DeveloperDao getDeveloperDao(Connection connection) {
    //    return new MySqlDeveloperDao();
        return null;
    }

    public ProjectDao getProjectDao(Connection connection) {
        return new MySqlProjectDao(connection);
    }
    
}
