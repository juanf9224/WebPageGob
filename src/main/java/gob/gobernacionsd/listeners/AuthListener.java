/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.listeners;

/**
 *
 * @author juanf_000
 */
import gob.gobernacionsd.beans.LoginBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

/**
 *
 * @author juanf_000
 */
public class AuthListener implements PhaseListener {

    
    private static final String USER_LOGIN_OUTCOME = "non-responsive-login";
    @Inject LoginBean login;
    
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
        /*if(path.startsWith("/admin/") && !login.getRole().equals("admin") || login.getRole() == null){
            return (!"/index.xhtml".equals(path) && !"/templates/forms/non-responsive-login-form.xhtml".equals(path) && !"/templates/forms/login-form.xhtml".equals(path));              
        }else{
        
        }*/
        return (!"/index.xhtml".equals(path) && !"/templates/forms/non-responsive-login-form.xhtml".equals(path) && !"/templates/forms/login-form.xhtml".equals(path));              
    }
    
    
}

