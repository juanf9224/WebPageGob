/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class HomeBean implements Serializable {
    
    
    public HomeBean() {
    }
    
      public void viewLogin() {
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentWidth", "400");

        RequestContext.getCurrentInstance().openDialog("login", options, null);
    }
}
