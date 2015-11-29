/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.entities;

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
    @NamedQuery(name = "UserInfo.findByLastname", query = "SELECT u FROM UserInfo u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserInfo.findByAge", query = "SELECT u FROM UserInfo u WHERE u.age = :age"),
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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "age")
    private Integer age;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    
    @OneToOne(targetEntity = LoginInfo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "login_info_id", referencedColumnName = "login_info_id")
    private LoginInfo loginInfo1;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departmentID;

    public UserInfo() {
    }

    public UserInfo(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public UserInfo(Long userInfoId, String name, Integer age, Date dateCreated) {
        this.userInfoId = userInfoId;
        this.name = name;
        this.age = age;
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
    

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
