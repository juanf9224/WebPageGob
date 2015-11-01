package com.mycompany.ticketmanagement.entities;

import com.mycompany.ticketmanagement.entities.UserInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-01T18:25:57")
@StaticMetamodel(LoginInfo.class)
public class LoginInfo_ { 

    public static volatile SingularAttribute<LoginInfo, String> role;
    public static volatile SingularAttribute<LoginInfo, Long> loginInfoId;
    public static volatile ListAttribute<LoginInfo, UserInfo> userInfoList;
    public static volatile SingularAttribute<LoginInfo, String> pwd;
    public static volatile SingularAttribute<LoginInfo, String> username;

}