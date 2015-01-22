package vsashyn.dt.command;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.ElapsedTimeDao;
import vsashyn.dt.dao.ProjectDao;
import vsashyn.dt.dao.StaffDAO;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
class AddElapsedTimeCommand implements Command {
    
    private static final Logger LOG = 
            LogManager.getLogger(AddElapsedTimeCommand.class.getName());
    
    public AddElapsedTimeCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "sdfgfsd";
        HttpSession session = request.getSession(false);
        
        String elapsedTime = request.getParameter("elapsedTime");
        Staff worker = (Staff) session.getAttribute("worker");
        
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.beginConnectionScope();              //begin connection scope
        LOG.info("Begin connection scope daoFactory");
        StaffDAO staffDao = daoFactory.getStaffDao();
        ProjectDao projectDao = daoFactory.getProjectDao();
        ElapsedTimeDao elapsedTimeDao = daoFactory.getElapsedTimeDao();

        
        Project project = new Project();
        project.setId(Integer.parseInt(request.getParameter("witchProject")));
        project = projectDao.getProjectEntry(project);
        boolean result = elapsedTimeDao.addElapsedTime(project, worker, Integer.parseInt(elapsedTime));
        
        if(result){
            resultURL = "successfull";
        }
        daoFactory.endConnectionScope();
        LOG.info("end Connection scope daoFactory");
        return resultURL;
    }
}
