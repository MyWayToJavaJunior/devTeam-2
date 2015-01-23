/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author vsa
 */
public class DAOManager {
    Connection connection;
    private static final Logger LOG = LogManager.getLogger(DAOManager.class.getName());
    
    public DAOManager(Connection connection){
        this.connection = connection;
    }
    
    /**
     * Run this method after all DAO methods was invoked and you must 
     * return connection to connection pool.
     */
    public void endConnectionScope(){
        try{
            if(connection!=null) connection.close();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
    }
    
    public StaffDAO getStaffDao(){
            return new StaffDAO(connection);
    }
    
    public CustomerDAO getCustomerDao() {
        return new CustomerDAO(connection);
    }

    public ProjectDAO getProjectDao() {
        return new ProjectDAO(connection);
    }
    public ElapsedTimeDAO getElapsedTimeDao(){
        return new ElapsedTimeDAO(connection);
    }
    public ProjectStaffDAO getProjectStaffDao(){
        return new ProjectStaffDAO(connection);
    }
}
