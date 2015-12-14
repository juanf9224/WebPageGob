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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;

/**
 *
 * @author juanf_000
 */
@Named
@ViewScoped
public class PostBean implements Serializable {

    @Inject
    PostServiceBean psb;
    @Inject
    LoginBean login;
    @Inject
    PostModificationBean pmb;
    private long id;
    private String title;
    private String post;
    private String note;
    private UploadedFile image;
    private String imagePath;
    private String previewName;
    private List<Post> posts;
    private List<Post> filteredPosts;
    private Post selectedPost;

    /**
     * Creates a new instance of PostBean...
     */
    public PostBean() {
    }

    @PostConstruct
    public void init() {
        this.psb = new PostServiceBean(new PostDAOImpl());
        this.posts = psb.findAll();
    }

    //getters setters...
    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public PostServiceBean getPsb() {
        return psb;
    }

    public void setTsb(PostServiceBean psb) {
        this.psb = psb;
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

    public String getPreviewName() {
        return previewName;
    }

    public void setPreviewName(String previewName) {
        this.previewName = previewName;
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

    public void HandleFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        this.image = event.getFile();
        try {
            if (image != null) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String directory = externalContext.getInitParameter("uploadDirectory");
                String imageName = FilenameUtils.getName(image.getFileName());
                InputStream input = image.getInputstream();
                OutputStream output = new FileOutputStream(new File(directory, imageName));
                try {
                    IOUtils.copy(input, output);
                } finally {
                    IOUtils.closeQuietly(input);
                    IOUtils.closeQuietly(output);
                }
                FacesMessage msg = new FacesMessage("Imagen cargada");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);

                //Image Resizing
                int width = 280;
                int height = 200;
                previewName = "preview" + imageName;
                BufferedImage originalImage = ImageIO.read(new File(directory, imageName));
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                BufferedImage resizeImageJpg = resizeImage(originalImage, type, width, height);
                ImageIO.write(resizeImageJpg, "jpg", new File(directory, previewName));
                
                msg = new FacesMessage("Preview cargado");
                context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);

                imagePath = imageName;
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error al cargar imagen" + e.toString());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            e.toString();
        }

    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, width, null);
        g.dispose();

        return resizedImage;
    }

    //Create Post...
    public String createPost() {
        try {

            Post pst = new Post();
            LoginInfo li = new LoginInfo();

            li.setUsername(login.getUsername());
            pst.setTitle(title);
            pst.setPost(post);
            pst.setImagePath(imagePath);
            pst.setPreviewName(previewName);
            pst.setNote(note);
            pst.setCreatedBy(li);
            pst.setDateCreated(new Date());
            psb.createPost(pst);
            note = "";
            title = "";
            post = "";
            imagePath = "";
            FacesMessage msg = new FacesMessage("Post created");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "create-post";
        } catch (Exception e) {
            e.toString();
            FacesMessage msg = new FacesMessage("Post could not be created");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    /*public void postEdit(long id){
        pmb.postEdit(id);
    }*/
    //delete Post...
    public String remove(long id) {
        try {

            Post t = new Post();

            t.setPostId(id);
            psb.remove(t);
            FacesMessage msg = new FacesMessage("Post deleted");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "post-management-view";
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }
}
