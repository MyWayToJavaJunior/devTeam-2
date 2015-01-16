/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String resultURL = "error65433efttg";
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        
        Staff worker = new Staff();
        worker.setEmail(email);
        worker.setPassword(password);
        
        DaoFactory daoFactory = new DaoFactory();
        StaffDao staffDao = null;
        ElapsedTimeDao elapsedTimeDao = null;
        try {
            staffDao = daoFactory.getStaffDao();
            elapsedTimeDao= daoFactory.getElapsedTimeDao();
        } catch (SQLException ex) {
            throw null;
        }
        
        if(staffDao.isMember(worker)){
            //Определить кто он: менеджер или девелопер
            HttpSession session = request.getSession();
            worker = staffDao.getStaffEntry(worker);
            session.setAttribute("worker", worker);
            session.setAttribute("role", staffDao.getQualificationTitle(worker));

            //Run showDashboardCommand
            
            Command command = new ShowDashboardCommand();
            resultURL = command.execute(request, response);
//            session.setAttribute("command","showDashboard");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Controller");
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException ex) {
//            } catch (IOException ex) {
//                Logger.getLogger(AuthStaffCommand.class.getName()).log(Level.SEVERE, null, ex);
//            }
//               
            //if(true) throw null;
        }
        staffDao.closeConnection();
        elapsedTimeDao.closeConnection();
        return resultURL;

}
}