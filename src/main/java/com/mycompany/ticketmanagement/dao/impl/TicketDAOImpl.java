/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.dao.impl;

import com.mycompany.ticketmanagement.dao.TicketDAO;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.entities.Ticket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanf_000
 */
public class TicketDAOImpl implements TicketDAO{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TicketManagement_war_1.0-SNAPSHOTPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    
    //Create new ticket...
    @Override
    public Ticket create(Ticket t) {
       
        try{
            Ticket ticket = new Ticket();
            long login = findUser(t.getCreatedBy().getUsername());
            LoginInfo li = em.find(LoginInfo.class, login);
            ticket.setDateCreated(t.getDateCreated());
            ticket.setDescription(t.getDescription());
            ticket.setNote(t.getNote());
            ticket.setCreatedBy(li);
            tx.begin();
            em.persist(ticket);
            tx.commit();
            
            return ticket;
        }catch(Exception e){
            e.toString();
            return null;
        }
        
        
    }
    
    
     // find username in the db that match the received username from create method and get its corresponding id...
     @Override
     public long findUser(String username){
        
        try{
            long id = (long)em.createNamedQuery("LoginInfo.findByUsername").setParameter("username", username).getSingleResult();
            
            return id;
        }catch(Exception e){
            e.toString();
            return 0;
        }
        
    }

     //Look up in the db for the required ticket...
    @Override
    public Ticket retreive(Ticket t) {
        try{
            
            Ticket tick = em.find(Ticket.class, t.getTicketId());
            
            return tick;
            
        }catch(Exception e){
            e.toString();
            return null;
        }
    }

    //Update method for the tickets...
    @Override
    public Ticket update(Ticket t) {
        try{
            Ticket ticket = em.find(Ticket.class, t.getTicketId());
            long login = findUser(t.getAssignedUser().getUsername());
            LoginInfo li = em.find(LoginInfo.class, login);
            tx.begin();
            ticket.setToSolveDate(t.getToSolveDate());
            ticket.setDescription(t.getDescription());
            ticket.setNote(t.getNote());
            ticket.setStatus(t.getStatus());
            ticket.setAssignedUser(li);
            tx.commit();
            
            return ticket;
        }catch(Exception e){
            e.toString();
            return null;
        }
    }

    //delete method for the tickets...
    @Override
    public Ticket delete(Ticket t) {
        
        try{
        Ticket ticket = em.find(Ticket.class, t.getTicketId());
        
        tx.begin();
        em.remove(ticket);
        tx.commit();
        
        return ticket;
        }catch(Exception e){
        e.toString();
        return null;
        }
    }

    
    //find all tickets in order to show the in a datatable on the view page...
    @Override
    public List<Ticket> findAll() {
        
        List<Ticket> all = null;
        try{
            
            
            TypedQuery<Ticket> tq = em.createNamedQuery("Ticket.findAll", Ticket.class);
            
            all = tq.getResultList();
            
            return all;
            
        }catch(Exception e){
            e.toString();
            return null;
        }
        
        
    }
    
    //test method to count all tickets. Not used...
    @Override
    public long count(){
        Query query = em.createQuery("select count(1) from Ticket t");
        long count = (long) query.getSingleResult();
        
        return count;
    }
    
    //List that retrevies all usernames with "Admin" role in the login information entity so that the supervisor could assign the tickets to any of the retreived usernames.
    @Override
    public List<LoginInfo> assignedUser(){
        
        try{
            List<LoginInfo> assigned = null;
            
            TypedQuery<LoginInfo> tq = em.createNamedQuery("LoginInfo.findByUsernameAndDept", LoginInfo.class);
            
            assigned = tq.getResultList();
            
            return assigned;
        }catch(Exception e){
            e.toString();
            return null;
        }
        
    }
    
    //Test List that retreives tickets by department. Not used...
    public List<Ticket> findTicketsByDept(String department){
        
        try{
        List<Ticket> byDept = null;
        
        TypedQuery<Ticket> tq = em.createNamedQuery("UserInfo.", Ticket.class).setParameter("dept", department);
        
        byDept = tq.getResultList();
        
        return byDept;
        }catch(Exception e){
            e.toString();
            return null;
        }
        
    }

    //Test method to get the max id of an entity instance...
    private Long getMaxId(String select_maxtid_FROM_Ticket_t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
