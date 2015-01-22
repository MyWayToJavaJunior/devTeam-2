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
import org.apache.logging.log4j.LogManager;
import vsashyn.dt.command.ShowDashboardCommand;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.ProjectStaff;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ElapsedTimeDao {
    
    private static final org.apache.logging.log4j.Logger LOG = 
            LogManager.getLogger(ElapsedTimeDao.class.getName());
    Connection connection;
    
    ElapsedTimeDao(Connection connection){
        this.connection = connection;
    }

    public boolean addElapsedTime(Project project, Staff worker, int time){
        boolean result = false;
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.beginConnectionScope();
        LOG.info("begin Connection scope daoFactory");
        ProjectStaffDAO projectStaffDao=daoFactory.getProjectStaffDao();

        ProjectStaff projectStaff = 
                projectStaffDao.getProjectStaffEntry(worker, project);
        PreparedStatement ps = null;
        String sqlQuery = "INSERT INTO Elapsed_time(project_staff_id, time) "
                + "VALUES (?,?);";
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setInt(1, projectStaff.getId());
            ps.setInt(2, time);
            ps.execute();
            result = true;
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
//        } finally {
//            try {
//                if(ps!=null)  ps.close();
//            } catch (SQLException ex) {
//                LOG.error(ex.getMessage());
//            }
        }
        daoFactory.endConnectionScope();
        LOG.info("end Connection scope daoFactory");
        return result;
    }
    
    
    //method getTotalElapsedTime(ProjectStaff)
    public int getTotalElapsedTime(Staff worker, Project project){
        int result = -1;
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.beginConnectionScope();
        LOG.info("begin Connection scope daoFactory");
        ProjectStaffDAO projectStaffDao=daoFactory.getProjectStaffDao();
        
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
            LOG.error(ex.getMessage() + ". StackTrace : ");
            for(StackTraceElement ste: ex.getStackTrace()){
                LOG.debug(ste);
            }
        }
        try {
            if(rs.next()){
                result=rs.getInt("SUM(time)");
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage() + ". StackTrace : ");
            for(StackTraceElement ste: ex.getStackTrace()){
                LOG.debug(ste); 
            }
//        } finally {
//            try {
//                if(ps!=null) ps.close();
//            } catch (SQLException ex) {
//                LOG.error(ex.getMessage() + ". StackTrace : ");
//            for(StackTraceElement ste: ex.getStackTrace()){
//                LOG.error(ste);
//            }
//            }
        }
        daoFactory.endConnectionScope();
        LOG.info("end Connection scope daoFactory");
        return result;
    }
}
