/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DaoFactory;
import vsashyn.dt.dao.ElapsedTimeDao;
import vsashyn.dt.dao.StaffDao;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
public class ShowDashboardCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "asdfasdf";
        
        HttpSession session = request.getSession();
        DaoFactory daoFactory = new DaoFactory();
        StaffDao staffDao = null;
        ElapsedTimeDao elapsedTimeDao = null;
        Staff worker = (Staff) session.getAttribute("worker");
        try {
            staffDao = daoFactory.getStaffDao();
            elapsedTimeDao = daoFactory.getElapsedTimeDao();
        } catch (SQLException ex){
            throw null;
        }

        Map<Project, Integer> projectsTimes = new HashMap();
        List<Project> projects = staffDao.getProjects(worker);          //Get all projects, where worker involved
        for(Project project : projects){
            projectsTimes.put(project, elapsedTimeDao.getTotalElapsedTime(worker, project));
        }
        request.setAttribute("projectsTimes",projectsTimes);            //Set HashMap with projects and total elapsed time for each
        resultURL="dashboard";
        //if(true) throw null;
        staffDao.closeConnection();
        elapsedTimeDao.closeConnection();
        return resultURL;
    }
}
