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
import vsashyn.dt.dao.CustomerDao;
import vsashyn.dt.dao.DAOFactory;
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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        Customer customer = new Customer();
        customer.setEmail(login);
        customer.setPassword(password);
        
        DAOFactory df = new DAOFactory();
        CustomerDao customerDao = null;
//        try {
//            customerDao = df.getCustomerDao();
//        } catch (SQLException ex) {
//            for(StackTraceElement stl :ex.getStackTrace()){
//                System.err.println(stl);
//            }
//        }
        
        if(customerDao.isMember(customer)){
           return "successfullogin";
        } else {
            return "faillogin";
        }
        
    }
    
}
