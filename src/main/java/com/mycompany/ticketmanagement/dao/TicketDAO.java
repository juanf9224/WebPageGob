/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.dao;

import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.entities.Ticket;
import java.util.List;

/**
 *
 * @author juanf_000
 */
public interface TicketDAO extends GenericDAO<Ticket>{
    public long findUser(String username);
    public long count();
    public List<LoginInfo> assignedUser();
}
