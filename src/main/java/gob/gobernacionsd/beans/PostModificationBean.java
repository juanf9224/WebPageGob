/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import gob.gobernacionsd.dao.impl.PostDAOImpl;
import gob.gobernacionsd.entities.Post;
import gob.gobernacionsd.servicebeans.PostServiceBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author JBG INC
 */
@Named("postMB")
@SessionScoped
public class PostModificationBean implements Serializable {

    @Inject
    PostServiceBean psb;
    @Inject
    LoginBean login;
    @Inject
    private Post p;

    public PostModificationBean() {

    }

    @PostConstruct
    public void init() {
        this.psb = new PostServiceBean(new PostDAOImpl());
    }

    public PostServiceBean getPsb() {
        return psb;
    }

    public void setPsb(PostServiceBean psb) {
        this.psb = psb;
    }

    public Post getP() {
        return p;
    }

    public void setP(Post p) {
        this.p = p;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public String readPost(long id) {
        try {
            this.p = psb.find(id);
            return "read-post-view";

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Could not open the post!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "";
        }
    }

    public void onRowSelect(SelectEvent event) {
        try {
            this.p = (Post) event.getObject();
            String url = "../forms/post-edit.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Could not retreive the specified post");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            e.toString();

        }

    }

    //Update Post...
    public String update() {

        try {
            psb.updatePost(p);
            FacesMessage msg = new FacesMessage("Post updated successfully!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "post-management-view";

        } catch (Exception e) {
            e.toString();
            FacesMessage msg = new FacesMessage("Could not retreive the specified post.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "";
        }
    }

}
