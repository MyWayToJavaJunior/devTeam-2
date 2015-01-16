package vsashyn.dt.command;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DaoFactory;
import vsashyn.dt.dao.ElapsedTimeDao;
import vsashyn.dt.dao.ProjectDao;
import vsashyn.dt.dao.StaffDao;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
class AddElapsedTimeCommand implements Command {

    public AddElapsedTimeCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "sdfgfsd";
        HttpSession session = request.getSession(false);
        
        String elapsedTime = request.getParameter("elapsedTime");
        Staff worker = (Staff) session.getAttribute("worker");

        StaffDao staffDao = null;
        ProjectDao projectDao = null;
        ElapsedTimeDao elapsedTimeDao = null;

        DaoFactory daoFactory = new DaoFactory();
        try {
            staffDao = daoFactory.getStaffDao();
            projectDao = daoFactory.getProjectDao();
            elapsedTimeDao = daoFactory.getElapsedTimeDao();
        } catch (SQLException ex) {
        }
        Project project = new Project();
        project.setId(Integer.parseInt(request.getParameter("witchProject")));
        project = projectDao.getProjectEntry(project);
        boolean result = elapsedTimeDao.addElapsedTime(project, worker, Integer.parseInt(elapsedTime));
        //if(true) throw null;
        if (result) {                                             //result return true
            Command command = new ShowDashboardCommand();
            resultURL = command.execute(request, response);
        }
        staffDao.closeConnection();
        projectDao.closeConnection();
        elapsedTimeDao.closeConnection();
        return resultURL;
    }
}
