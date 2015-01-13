/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.model;

import java.io.Serializable;

/**
 *
 * @author vsa
 */
public class Project implements Serializable {
    
    int id;
    String title;
    int idSpecification;
    int bill;

    public Project (){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(int idSpecification) {
        this.idSpecification = idSpecification;
    }

  
}
