/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author vsa
 */
class LogoutCommand implements Command {

    public LogoutCommand() {
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "index";
    }
}
