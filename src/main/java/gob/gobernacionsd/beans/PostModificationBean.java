/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import gob.gobernacionsd.entities.Post;
import gob.gobernacionsd.servicebeans.PostServiceBean;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author JBG INC
 */
@Named
@ApplicationScoped
public class PostModificationBean implements Serializable{
    
    @Inject
    PostServiceBean tsb;
    @Inject
    LoginBean login;
    private long id;
    private String title;
    private String post;
    private String note;
    private UploadedFile image;
    private String imagePath;
    
    public PostModificationBean(){
        
    }

    public PostServiceBean getTsb() {
        return tsb;
    }

    public void setTsb(PostServiceBean tsb) {
        this.tsb = tsb;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String postEdit(long id) {
        try {
            tsb.find(id);
            title = tsb.find(id).getTitle();
            post = tsb.find(id).getPost();
            note = tsb.find(id).getNote();
            imagePath = tsb.find(id).getImagePath();
            this.id = id;

            return "post-edit?faces-redirect=true";
        } catch (Exception e) {
            e.toString();
            FacesMessage msg = new FacesMessage("Could not retreive the specified post");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "posts-management-view";
        }

    }
    
    
    //Update Post...
    public void update() {
        Post p = new Post();
        

        p.setPostId(id);
        p.setTitle(title);
        p.setPost(post);
        p.setImagePath(imagePath);
        p.setNote(note);

        tsb.updatePost(p);
    }
    
}
