package net.java.railway.core;

import java.util.Date;

import javax.ejb.Local;

import net.java.railway.Ticket;
import net.java.railway.Transaction;

@Local
public interface TicketManager {

    public Ticket createTicket(Ticket ticket, Transaction transaction) throws Exception;

    public double calculateTicketAmount(String from, String to, int noofTickets) throws Exception;

    public Long findNoOfTravellers(String from, String to, Date startDate, Date endDate) throws Exception;

    public Double findTotalRevenue(String from, Date startDate, Date endDate) throws Exception;

}
