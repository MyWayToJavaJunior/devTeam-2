/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DaoFactory;
import vsashyn.dt.dao.ElapsedTimeDao;
import vsashyn.dt.dao.StaffDao;
import vsashyn.dt.model.ElapsedTime;
import vsashyn.dt.model.Project;
import vsashyn.dt.model.Staff;
/**
 *
 * @author vsa
 */
class AuthStaffCommand implements Command {

    public AuthStaffCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        
        Staff worker = new Staff();
        worker.setEmail(email);
        worker.setPassword(password);
        
        DaoFactory df = new DaoFactory();
        StaffDao staffDao = null;
        ElapsedTimeDao elapsedTimeDao = null;
        try {
            staffDao = df.getStaffDao();
            elapsedTimeDao= df.getElapsedTimeDao();
        } catch (SQLException ex) {
            Logger.getLogger(AuthStaffCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(staffDao.isMember(worker)){
            //Определить кто он: менеджер или девелопер
            HttpSession session = request.getSession();
            worker = staffDao.getStaffEntry(worker);
            session.setAttribute("worker", worker);
            
            Map<Project, Integer> projectsTimes = new HashMap();
            List<Project> projects = staffDao.getProjects(worker);
            for(Project project : projects){
                projectsTimes.put(project, elapsedTimeDao.getTotalElapsedTime(worker, project));
            }
            
            //session.setAttribute("involvedProjects", staffDao.getProjects(worker));
            session.setAttribute("projectsTimes",projectsTimes);
            session.setAttribute("role", staffDao.getQualificationTitle(worker));
            try{
            RequestDispatcher rd = request.getRequestDispatcher("dashboard");
            rd.forward(request, response);
            } catch (ServletException ex){
            } catch (IOException ex){
            }

        } else {
            try {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                PrintWriter pw  = response.getWriter();
                pw.append("Please, error ");
                rd.include(request, response);
               // response.sendRedirect("faillogin");
            } catch (IOException ex) {
            } catch (ServletException ex){
            }
        }
    }
}
