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
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;
import vsashyn.dt.dao.AbstractDAO;
/**
 *
 * @author vsa
 */
public class StaffDAO extends AbstractDAO<Integer, Staff>{
    
    private static final Logger LOG 
            = LogManager.getLogger(StaffDAO.class.getName());
    Connection connection;
    
    public StaffDAO(Connection connection) {
        this.connection = connection;
    }
    
    public boolean closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            throw null;
        }
        return true;
    }
    public boolean isMember(Staff worker) {
        String email = worker.getEmail();
        String password = worker.getPassword();
        PreparedStatement ps = null;
        String sqlQuery = "SELECT * FROM Staff_auth WHERE email=? and password=?";
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, email);
            ps.setString(2, password);
        } catch (SQLException ex) {
        }
        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    /**
     *
     * @param email
     * @return null, if we cannot execute query
     */
    public Staff getStaffEntry(Staff worker) {
        if (!isMember(worker)) {
            //Ты неправильно используешь метод получения работника. 
            return null;
        }
        PreparedStatement ps = null;
        String sqlQuery = "SELECT S.idPerson, S.name, S.surname, S.qualification_id, S.isFree "
                + "FROM Staff as S, Staff_auth as Sa WHERE S.idPerson = Sa.staff_id  and Sa.email=?";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, worker.getEmail());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
        }
        try {
            if (rs.next()) {
                worker.setId(rs.getInt("idPerson"));
                worker.setName(rs.getString("name"));
                worker.setSurname(rs.getString("surname"));
                worker.setIdQualification(rs.getInt("qualification_id"));
                worker.setIsFree(rs.getBoolean("isFree"));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        return worker;
    }
    
    public String getQualificationTitle(Staff worker) {
        PreparedStatement ps = null;
        String sqlQuery = "SELECT Q.title FROM  Qualification as Q, Staff as S "
                + " WHERE S.qualification_id=Q.idQualification AND S.idPerson=?";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, worker.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
        }
        String result = null;
        try {
            if (rs.next()) {
                result = rs.getString("title");
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        return result;
    }
    
    public List getProjects(Staff worker) {
        List resultProjects = new ArrayList();
        PreparedStatement ps = null;
        String sqlQuery = "SELECT P.idProject, P.title, P.specification_id, P.bill "
                + "FROM Staff as S, Project_staff as Ps, Project as P "
                + "WHERE S.idPerson = Ps.Staff_id_person "
                + "and Ps.Project_idProject = P.idProject "
                + "and S.idPerson=?;";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, worker.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        try {
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("idProject"));
                project.setTitle(rs.getString("title"));
                project.setIdSpecification(rs.getInt("specification_id"));
                project.setBill(rs.getInt("bill"));
                resultProjects.add(project);
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        return resultProjects;
    }
    /*
     public Integer getWorkerID(String email){
     PreparedStatement ps = null;
     String sqlQuery = "SELECT S.idPerson FROM Staff as S, Staff_auth as Sa WHERE S.idPerson=Sa.staff_id and Sa.email=?";
     ResultSet rs=null;
     Integer result=-1;
     try{
     ps=connection.prepareStatement(sqlQuery);
     ps.setString(1, email);
     rs = ps.executeQuery();
     rs.next();
     result = rs.getInt("id_person");
     } catch (SQLException ex){
     }
       
     return result;
     }
     */

    @Override
    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Staff findEntityById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Staff update(Staff worker) {
        PreparedStatement ps = null;
        String sqlQuery = "UPDATE Staff "
                + "SET name=?, "
                + "surname=?, "
                + "qualification_id=?, "
                + "isFree=? "
                + "WHERE idPerson=?;";
        ResultSet rs = null;
        int resultSQL = -1;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, worker.getName());
            ps.setString(2, worker.getSurname());
            ps.setInt(3, worker.getIdQualification());
            ps.setBoolean(4, worker.isIsFree());
            ps.setInt(5, worker.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        if(-1 == resultSQL) LOG.error("SQLQuery has been runned unsuccesfully");
        return worker;
    }

   
}
