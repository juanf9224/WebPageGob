/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.servicebeans;

import gob.gobernacionsd.dao.impl.PostDAOImpl;
import gob.gobernacionsd.entities.Post;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;

/**
 *
 * @author juanf_000
 */
@Named
@ApplicationScoped
public class PostServiceBean implements Serializable{

    @Inject @Transient PostDAOImpl pdi;
    /**
     * Creates a new instance of TicketServiceBean
     */
    public PostServiceBean() {
    }
    
    public PostServiceBean(PostDAOImpl pdi) {
        
        this.pdi = pdi;
    }
    
    public Post createPost(Post p){
        return pdi.create(p);
    }
    
    public List<Post> findAll(){
        return pdi.findAll();
    }
    
    public Post updatePost(Post p){
        return pdi.update(p);
    } 
    
    public Post remove(Post p){
        
        return pdi.delete(p);
        
    } 
    
    public Post find(long id){
        return pdi.find(id);
    }
    
}
