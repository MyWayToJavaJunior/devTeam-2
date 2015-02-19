/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsashyn.dt.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author vsa
 */
public class PoweredByTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException{
        
            JspWriter out = pageContext.getOut();
        try {
            out.write("Powered by sashyn.v@gmail.com");
        } catch (IOException ex) {
            Logger.getLogger(PoweredByTag.class.getName()).log(Level.SEVERE, null, ex);
        }
            return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
