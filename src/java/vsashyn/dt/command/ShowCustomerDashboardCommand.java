/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.CustomerDAO;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.ProjectDAO;
import vsashyn.dt.model.Customer;
import vsashyn.dt.model.Project;

/**
 *
 * @author vsa
 */
public class ShowCustomerDashboardCommand implements Command{
    private static Logger LOG = LogManager.getLogger(ShowCustomerDashboardCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        CustomerDAO customerDao = daoManager.getCustomerDao();
        ProjectDAO projectDao = daoManager.getProjectDao();
        Customer customer  
                = customerDao.findEntityById((Integer)session.getAttribute("customerID"));
        request.setAttribute("customer", customer);
        List<Project> projects = projectDao.findAll(customer);
        request.setAttribute("projects", projects);
        LOG.info("setted projects attribute. Size : " + projects.size());
        
        daoManager.endConnectionScope();
        return "dashboard_customer";
    }
    
}
