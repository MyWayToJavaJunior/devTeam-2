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
public class MySqlDaoFactory implements DaoFactory{
    
    private String user = "dt";
    private String pass = "dtDT123321";
    private String url = "jdbc:mysql://localhost:3306/devTeam";
    private String driver = "com.mysql.jdbc.Driver";
    

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    @Override
    public DeveloperDao getDeveloperDao(Connection connection) {
    //    return new MySqlDeveloperDao();
        return null;
    }

    @Override
    public ProjectDao getProjectDao(Connection connection) {
        return new MySqlProjectDao(connection);
    }
    
}
