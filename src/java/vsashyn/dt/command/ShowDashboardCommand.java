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
import vsashyn.dt.dao.ElapsedTimeDao;
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
        String resultURL = "asdfasdf";
        
        HttpSession session = request.getSession(false);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.beginConnectionScope();
        LOG.info("Begin connection scope daoFactory");
        StaffDAO staffDao = daoFactory.getStaffDao();
        ElapsedTimeDao elapsedTimeDao = daoFactory.getElapsedTimeDao();
        
        Staff worker = (Staff) session.getAttribute("worker");
        Map<Project, Integer> projectsTimes = new HashMap();
        List<Project> projects = staffDao.getProjects(worker);          //Get all projects, where worker involved
        for(Project project : projects){
            projectsTimes.put(project, elapsedTimeDao.getTotalElapsedTime(worker, project));
        }
        daoFactory.endConnectionScope();        
        LOG.info("end connection scope daoFactory");
        request.setAttribute("projectsTimes",projectsTimes);            //Set HashMap with projects and total elapsed time for each
        resultURL="dashboard";
        return resultURL;
    }
}
