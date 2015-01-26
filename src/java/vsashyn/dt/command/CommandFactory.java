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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author vsa
 */
public class CommandFactory {
    
    private static final Logger LOG = 
            LogManager.getLogger(CommandFactory.class.getName());
    
    private static final Map<String, Command> commands = new HashMap<>(); 
    
    static{
        commands.put("authCustomer", new AuthCustomerCommand());
        commands.put("authStaff", new AuthStaffCommand());
        commands.put("addElapsedTime", new AddElapsedTimeCommand());
        commands.put("showDashboard", new ShowDashboardCommand());
        commands.put("changeInvolvement", new ChangeInvolvementCommand());
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
        String command      =   request.getParameter("command");
        
        
        if(command.equals("loginStaff")){
            if((login!=null)&&(password!=null)){
                    return commands.get("authStaff");
        }
        }
        
        if(command.equals("loginCustomer")){
            if((login!=null)&&(password!=null)){
                return commands.get("authCustomer");  
            }
        }
        if(command.equals("addElapsedTime")){
            return commands.get("addElapsedTime");
        }
        if(command.equals("showDashboard")){
            return commands.get("showDashboard");
        }
        if(command.equals("changeInvolvement")){
            return commands.get("changeInvolvement");
        }
        return null;
    }
}
