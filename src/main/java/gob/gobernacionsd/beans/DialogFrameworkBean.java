/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author juanf_000
 */
@Named(value = "dialogFrameworkBean")
@ApplicationScoped
public class DialogFrameworkBean {

    /**
     * Creates a new instance of DialogFrameworkBean
     */
    public DialogFrameworkBean() {
    }
    
    
    //PrimeFaces Dialog Framework...
    public void viewCreateCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 290);

        RequestContext.getCurrentInstance().openDialog("login", options, null);
    }
    
}
