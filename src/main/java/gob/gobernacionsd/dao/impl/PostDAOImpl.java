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
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Transient;

/**
 *
 * @author juanf_000
 */
public class PostDAOImpl implements PostDAO {

    @Transient
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gobernacion_sd_unit");
    @Transient
    private EntityManager em;
    @Transient
    private EntityTransaction tx;

    //Create new ticket...
    @Transient
    @Override
    public Post create(Post p) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try {
            Post post = new Post();
            long login = findUser(p.getCreatedBy().getUsername());
            LoginInfo li = em.find(LoginInfo.class, login);
            post.setDateCreated(p.getDateCreated());
            post.setTitle(p.getTitle());
            post.setPost(p.getPost());
            post.setImagePath(p.getImagePath());
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
    @Transient
    @Override
    public long findUser(String username) {
        em = emf.createEntityManager();
        try {
            long id = (long) em.createNamedQuery("LoginInfo.findByUsername").setParameter("username", username).getSingleResult();

            return id;
        } catch (Exception e) {
            e.toString();
            return 0;
        }

    }

    //Look up in the db for the required Post...
    @Transient
    @Override
    public Post retreive(Post p) {
        em = emf.createEntityManager();
        try {

            Post pst = em.find(Post.class, p.getPostId());

            return pst;

        } catch (Exception e) {
            e.toString();
            return null;
        }
    }
    

    //Update method for the Posts...
    @Transient
    @Override
    public Post update(Post p) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try {
            Post post = em.find(Post.class, p.getPostId());
            tx.begin();
            post.setTitle(p.getTitle());
            post.setPost(p.getPost());
            post.setImagePath(p.getImagePath());
            post.setNote(p.getNote());
            em.flush();
            tx.commit();
            return post;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Transient
    //delete method for the Posts...
    @Override
    public Post delete(Post t) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
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

    @Transient
    //find all Posts in order to show them in a datatable on the view page...
    @Override
    public List<Post> findAll() {
        em = emf.createEntityManager();
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

    @Transient
    //Test method to get the max id of an entity instance...
    private Long getMaxId(String select_maxtid_FROM_Post_p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
