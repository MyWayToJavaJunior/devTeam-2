package vsashyn.dt.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DaoFactory;
import vsashyn.dt.dao.ElapsedTimeDao;
import vsashyn.dt.dao.ProjectDao;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String elapsedTime = request.getParameter("elapsedTime");
        Staff worker = (Staff) session.getAttribute("worker");
        
        DaoFactory daoFactory = new DaoFactory();
        ProjectDao projectDao = null;
        ElapsedTimeDao elapsedTimeDao = null;
        try {
            projectDao = daoFactory.getProjectDao();
            elapsedTimeDao = daoFactory.getElapsedTimeDao();
        } catch (SQLException ex) {
        }
        
        
        Project project = new Project();
        project.setId(Integer.parseInt(request.getParameter("witchProject")));
        project = projectDao.getProjectEntry(project);
        boolean result = elapsedTimeDao.addElapsedTime(project, worker, Integer.parseInt(elapsedTime));
        if(!result){
            RequestDispatcher rd = request.getRequestDispatcher("dashboard");
            //here please add error message in top
            try {
                
                rd.forward(request, response);
            } catch (ServletException ex) {
            } catch (IOException ex) {
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("dashboard");
            try {
                rd.forward(request,response);
            } catch (ServletException ex) {
            } catch (IOException ex) {
            }
        }
    }
    
}
