/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.CustomerDAO;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.model.Customer;

/**
 *
 * @author vsa
 */
class AuthCustomerCommand implements Command {

    public AuthCustomerCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultURL = "error.jsp";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
//        Customer customer = new Customer();
//        customer.setEmail(login);
//        customer.setPassword(password);
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        CustomerDAO customerDao = daoManager.getCustomerDao();

        
        
        //insert code after , to show dashboard
        if( customerDao.isMember(login, password) ) {
            HttpSession session = request.getSession(true);
            Customer customer = customerDao.findEntityByEmailAndPass(login, password);
            session.setAttribute("customerID", customer.getId());
        } else {
            // wrong email/password
        }
        daoManager.endConnectionScope();
        
        Command command = new ShowCustomerDashboardCommand();
        resultURL = command.execute(request, response);
        return resultURL;
        
    }
    
}
