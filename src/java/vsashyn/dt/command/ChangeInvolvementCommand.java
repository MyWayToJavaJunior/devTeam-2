/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.StaffDAO;
import vsashyn.dt.model.Staff;

/**
 *
 * @author vsa
 */
class ChangeInvolvementCommand implements Command {
    
    private static final Logger LOG 
            = LogManager.getLogger(ChangeInvolvementCommand.class.getName());
    
    public ChangeInvolvementCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "error.jsp";
        HttpSession session = request.getSession(false);
        Staff worker = (Staff) session.getAttribute("worker");
        String status = request.getParameter("status");
        worker.setIsFree(Boolean.getBoolean(status));
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        StaffDAO staffDao = daoManager.getStaffDao();
        staffDao.update(worker);
        daoManager.endConnectionScope();
        
        LOG.info("User " + session.getId() + "change status to " + status);
        resultURL = "successfull";
        return resultURL;
    }
    
    
}
