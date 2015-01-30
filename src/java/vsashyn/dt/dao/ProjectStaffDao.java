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
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.ProjectStaff;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ProjectStaffDAO extends AbstractDAO<Integer, ProjectStaff>{
    
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

    @Override
    public List<ProjectStaff> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProjectStaff findEntityById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(ProjectStaff entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param entity[]
     * @return 
     */
    public boolean create(ProjectStaff[] list){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        PreparedStatement ps = null;
        String sqlQuery = "INSERT INTO Project_staff (Project_idProject, Staff_id_person) VALUES (?, ?);";
        int result = -1;
        for(ProjectStaff entity : list){
            try {
                ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, entity.getIdProject());
                ps.setInt(2, entity.getIdPerson());
                result = ps.executeUpdate();
            } catch (SQLException ex){
                LOG.error(ex.getMessage());
                try {
                    LOG.error("Transaction is bein rolled back");
                    connection.rollback();
                } catch (SQLException ex2){
                    LOG.error(ex2.getMessage());
                }
            }
        }
        return (result>-1);
    }

    @Override
    public ProjectStaff update(ProjectStaff entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
