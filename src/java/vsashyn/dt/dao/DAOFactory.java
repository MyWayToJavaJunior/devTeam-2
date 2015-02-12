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

    
    static {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/devTeam");
        } catch (NamingException ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException();
        }
    }
  
    public DAOManager getDaoManager(){
        LOG.info("new DaoManager was returned");
        try{
            return new DAOManager(dataSource.getConnection());
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
            return null;
        }
    }
 }
