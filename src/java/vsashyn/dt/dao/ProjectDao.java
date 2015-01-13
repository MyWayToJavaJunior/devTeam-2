package vsashyn.dt.dao;
import java.sql.Connection;
import vsashyn.dt.model.Project;

/**
 *
 * @author vsa
 */
public class ProjectDao {
    Connection connection;
    
    ProjectDao(Connection connection){
        this.connection = connection;
    }
    
 //   public List getAllForWorker()
}
