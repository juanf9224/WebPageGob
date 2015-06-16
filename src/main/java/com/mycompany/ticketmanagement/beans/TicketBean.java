/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.beans;


import com.mycompany.ticketmanagement.dao.impl.TicketDAOImpl;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.entities.Ticket;
import com.mycompany.ticketmanagement.servicebeans.TicketServiceBean;
import org.primefaces.context.RequestContext;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author juanf_000
 */
@ManagedBean
@ViewScoped
public class TicketBean implements Serializable {

    @ManagedProperty("#{ticketServiceBean}")
    private TicketServiceBean tsb;
    @ManagedProperty("#{loginBean}")
    private LoginBean login;
    private String description;
    private String note;
    private String status;
    private Date toSolveDate;
    private String assignedUser;
    private List<Ticket> tickets;
    private List<Ticket> filteredTickets;
    private List<LoginInfo> assignedUserList;
    private Ticket selectedTicket; 

    
    /**
     * Creates a new instance of TicketBean...
     */
    public TicketBean() {
    }
    
    @PostConstruct
    public void init(){
        this.tsb = new TicketServiceBean(new TicketDAOImpl());
        this.tickets = tsb.findAll();
        this.assignedUserList = tsb.assignedUser();
    }

    
    //getters setters...
    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public TicketServiceBean getTsb() {
        return tsb;
    }

    public void setTsb(TicketServiceBean tsb) {
        this.tsb = tsb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Date getToSolveDate() {
        return toSolveDate;
    }

    public void setToSolveDate(Date toSolveDate) {
        this.toSolveDate = toSolveDate;
    }
    
     

    public List<LoginInfo> getAssignedUserList() {
        return assignedUserList;
    }

    public void setAssignedUserList(List<LoginInfo> assignedUserList) {
        this.assignedUserList = assignedUserList;
    }

    public List<Ticket> getFilteredTickets() {
        return filteredTickets;
    }

    public void setFilteredTickets(List<Ticket> filteredTickets) {
        this.filteredTickets = filteredTickets;
    }

    public Ticket getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(Ticket selectedTicket) {
        this.selectedTicket = selectedTicket;
    }
    
    
    //Create Ticket...
    public String createTicket(){
        try{
        Ticket tick = new Ticket();
        LoginInfo li = new LoginInfo();
        
        li.setUsername(login.getUsername());
        tick.setDescription(description);
        tick.setNote(note);
        tick.setCreatedBy(li);
        tick.setDateCreated(new Date());
        tsb.createTicket(tick);
        note = null;
        description = null;
        FacesMessage msg = new FacesMessage("Ticket created");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "createticket";
        }catch(Exception e){
            e.toString();
            FacesMessage msg = new FacesMessage("Ticket could not be created");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
    
    
    //Update Ticket...
    public void onRowEdit(RowEditEvent event) {
        
        try{
            LoginInfo li = new LoginInfo();
        
        li.setUsername(assignedUser);
        Ticket t =(Ticket) event.getObject();
        t.setAssignedUser(li);
        tsb.updateTicket(t);
        
        FacesMessage msg = new FacesMessage("Ticket Edited", ((Ticket) event.getObject()).getTicketId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
            e.toString();
        }
    }
     
    //Cancel update on ticket...
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Ticket) event.getObject()).getTicketId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    //delete Ticket...
    public String remove(long id){
        try{
            
            Ticket t = new Ticket();
            
            t.setTicketId(id);
            tsb.remove(t);
            FacesMessage msg = new FacesMessage("Ticket deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "welcome.xhtml?faces-redirect=true";
        }catch(Exception e){
            e.toString();
            return null;
        }
    }
    //Dialog to create a new Ticket...
    public void viewTicketCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("createticket", options, null);
    }
}
