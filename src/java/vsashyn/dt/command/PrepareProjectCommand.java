/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.dao.SpecificationDAO;
import vsashyn.dt.dao.StaffDAO;

/**
 *
 * @author vsa
 */
class PrepareProjectCommand implements Command {

    public PrepareProjectCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession(false);
        int idSpecification = Integer.valueOf(request.getParameter("idSpecification"));
        
        Cookie cookie = new Cookie("idSpecForPrepare", Integer.toString(idSpecification));
        response.addCookie(cookie);
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        SpecificationDAO specificationDao = daoManager.getSpecificationDao();
        StaffDAO staffDao = daoManager.getStaffDao();
        request.setAttribute("freeWorkers", staffDao.findAllFree());
        request.setAttribute("specification", specificationDao.findEntityById(idSpecification));
        //show all free developers
        
        daoManager.endConnectionScope();
        return "prepare_project";
    }
    
}
