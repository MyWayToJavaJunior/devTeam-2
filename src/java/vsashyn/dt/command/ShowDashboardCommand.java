/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.ElapsedTimeDAO;
import vsashyn.dt.dao.StaffDAO;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ShowDashboardCommand implements Command{
    
    private static final Logger LOG = 
            LogManager.getLogger(ShowDashboardCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "error.jsp";
        
        HttpSession session = request.getSession(false);
        Integer workerID = (Integer) session.getAttribute("workerID");
        LOG.info("returned workerID ==" + workerID);


        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        StaffDAO staffDao = daoManager.getStaffDao();
        ElapsedTimeDAO elapsedTimeDao = daoManager.getElapsedTimeDao();
    
        LOG.info("Begin connection scope daoFactory");
        
        //Integer workerID =  Integer.p;
        Staff worker = staffDao.findEntityById(workerID);
        request.setAttribute("worker", worker);
        LOG.info("Set request attribute worker ");
        
        Map<Project, Integer> projectsTimes = new HashMap();
        List<Project> projects = staffDao.getProjects(worker);          //Get all projects, where worker involved
        for(Project project : projects){
            projectsTimes.put(project, elapsedTimeDao.getTotalElapsedTime(worker, project));
        }
        daoManager.endConnectionScope();        
        LOG.info("end connection scope daoFactory");
        request.setAttribute("projectsTimes",projectsTimes);            //Set HashMap with projects and total elapsed time for each
        resultURL="dashboard";
        return resultURL;
    }
}
