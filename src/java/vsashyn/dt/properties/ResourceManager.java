/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author vsa
 */
public enum ResourceManager {
     INSTANCE;
     private ResourceBundle resourceBundle;
     private final String resourceName = "context";
     private ResourceManager(){
         resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
     }
    public void changeResource(Locale locale){
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    } 
    public String getString(String key){
        return resourceBundle.getString(key);
    }
}
