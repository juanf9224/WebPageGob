/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanf_000
 */
@Entity
@Table(name = "user_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM UserInfo u"),
    @NamedQuery(name = "UserInfo.findByDepartment", query = "SELECT u.departmentID.departmentId FROM UserInfo u where u.departmentID.department = :department"),
    @NamedQuery(name = "UserInfo.findByDepartmentID", query = "SELECT u.departmentID.department FROM UserInfo u "),
    @NamedQuery(name = "UserInfo.findByUserInfoId", query = "SELECT u FROM UserInfo u WHERE u.userInfoId = :userInfoId"),
    @NamedQuery(name = "UserInfo.findByName", query = "SELECT u FROM UserInfo u WHERE u.name = :name"),
    @NamedQuery(name = "UserInfo.findByEmail", query = "SELECT u FROM UserInfo u WHERE u.email = :email"),   
    @NamedQuery(name = "UserInfo.findByDateCreated", query = "SELECT u FROM UserInfo u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "UserInfo.findByLogin", query = "SELECT u FROM UserInfo u WHERE u.loginInfo1 = :login")})

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_info_id")
    private Long userInfoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToOne(targetEntity=LoginInfo.class,cascade=CascadeType.ALL)
    @JoinColumn(name="login_info_id", referencedColumnName = "login_info_id")
    private LoginInfo loginInfo1;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department departmentID;

    
    
    public UserInfo() {
    }

    public UserInfo(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public UserInfo(Long userInfoId, String name, String email, Date dateCreated) {
        this.userInfoId = userInfoId;
        this.name = name;
        this.email = email;
        this.dateCreated = dateCreated;
    }
    
    public Department getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Department departmentID) {
        this.departmentID = departmentID;
    }

    public LoginInfo getLoginInfo1() {
        return loginInfo1;
    }

    public void setLoginInfo1(LoginInfo loginInfo1) {
        this.loginInfo1 = loginInfo1;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
}
