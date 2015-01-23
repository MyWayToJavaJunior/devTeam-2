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
import org.apache.logging.log4j.Logger;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.ProjectStaff;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ProjectStaffDAO {
    
    private static Logger LOG 
            = LogManager.getLogger(ProjectStaffDAO.class.getName());
    Connection connection;
    
    public ProjectStaffDAO(Connection connection){
        this.connection = connection;
    }
    public ProjectStaff getProjectStaffEntry(Staff worker, Project project){
        ProjectStaff projectStaff = new ProjectStaff();
        PreparedStatement ps = null;
        String sqlQuery = "SELECT Ps.idProject_staff "
                + "FROM Staff as S, Project_staff as Ps, Project as P "
                + "WHERE S.idPerson = Ps.Staff_id_person "
                + "and Ps.Project_idProject = P.idProject "
                + "and S.idPerson=? "
                + "and P.idProject=?;";
        ResultSet rs = null;
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setInt(1, worker.getId());
            ps.setInt(2, project.getId());
            rs=ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage()  + ". Next stack trace :");
            for(StackTraceElement ste : ex.getStackTrace()){
                LOG.error(ste);
            }
        }
        try {
            if(rs.next()){
                projectStaff.setId(rs.getInt("idProject_staff"));
                projectStaff.setIdPerson(worker.getId());
                projectStaff.setIdProject(project.getId());
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage()  + ". Next stack trace :");
            for(StackTraceElement ste : ex.getStackTrace()){
                LOG.error(ste);
            }
        }
        return projectStaff;
    }
}
