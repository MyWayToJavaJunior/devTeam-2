/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vsa
 */
public class CommandFactory {
    
    private static Map<String, Command> commands = new HashMap<>(); 
    
    static{
        commands.put("authCustomer", new AuthCustomerCommand());
        commands.put("authStaff", new AuthStaffCommand());
        commands.put("addElapsedTime", new AddElapsedTimeCommand());
    }
    
    public CommandFactory() throws IOException{
        Properties props = new Properties();
        props.load(new FileInputStream(
                new File("config/commands.properties")));
    }
    
    public static Command createCommand(HttpServletRequest request){
        
        String isStaff      =   request.getParameter("isStaff");
        String isCustomer   =   request.getParameter("isCustomer");
        String changeTime   =   request.getParameter("changeTime");
        String login        =   request.getParameter("login");
        String password     =   request.getParameter("password");
        
        
        if(isStaff!=null){
            if((login!=null)&&(password!=null)){
                    return commands.get("authStaff");
        }
        }
        
        if(isCustomer!=null){
            if((login!=null)&&(password!=null)){
                return commands.get("authCustomer");  
            }
        }
        if(changeTime!=null){
            return commands.get("addElapsedTime");
        }
        return null;
    }
}
