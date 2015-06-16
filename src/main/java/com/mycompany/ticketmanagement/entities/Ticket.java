/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanf_000
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByTicketId", query = "SELECT t FROM Ticket t WHERE t.ticketId = :ticketId"),
    @NamedQuery(name = "Ticket.findByDescription", query = "SELECT t FROM Ticket t WHERE t.description = :description"),
    @NamedQuery(name = "Ticket.findByNote", query = "SELECT t FROM Ticket t WHERE t.note = :note"),
    @NamedQuery(name = "Ticket.findByDateCreated", query = "SELECT t FROM Ticket t WHERE t.dateCreated = :dateCreated"),
    @NamedQuery(name = "Ticket.findByToSolveDate", query = "SELECT t FROM Ticket t WHERE t.toSolveDate = :toSolveDate")})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long ticketId;
    private String description;
    private String note;
    @ManyToOne
    @JoinColumn(name="created_by")
    private LoginInfo createdBy;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name="status")
    private String status;
    @Column(name = "to_solve_date")
    @Temporal(TemporalType.DATE)
    private Date toSolveDate;
    @ManyToOne
    @JoinColumn(name="assigned_user")
    private LoginInfo assignedUser; 

    public Ticket() {
    }

    public Ticket(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(Long ticketId, String description, String note, LoginInfo createdBy, String status, LoginInfo assignedUser, Date dateCreated, Date toSolveDate) {
        this.ticketId = ticketId;
        this.description = description;
        this.note = note;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.toSolveDate = toSolveDate;
        this.assignedUser = assignedUser;
        this.status = status;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public LoginInfo getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LoginInfo createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getToSolveDate() {
        return toSolveDate;
    }

    public void setToSolveDate(Date toSolveDate) {
        this.toSolveDate = toSolveDate;
    }

    public LoginInfo getAssignedUser() {
        return assignedUser;
    }
    
    public void setAssignedUser(LoginInfo assignedUser) {
        this.assignedUser = assignedUser;
    }
}
