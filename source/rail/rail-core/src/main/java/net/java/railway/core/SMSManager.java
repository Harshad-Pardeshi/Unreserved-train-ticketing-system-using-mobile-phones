package net.java.railway.core;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import net.java.railway.Constants.ProcessingStatus;
import net.java.railway.SMS;
import net.java.railway.Ticket;

@Local
public interface SMSManager {

    public SMS createSMS(SMS sms) throws Exception;

    public List<SMS> findQueuedSMS();

    public void ticketBooked(Set<Long> pBookedTicket, ProcessingStatus processingStatus);

    public SMS saveTicketSMS(SMS insms, Ticket ticket) throws Exception;

    public SMS findById(Long id);

}
