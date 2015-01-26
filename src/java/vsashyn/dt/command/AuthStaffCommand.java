/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;


import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.ElapsedTimeDAO;
import vsashyn.dt.dao.StaffDAO;
import vsashyn.dt.model.Staff;
/**
 *
 * @author vsa
 */
class AuthStaffCommand implements Command {

    private static final Logger LOG = 
            LogManager.getLogger(AuthStaffCommand.class.getName());
    
    public AuthStaffCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String resultURL = "error.jsp";
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        
        LOG.info("get auth Parameters from request");
        
        Staff worker = new Staff();
        worker.setEmail(email);
        worker.setPassword(password);
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        LOG.info("Begin connection scope daoFactory");
        StaffDAO staffDao = daoManager.getStaffDao();
        
        LOG.info("Dao Objects created");
        
        if(staffDao.isMember(worker)){
            LOG.info("New user loginning as staff worker");
            //Определить кто он: менеджер или девелопер
            HttpSession session = request.getSession();
            worker = staffDao.getStaffEntry(worker);
            session.setAttribute("worker", worker);
            session.setAttribute("role", staffDao.getQualificationTitle(worker));
            LOG.info("end connection scope daoFactory");
            daoManager.endConnectionScope();
            
            //Run showDashboardCommand
            
            Command command = new ShowDashboardCommand();
            resultURL = command.execute(request, response);
            return resultURL;

        } else {
            daoManager.endConnectionScope();
            resultURL = "index";
            return resultURL;
        }
    }
}