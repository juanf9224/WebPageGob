/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanf_000
 */
@Entity
@Table(name = "login_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginInfo.findAll", query = "SELECT l FROM LoginInfo l"),
    @NamedQuery(name = "LoginInfo.findByLoginInfoId", query = "SELECT l FROM LoginInfo l WHERE l.loginInfoId = :loginInfoId"),
    @NamedQuery(name = "LoginInfo.findByUsernamePwdEmail", query = "SELECT l FROM LoginInfo l WHERE l.username = :username and l.pwd = :pwd"),
    @NamedQuery(name = "LoginInfo.findByUsername", query = "SELECT l.loginInfoId FROM LoginInfo l WHERE l.username = :username"),
    @NamedQuery(name = "LoginInfo.findByUsernameAndDept", query = "SELECT l FROM LoginInfo l WHERE l.role = 'admin'"),
    @NamedQuery(name = "LoginInfo.findByPwd", query = "SELECT l FROM LoginInfo l WHERE l.pwd = :pwd"),
    @NamedQuery(name = "LoginInfo.findByUserI", query = "SELECT l FROM LoginInfo l WHERE l.userInfoList = :userInfoList"),
    @NamedQuery(name = "LoginInfo.findByRole", query = "SELECT l FROM LoginInfo l WHERE l.role = :role")})
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "login_info_id")
    private Long loginInfoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "role")
    private String role = "user";
    @OneToMany(cascade=CascadeType.ALL, mappedBy="loginInfo1")
    private List<UserInfo> userInfoList;

    

    
    public LoginInfo() {
    }

    public LoginInfo(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public LoginInfo(Long loginInfoId, String username, String pwd, String role) {
        this.loginInfoId = loginInfoId;
        this.username = username;
        this.pwd = pwd;
        this.role = role;
    }
    
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }
    
    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
