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
    static {
        try {
            // Class.forName(driver);
            InitialContext context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/devTeam");
            
        } catch (NamingException ex) {
        }
    }
//    private String user = "dt";
//    private String pass = "dtDT123321";
//    private String url = "jdbc:mysql://localhost:3306/devTeam";
//    private String driver = "com.mysql.jdbc.Driver";
//    
    
    public StaffDao getStaffDao() throws SQLException{
            return new StaffDao(dataSource.getConnection());
    }
    
    public CustomerDao getCustomerDao() throws SQLException{
        return new CustomerDao(dataSource.getConnection());
    }

    public ProjectDao getProjectDao() throws SQLException {
        return new ProjectDao(dataSource.getConnection());
    }
    public ElapsedTimeDao getElapsedTimeDao() throws SQLException{
        return new ElapsedTimeDao(dataSource.getConnection());
    }
    public ProjectStaffDao getProjectStaffDao() throws SQLException{
        return new ProjectStaffDao(dataSource.getConnection());
    }
}
