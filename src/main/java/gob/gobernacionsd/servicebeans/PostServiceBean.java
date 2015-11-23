/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.servicebeans;

import gob.gobernacionsd.dao.impl.PostDAOImpl;
import gob.gobernacionsd.entities.Post;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author juanf_000
 */
@Named
@ApplicationScoped
public class PostServiceBean {

    PostDAOImpl tdi;
    /**
     * Creates a new instance of TicketServiceBean
     */
    public PostServiceBean() {
    }
    
    public PostServiceBean(PostDAOImpl tdi) {
        
        this.tdi = tdi;
    }
    
    public Post createTicket(Post t){
        return tdi.create(t);
    }
    
    public List<Post> findAll(){
        return tdi.findAll();
    }
    
    public Post updateTicket(Post t){
        return tdi.update(t);
    } 
    
    public Post remove(Post t){
        
        return tdi.delete(t);
        
    }
    
    public long getCount(){
        return tdi.count();
    }
            
    
}
