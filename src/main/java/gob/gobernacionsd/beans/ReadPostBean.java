/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import gob.gobernacionsd.dao.impl.PostDAOImpl;
import gob.gobernacionsd.entities.Post;
import gob.gobernacionsd.servicebeans.PostServiceBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named
@RequestScoped
public class ReadPostBean {
    @Inject PostServiceBean psb;
    @Inject LoginBean login;
    private long id; 
    private Post post;
    
    
    
    public ReadPostBean() {
    }
        
    @PostConstruct
    public void init() {
        this.psb = new PostServiceBean(new PostDAOImpl());
        
    }

    public Post getPost() {        
        return psb.find(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
