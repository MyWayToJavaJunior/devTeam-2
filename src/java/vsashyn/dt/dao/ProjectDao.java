/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;
import java.sql.SQLException;
import vsashyn.dt.u.Project;
/**
 *
 * @author vsa
 */
public interface ProjectDao {
    
    
            
    public Project create();
    public void delete();
    public void update();
    public Project read(int key) throws SQLException;
    
    
}
