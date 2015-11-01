package com.mycompany.ticketmanagement.entities;

import com.mycompany.ticketmanagement.entities.Department;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-01T18:25:57")
@StaticMetamodel(UserInfo.class)
public class UserInfo_ { 

    public static volatile SingularAttribute<UserInfo, Date> dateCreated;
    public static volatile SingularAttribute<UserInfo, LoginInfo> loginInfo1;
    public static volatile SingularAttribute<UserInfo, Department> departmentID;
    public static volatile SingularAttribute<UserInfo, String> name;
    public static volatile SingularAttribute<UserInfo, String> email;
    public static volatile SingularAttribute<UserInfo, Long> userInfoId;

}