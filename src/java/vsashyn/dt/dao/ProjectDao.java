package vsashyn.dt.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import vsashyn.dt.command.ShowDashboardCommand;
import vsashyn.dt.model.Project;

/**
 *
 * @author vsa
 */
public class ProjectDao {
    private static final org.apache.logging.log4j.Logger LOG = 
            LogManager.getLogger(ProjectDao.class.getName());
    Connection connection;
    
    ProjectDao(Connection connection){
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
 //   public List getAllForWorker()
}
