/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import gob.gobernacionsd.dao.impl.UserDAOImpl;
import gob.gobernacionsd.entities.Department;
import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.entities.UserInfo;
import gob.gobernacionsd.servicebeans.UserServiceBean;
import org.primefaces.context.RequestContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author juanf_000
 */
@Named
@RequestScoped
public class UserBean implements Serializable{

    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String department;
    private String username;
    private String pwd;
    private List<Department> departmentList;
    private List<UserInfo> all;
    private List<UserInfo> filteredUsers;
    @Inject UserServiceBean usb;
    
    
    
    /**
     * Creates a new instance of UserBean
     */
    
    @PostConstruct
    public void init(){
        this.usb = new UserServiceBean(new UserDAOImpl());
        this.departmentList = usb.findDept();
        this.all = usb.findAll();
    }
    
    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public UserServiceBean getUsb() {
        return usb;
    }

    public void setUsb(UserServiceBean usb) {
        this.usb = usb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<UserInfo> getAll() {
        return all;
    }

    public void setAll(List<UserInfo> all) {
        this.all = all;
    }

    public List<UserInfo> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<UserInfo> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
    
    public String createService(){
        try{
        UserInfo ui = new UserInfo();
        LoginInfo li = new LoginInfo();
        Department dept = new Department();
        
        li.setUsername(username);
        li.setPwd(pwd);
        li.setEmail(email);
        dept.setDepartment(department);
        ui.setDepartmentID(dept);
        ui.setName(name);
        ui.setLastName(lastName);
        ui.setLoginInfo1(li);  
        usb.create(ui);
        FacesMessage msg = new FacesMessage("User created");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "admin-welcome.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.toString();
            FacesMessage msg = new FacesMessage("User could not be created");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
    
    //Update user...
    public void onRowEdit(RowEditEvent event) {
        
        try{
        UserInfo u =(UserInfo) event.getObject();
        LoginInfo li = new LoginInfo();
        Department d = new Department();
        
        li.setLoginInfoId(u.getLoginInfo1().getLoginInfoId());
        li.setUsername(username);
        
        li.setPwd(pwd);
        d.setDepartmentId(u.getDepartmentID().getDepartmentId());
        d.setDepartment(department);
        u.setLoginInfo1(li);
        u.setDepartmentID(d);
        usb.update(u);
        
        FacesMessage msg = new FacesMessage("User Edited", ((UserInfo) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
            e.toString();
        }
    }
     
    //Cancel update on user...
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Edit Cancelled", ((UserInfo) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String remove(long id){
        try{
            
            UserInfo u = new UserInfo();
            u.setUserInfoId(id);
            usb.delete(u);
            FacesMessage msg = new FacesMessage("User deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "listusers.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.toString();
            return null;
        }
    }

    public void viewCreateCustomized() {
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("width", 700);

        RequestContext.getCurrentInstance().openDialog("create-user", options, null);
    }
    
    
}
