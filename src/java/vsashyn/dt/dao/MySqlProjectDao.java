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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vsashyn.dt.model.Project;

/**
 *
 * @author vsa
 */
public class MySqlProjectDao  implements ProjectDao{
    
    private final Connection connection;
    
    public MySqlProjectDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project read(int key) throws SQLException{
        PreparedStatement stm = null;
        ResultSet rs = null;
        String preparedQuery = "SELECT * FROM project WHERE id = ?;";
            
            stm = connection.prepareStatement(preparedQuery);
            stm.setInt(1, key);
            rs = stm.executeQuery();
            Project  p = new  Project();
            rs.next();
            p.setId(rs.getInt("id"));
            p.setIdSpecification(rs.getInt("idSpecification"));
            p.setName(rs.getString("name"));
        return p;
    }
    
    public Project create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
