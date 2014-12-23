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

/**
 *
 * @author vsa
 */
public class CommandFactory {
    Map<String, Command> commands = new HashMap<>(); 
    static{
     //   commands.put("find", );
    }
    
    public CommandFactory() throws IOException{
        Properties props = new Properties();
        props.load(new FileInputStream(
                new File("config/commands.properties")));
    }
}
