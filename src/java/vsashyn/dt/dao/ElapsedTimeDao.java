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
import java.util.logging.Level;
import java.util.logging.Logger;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.ProjectStaff;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ElapsedTimeDao {
    Connection connection;
    
    ElapsedTimeDao(Connection connection){
        this.connection = connection;
    }        
    //  addElapsedTime(Project, Worker)
    public boolean addElapsedTime(Project project, Staff worker, int time){
        DaoFactory daoFactory = new DaoFactory();
        ProjectStaffDao projectStaffDao=null;
        try {
            projectStaffDao = daoFactory.getProjectStaffDao();
        } catch (SQLException ex) {
        }
        ProjectStaff projectStaff = 
                projectStaffDao.getProjectStaffEntry(worker, project);
        PreparedStatement ps = null;
        String sqlQuery = "INSERT INTO Elapsed_time(project_staff_id, time) "
                + "VALUES (?,?);";
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setInt(1, projectStaff.getId());
            ps.setInt(2, time);
            return ps.execute();
        } catch (SQLException ex){
        }
        return false;
    }
    
    
    //method getTotalElapsedTime(ProjectStaff)
    public int getTotalElapsedTime(Staff worker, Project project){
        int result = -1;
        DaoFactory daoFactory = new DaoFactory();
        ProjectStaffDao projectStaffDao=null;
        try {
            projectStaffDao = daoFactory.getProjectStaffDao();
        } catch (SQLException ex) {
        }
        ProjectStaff projectStaff = 
                projectStaffDao.getProjectStaffEntry(worker, project);
        
        PreparedStatement ps = null;
        String sqlQuery = "SELECT SUM(time) FROM Elapsed_time WHERE project_staff_id=?; ";
        ResultSet rs = null;
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setInt(1, projectStaff.getId());
            rs=ps.executeQuery();
        } catch (SQLException ex){
        }
        try {
            if(rs.next()){
                result=rs.getInt("SUM(time)");
            }
        } catch (SQLException ex) {
        }
        return result;
    }
}
