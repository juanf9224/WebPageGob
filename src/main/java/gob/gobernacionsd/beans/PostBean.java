/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;


import gob.gobernacionsd.dao.impl.PostDAOImpl;
import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.entities.Post;
import gob.gobernacionsd.servicebeans.PostServiceBean;
import org.primefaces.context.RequestContext;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author juanf_000
 */
@Named
@ViewScoped
public class PostBean implements Serializable {

    @Inject PostServiceBean tsb;
    @Inject LoginBean login;
    private String title;
    private String post;
    private String note;
    private List<Post> posts;
    private List<Post> filteredPosts;
    private Post selectedPost; 

    
    /**
     * Creates a new instance of TicketBean...
     */
    public PostBean() {
    }
    
    @PostConstruct
    public void init(){
        this.tsb = new PostServiceBean(new PostDAOImpl());
        this.posts = tsb.findAll();
    }

    
    //getters setters...
    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public PostServiceBean getTsb() {
        return tsb;
    }

    public void setTsb(PostServiceBean tsb) {
        this.tsb = tsb;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getFilteredPosts() {
        return filteredPosts;
    }

    public void setFilteredPosts(List<Post> filteredPosts) {
        this.filteredPosts = filteredPosts;
    }

    public Post getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(Post selectedPost) {
        this.selectedPost = selectedPost;
    }
    
    
    //Create Post...
    public String createPost(){
        try{
        Post tick = new Post();
        LoginInfo li = new LoginInfo();
        
        li.setUsername(login.getUsername());
        tick.setTitle(title);
        tick.setNote(note);
        tick.setCreatedBy(li);
        tick.setDateCreated(new Date());
        tsb.createTicket(tick);
        note = null;
        title = null;
        FacesMessage msg = new FacesMessage("Post created");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "create-post";
        }catch(Exception e){
            e.toString();
            FacesMessage msg = new FacesMessage("Post could not be created");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
    
    
    //Update Post...
    public void onRowEdit(RowEditEvent event) {
        
        try{
        Post t =(Post) event.getObject();
        tsb.updateTicket(t);
        
        FacesMessage msg = new FacesMessage("Ticket Edited", ((Post) event.getObject()).getPostId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
            e.toString();
        }
    }
     
    //Cancel update on ticket...
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Post) event.getObject()).getPostId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    //delete Post...
    public String remove(long id){
        try{
            
            Post t = new Post();
            
            t.setPostId(id);
            tsb.remove(t);
            FacesMessage msg = new FacesMessage("Post deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "welcome.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.toString();
            return null;
        }
    }
    //Dialog to create a new Post...
    public void viewTicketCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("createPost", options, null);
    }
}
