/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanf_000
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findBypostId", query = "SELECT p FROM Post p WHERE p.postId = :postId"),
    @NamedQuery(name = "Post.findByPost", query = "SELECT p FROM Post p WHERE p.post = :post"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByNote", query = "SELECT p FROM Post p WHERE p.note = :note"),
    @NamedQuery(name = "Post.findByDateCreated", query = "SELECT p FROM Post p WHERE p.dateCreated = :dateCreated")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long postId;
    @Column(name = "title")
    private String title;
    @Column(name = "post")
    private String post;
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name="created_by")
    private LoginInfo createdBy;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Post() {
    }

    public Post(Long PostId) {
        this.postId = PostId;
    }

    public Post(Long PostId, String title, String post, String note, LoginInfo createdBy, String status, LoginInfo assignedUser, Date dateCreated, Date toSolveDate) {
        this.postId = PostId;
        this.title = title;
        this.post = post;
        this.note = note;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long ticketId) {
        this.postId = ticketId;
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

    public LoginInfo getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LoginInfo createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
