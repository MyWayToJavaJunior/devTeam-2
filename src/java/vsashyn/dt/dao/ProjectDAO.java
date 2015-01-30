package vsashyn.dt.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import vsashyn.dt.command.ShowDashboardCommand;
import vsashyn.dt.model.Customer;
import vsashyn.dt.model.Project;

/**
 *
 * @author vsa
 */
public class ProjectDAO extends AbstractDAO<Integer, Project>{
    
    private static final org.apache.logging.log4j.Logger LOG = 
            LogManager.getLogger(ProjectDAO.class.getName());
    Connection connection;
    
    ProjectDAO(Connection connection){
        this.connection = connection;
    }
    
    public boolean closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
        return true;
    }
    public Project getProjectEntry(Project project){
        PreparedStatement ps=null;
        String sqlQuery = "SELECT * FROM Project as P WHERE p.idProject=?;";
        ResultSet rs = null;
        try{
        ps=connection.prepareStatement(sqlQuery);
        ps.setInt(1, project.getId());
        rs=ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        try {
            if(rs.next()){
                project.setTitle(rs.getString("title"));
                project.setIdSpecification(rs.getInt("specification_id"));
                project.setBill(rs.getInt("bill"));
            } else {
                //No project with this id
                return null;
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
        }
        return project;
    }
    
    /**
     * Find all projects, created with specific customer.
     * @param customer
     * @return 
     */
    public List<Project> findAll(Customer customer){
        PreparedStatement ps = null;
        String sqlQuery = "SELECT P.* "
                + "FROM Project P, Specification s, Customer_auth C "
                + "WHERE C.idCustomer_auth = S.customer_id "
                + "and P.specification_id = S.idSpecification "
                + "and C.idCustomer_auth=?";
        ResultSet rs = null;
        try {
            ps= connection.prepareStatement(sqlQuery);
            ps.setInt(1, customer.getId());
            rs=ps.executeQuery();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        List<Project> projects = new ArrayList<>();
        try {
            while(rs.next()){
                LOG.info("Has projects");
                Project p = new Project();
                p.setId(rs.getInt("idProject"));
                LOG.info(p.getId());
                p.setTitle(rs.getString("title"));
                LOG.info(p.getTitle());
                p.setIdSpecification(rs.getInt("specification_id"));
                p.setBill(rs.getInt("bill"));
                LOG.info(p.getBill());
                projects.add(p);
                LOG.info("add p to list");
        }
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        LOG.info("return " + projects.size() + " projects");
        return projects;
    }


    @Override
    public List<Project> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project findEntityById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Project entity) {
        PreparedStatement ps = null;
        String sqlQuery = "INSERT INTO Project (title, specification_id, bill) "
                + "VALUES (?,?,?);";
        int result = -1;
        try {
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1 ,entity.getTitle());
            ps.setInt(2 ,entity.getIdSpecification());
            ps.setInt(3 ,entity.getBill());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            LOG.error(ex.getMessage());
        }
        return (result>0);
    }

    @Override
    public Project update(Project entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
