/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.listeners;

/**
 *
 * @author juanf_000
 */
import com.mycompany.ticketmanagement.beans.LoginBean;
import static com.mycompany.ticketmanagement.beans.LoginBean.USER_SESSION_KEY;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

/**
 *
 * @author juanf_000
 */
public class AuthListener implements PhaseListener {

    
    private static final String USER_LOGIN_OUTCOME = "login";
    
    
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
       
        if (userExists(context)) {
            return;
        } else {            
            // send the user to the login view
            if (requestingSecureView(context)) {
                context.responseComplete();              
                context.getApplication().
                        getNavigationHandler().handleNavigation(context, 
                                                                null, 
                                                                USER_LOGIN_OUTCOME);
            }
        }
    }
    
    /**
     * <p>This is a no-op.</p>
     * @param event
     */
    @Override
    public void beforePhase(PhaseEvent event) {        
    }

    /**
     * @return <code>PhaseId.RESTORE_VIEW</code>
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    
    /**
     * <p>Determine if the user has been authenticated by checking the session
     * for an existing <code>user</code> object.</p>
     * 
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the user has been authenticated, otherwise
     *  <code>false</code>
     */
    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(LoginBean.USER_SESSION_KEY));
    }
    
    /**
     * <p>Determines if the requested view is one of the login pages which will
     * allow the user to access them without being authenticated.</p>
     *
     * <p>Note, this implementation most likely will not work if the 
     * <code>FacesServlet</code> is suffix mapped.</p>
     *
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the requested view is allowed to be accessed
     *  without being authenticated, otherwise <code>false</code>
     */
    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();       
        String path = extContext.getRequestPathInfo();
        return (!"/index.xhtml".equals(path) && !"/create.xhtml".equals(path));              
    }
    
    
}

