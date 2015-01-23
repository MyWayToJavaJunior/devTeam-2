/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author vsa
 */
public class DAOFactory {
    
    private static final Logger LOG 
            = LogManager.getLogger(DAOFactory.class.getName());
    private static DataSource dataSource;
//    Connection connection;
    
    static {
        try {
            // Class.forName(driver);
            InitialContext context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/devTeam");
            
        } catch (NamingException ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException();
        }
    }
    
//    public void beginConnectionScope(){
//        try {
//        connection = dataSource.getConnection();
//        } catch (SQLException ex){
//            LOG.error(ex.getMessage());
//        }
//    }
//    public void endConnectionScope(){
//        try{
//            if(connection!=null) connection.close();
//        } catch (SQLException ex){
//            LOG.error(ex.getMessage());
//        }
//    }
    
    public DAOManager getDaoManager(){
        LOG.info("new DaoManager was returned");
        try{
            return new DAOManager(dataSource.getConnection());
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
            return null;
        }
    }
    
//    public StaffDao getStaffDao() throws SQLException{
//            return new StaffDao(dataSource.getConnection());
//    }
//    
//    public CustomerDao getCustomerDao() throws SQLException{
//        return new CustomerDao(dataSource.getConnection());
//    }
//
//    public ProjectDao getProjectDao() throws SQLException {
//        return new ProjectDao(dataSource.getConnection());
//    }
//    public ElapsedTimeDao getElapsedTimeDao() throws SQLException{
//        return new ElapsedTimeDao(dataSource.getConnection());
//    }
//    public ProjectStaffDAO getProjectStaffDao() throws SQLException{
//        return new ProjectStaffDAO(dataSource.getConnection());
//    }
}
