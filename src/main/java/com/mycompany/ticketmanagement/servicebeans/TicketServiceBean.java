/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.servicebeans;

import com.mycompany.ticketmanagement.dao.impl.TicketDAOImpl;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.entities.Ticket;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author juanf_000
 */
@ManagedBean
@ApplicationScoped
public class TicketServiceBean {

    TicketDAOImpl tdi;
    /**
     * Creates a new instance of TicketServiceBean
     */
    public TicketServiceBean() {
    }
    
    public TicketServiceBean(TicketDAOImpl tdi) {
        
        this.tdi = tdi;
    }
    
    public Ticket createTicket(Ticket t){
        return tdi.create(t);
    }
    
    public List<Ticket> findAll(){
        return tdi.findAll();
    }
    
    public List<LoginInfo> assignedUser(){
        return tdi.assignedUser();
    }
    
    public Ticket updateTicket(Ticket t){
        return tdi.update(t);
    } 
    
    public Ticket remove(Ticket t){
        
        return tdi.delete(t);
        
    }
    
    public long getCount(){
        return tdi.count();
    }
            
    
}
