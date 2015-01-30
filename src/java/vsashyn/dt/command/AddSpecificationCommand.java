/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.CustomerDAO;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.ProjectDAO;
import vsashyn.dt.dao.SpecificationDAO;
import vsashyn.dt.model.Customer;
import vsashyn.dt.model.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author vsa
 */
class AddSpecificationCommand implements Command {
    
    private static final Logger LOG 
            = LogManager.getLogger(AddSpecificationCommand.class.getName());
    
    public AddSpecificationCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "error.jsp";
        HttpSession session = request.getSession(false);
        String specification = request.getParameter("specification");
        String title    =   request.getParameter("title");

        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        CustomerDAO customerDao = daoManager.getCustomerDao();
        ProjectDAO projectDao = daoManager.getProjectDao();
        SpecificationDAO specificationDao = daoManager.getSpecificationDao();
        
        Customer customer  
                = customerDao.findEntityById((Integer)session.getAttribute("customerID"));
        
        Specification spec = new Specification();
        spec.setCustomerId(customer.getId());
        spec.setTitle(title);
        spec.setSpecification(specification);
        
        if(specificationDao.create(spec)) {
            LOG.info("New specification was added by user "+ customer.getName());
        } else {
            LOG.info("Adding new specification was failed with user" + customer.getName());
        }
        
        daoManager.endConnectionScope();
        resultURL = "successfull";
        return resultURL;
    }
    
}
