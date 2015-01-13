/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.model;

/**
 *
 * @author vsa
 */
public class ElapsedTime {
    int id;
    int project_staff_id;
    int time;                       //in minutes
    
    public ElapsedTime(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_staff_id() {
        return project_staff_id;
    }

    public void setProject_staff_id(int project_staff_id) {
        this.project_staff_id = project_staff_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}
