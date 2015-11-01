package com.mycompany.ticketmanagement.entities;

import com.mycompany.ticketmanagement.entities.LoginInfo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-01T18:25:57")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, String> note;
    public static volatile SingularAttribute<Ticket, Date> dateCreated;
    public static volatile SingularAttribute<Ticket, LoginInfo> createdBy;
    public static volatile SingularAttribute<Ticket, String> description;
    public static volatile SingularAttribute<Ticket, Date> toSolveDate;
    public static volatile SingularAttribute<Ticket, LoginInfo> assignedUser;
    public static volatile SingularAttribute<Ticket, Long> ticketId;
    public static volatile SingularAttribute<Ticket, String> status;

}