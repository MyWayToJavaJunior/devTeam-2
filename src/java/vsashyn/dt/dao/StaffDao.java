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
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class StaffDao {
    Connection connection;
    
    public StaffDao(Connection connection){
        this.connection = connection;
    }
    
    public boolean isMember(Staff worker){
        String email = worker.getEmail();
        String password = worker.getPassword();
        PreparedStatement ps=null;
        String sqlQuery = "SELECT * FROM Staff_auth WHERE email=? and password=?";
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setString(1, email);
            ps.setString(2 , password);
        } catch (SQLException ex){
            }
        try {
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true;
            }
        } catch (SQLException ex){
        }
        return false;
    }
    
    /**
     * 
     * @param email
     * @return null, if we cannot execute query
     */
    public Staff getStaffEntry(String email){
        Staff worker = new Staff();
        PreparedStatement ps = null;
        String sqlQuery = "SELECT S.idPerson, S.name, S.surname, S.qualification_id, S.isFree "
                + "FROM Staff as S, Staff_auth as Sa WHERE S.idPerson = Sa.staff_id  and Sa.email=?";
        ResultSet rs = null;
        try{
            ps=connection.prepareStatement(sqlQuery);
            ps.setString(1, email);
            rs = ps.executeQuery();
        } catch (SQLException ex){
        }
        try{
            if(rs.next()){
             worker.setId(rs.getInt("idPerson"));
             worker.setName(rs.getString("name"));
             worker.setSurname(rs.getString("surname"));
             worker.setIdQualification(rs.getInt("qualification_id"));
             worker.setIsFree(rs.getBoolean("isFree"));
            }
        }catch (SQLException ex){
        }
        return worker;
    }
    
    public String getQualificationTitle(Staff worker){
        
        PreparedStatement ps = null;
        String sqlQuery = "SELECT Q.title FROM  Qualification as Q, Staff as S  WHERE S.qualification_id=Q.idQualification AND S.idPerson=?";
        ResultSet rs = null;
        try {
            ps=connection.prepareStatement(sqlQuery);
            ps.setInt(1, getWorkerID(worker.getEmail()));
            rs =   ps.executeQuery();
        } catch (SQLException ex) {
        }
        String result=null;
        try {
            if(rs.next()){
                result=rs.getString("title");
            }
        } catch (SQLException ex) {
        }
        return result;
    }
    
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
}
