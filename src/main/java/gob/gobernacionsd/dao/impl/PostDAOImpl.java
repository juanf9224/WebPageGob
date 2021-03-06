/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao.impl;

import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.entities.Post;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import gob.gobernacionsd.dao.PostDAO;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author juanf_000
 */
@ApplicationScoped
public class PostDAOImpl implements PostDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gobernacion_sd_unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    //Create new ticket...
    @Override
    public Post create(Post p) {
        
        
        try {
            Post post = new Post();
            long login = findUser(p.getCreatedBy().getUsername());
            LoginInfo li = em.find(LoginInfo.class, login);
            post.setDateCreated(p.getDateCreated());
            post.setTitle(p.getTitle());
            post.setPost(p.getPost());
            post.setImagePath(p.getImagePath());
            post.setPreviewName(p.getPreviewName());
            post.setNote(p.getNote());
            post.setCreatedBy(li);
            tx.begin();
            em.persist(post);
            em.flush();
            tx.commit();
            return post;
        } catch (Exception e) {
            e.toString();
            return null;
        }

    }

    // find username in the db that match the received username from create method and get its corresponding id...
    @Override
    public long findUser(String username) {
        try {
            long id = (long) em.createNamedQuery("LoginInfo.findByUsername").setParameter("username", username).getSingleResult();

            return id;
        } catch (Exception e) {
            e.toString();
            return 0;
        }

    }

    //Look up in the db for the required Post...
    @Override
    public Post retreive(Post p) {
        try {

            Post pst = em.find(Post.class, p.getPostId());

            return pst;

        } catch (Exception e) {
            e.toString();
            return null;
        }
    }
    
    public Post find(long id){
        
        Post pst = em.find(Post.class, id);
        return pst;
    }
    

    //Update method for the Posts...
    @Override
    public Post update(Post p) {
        try {
            Post post = em.find(Post.class, p.getPostId());
            tx.begin();
            post.setTitle(p.getTitle());
            post.setPost(p.getPost());
            post.setImagePath(p.getImagePath());
            post.setNote(p.getNote());
            tx.commit();
            return post;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    //delete method for the Posts...
    @Override
    public Post delete(Post t) {
        try {
            Post post = em.find(Post.class, t.getPostId());
            
            tx.begin();
            em.remove(post);
            em.flush();
            tx.commit();

            return post;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    //find all Posts in order to show them in a datatable on the view page...
    @Override
    public List<Post> findAll() {
        List<Post> all = null;
        try {

            TypedQuery<Post> tq = em.createNamedQuery("Post.findAll", Post.class);

            all = tq.getResultList();

            return all;

        } catch (Exception e) {
            e.toString();
            return null;
        }

    }

    //Test method to get the max id of an entity instance...
    private Long getMaxId(String select_maxtid_FROM_Post_p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
