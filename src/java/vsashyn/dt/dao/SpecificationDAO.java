/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.dao;

import java.util.List;
import vsashyn.dt.model.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author vsa
 */
public class SpecificationDAO extends AbstractDAO<Integer, Specification> {
    
    private static Logger LOG = LogManager.getLogger(SpecificationDAO.class.getName());
    Connection connection;
    
    public SpecificationDAO(Connection connection){
        this.connection = connection;
    }
    
    /**
     * Find all new Specifications (witch without projects)
     * @return 
     */
    public List<Specification> findAllNew() {
        PreparedStatement ps = null;
        String sqlQuery = "SELECT * FROM Specification as S LEFT OUTER Join Project as P ON S.idSpecification = P.specification_id  WHERE P.specification_id is null;";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        List<Specification> specs = new ArrayList<>();
        try {
            while(rs.next()){
                Specification spec = new Specification();
                spec.setCustomerId(rs.getInt("idSpecification"));
                spec.setTitle(rs.getString("title"));
                spec.setSpecification(rs.getString("f_spec"));
                specs.add(spec);
            }
        } catch (SQLException ex){
                LOG.error(ex.getMessage());
        }
        return specs;    
    }
    
    @Override
    public List<Specification> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Specification findEntityById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Specification entity) {
        int result=-1;
        PreparedStatement ps = null;
        String sqlQuery = "INSERT INTO Specification (title, f_spec, customer_id) VALUES (?, ?, ?); ";
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getSpecification());
            ps.setInt(3, entity.getCustomerId());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        return (result>-1);
    }

    @Override
    public Specification update(Specification entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
