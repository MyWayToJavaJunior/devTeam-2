/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.util.List;
import vsashyn.dt.model.Developer;


/**
 *
 * @author vsa
 */
public interface DeveloperDao {
    
    //    public Project create();
    public void delete();
    public void update(Developer developer);
    public void read(int key);
    
    public List<Developer> getAll();
    
}
