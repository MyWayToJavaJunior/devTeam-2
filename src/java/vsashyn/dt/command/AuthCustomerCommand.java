/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vsashyn.dt.dao.CustomerDao;
import vsashyn.dt.dao.DaoFactory;
import vsashyn.dt.model.Customer;

/**
 *
 * @author vsa
 */
class AuthCustomerCommand implements Command {

    public AuthCustomerCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        Customer customer = new Customer();
        customer.setEmail(login);
        customer.setPassword(password);
        
        DaoFactory df = new DaoFactory();
        CustomerDao customerDao = df.getCustomerDao();
        
        if(customerDao.isMember(customer)){
            try {
                response.sendRedirect("successfullogin");
            } catch (IOException ex) {
            }
        } else {
            try {
                response.sendRedirect("faillogin");
            } catch (IOException ex) {
            }
        }
        
    }
    
}
