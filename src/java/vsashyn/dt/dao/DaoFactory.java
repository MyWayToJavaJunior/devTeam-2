/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author vsa
 */
public interface DaoFactory {
    
    public Connection getConnection() throws SQLException;
    
    public  DeveloperDao getDeveloperDao(Connection connection);
    
    public ProjectDao getProjectDao(Connection connection);
    
}
