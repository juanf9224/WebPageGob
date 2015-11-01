package com.mycompany.ticketmanagement.entities;

import com.mycompany.ticketmanagement.entities.UserInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-01T18:25:57")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile ListAttribute<Department, UserInfo> userInfo;
    public static volatile SingularAttribute<Department, Long> departmentId;
    public static volatile SingularAttribute<Department, String> department;

}