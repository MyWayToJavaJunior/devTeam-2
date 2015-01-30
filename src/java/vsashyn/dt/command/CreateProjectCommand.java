/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vsashyn.dt.dao.DAOFactory;
import vsashyn.dt.dao.DAOManager;
import vsashyn.dt.model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsashyn.dt.dao.ProjectDAO;
import vsashyn.dt.dao.ProjectStaffDAO;
import vsashyn.dt.model.ProjectStaff;

/**
 *
 * @author vsa
 */
public class CreateProjectCommand implements Command{
    
    private static final Logger LOG 
            = LogManager.getLogger(CreateProjectCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession(false);
        //int idSpecification = Integer.valueOf(request.getParameter("idSpecification"));
        Cookie[] cookies = request.getCookies();
        int idSpec=-1;
        for(Cookie cook: cookies){
            if(cook.getName().equals("idSpecForPrepare")){
                idSpec=Integer.valueOf(cook.getValue());
            }
        }
        String titleProject = request.getParameter("nameProject");
        int billProject = Integer.valueOf(request.getParameter("billProject"));
        
        Map <String, String[]> parameters = request.getParameterMap();
        int[] idWorkers = new int[0];
        
        if(parameters.get("workerIDs") != null){
            LOG.info("next array" + parameters.get("workerIDs").length);
            String[] idWorkerParams = parameters.get("workerIDs");
            idWorkers = new int[parameters.get("workerIDs").length];
            for(int i=0; i<idWorkers.length; i++){
                idWorkers[i] = Integer.valueOf(idWorkerParams[i]);
            }
        }
        
        DAOFactory daoFactory = new DAOFactory();
        DAOManager daoManager = daoFactory.getDaoManager();
        ProjectDAO projectDao = daoManager.getProjectDao();
        ProjectStaffDAO projectStaffDao = daoManager.getProjectStaffDao();
        //create project   
        Project project = new Project();
        project.setTitle(titleProject);
        project.setBill(billProject);
        project.setIdSpecification(idSpec);
        projectDao.create(project);
        //add find project ID 
        LOG.info("created new project : " + project.getTitle());
        //add staff to project, if idWorkers not null
        if(idWorkers.length>0){
            ProjectStaff[] workersToProject = new ProjectStaff[idWorkers.length];
            for(int idItem=0; idItem < idWorkers.length; idItem++) {
                ProjectStaff projectStaff = new ProjectStaff();
                projectStaff.setIdPerson(idItem);
                projectStaff.setIdProject(project.getId());
                workersToProject[idItem] = projectStaff;
            }
        projectStaffDao.create(workersToProject);
        LOG.info("Adding array of workers to project");
        }
        
        daoManager.endConnectionScope();
        return "successfull";
    }
    
}
