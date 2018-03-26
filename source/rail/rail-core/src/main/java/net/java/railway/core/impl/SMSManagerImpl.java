package net.java.railway.core.impl;

import static net.java.railway.Constants.COMPANY_DISPLAY_NAME;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.java.railway.Account;
import net.java.railway.Constants.ProcessingStatus;
import net.java.railway.Constants.SmsType;
import net.java.railway.SMS;
import net.java.railway.Ticket;
import net.java.railway.core.AccountManager;
import net.java.railway.core.SMSManager;
import net.java.railway.core.Util;

@Stateless
public class SMSManagerImpl implements SMSManager {

	@PersistenceContext(unitName = "rail-core")
	EntityManager em;

	@EJB
	AccountManager accountManager;

	@Override
	public SMS createSMS(SMS sms) throws Exception {
		em.persist(sms);
		return sms;
	}

	/**
	 * returns all the queued sms
	 * 
	 * @return collection
	 */

	@Override
	public List<SMS> findQueuedSMS() {

		Query query = em.createNamedQuery("findQueuedSMS");

		query.setParameter("processingStatus", ProcessingStatus.INITAL);

		@SuppressWarnings("unchecked")
		List<SMS> list = query.getResultList();

		return list;

	}

	@Override
	public void ticketBooked(Set<Long> pBookedTicket,
			ProcessingStatus processingStatus) {
		String lInqueryForBookedTickets = pBookedTicket.toString()
				.replace("[", "").replace("]", "");

		// updateSMSStatus
		Query query = em.createNamedQuery("updateSMSStatus");

		query.setParameter("processingStatus", processingStatus);
		query.setParameter("id", lInqueryForBookedTickets);

		query.executeUpdate();
	}

	@Override
	public SMS saveTicketSMS(SMS insms, Ticket ticket) throws Exception {

		Account acc = accountManager
				.findAccountById(insms.getAccount().getId());

		StringBuilder sb = new StringBuilder();
		sb.append(COMPANY_DISPLAY_NAME);
		sb.append("\nTicket No : " + ticket.getId());
		sb.append("\nFrom : " + ticket.getFromStation().getStationName());
		sb.append("\nTo : " + ticket.getToStation().getStationName());
		sb.append("\nNu of Tickets : " + ticket.getNoofTickets());
		sb.append("\nRs : " + ticket.getAmount());
		sb.append("\nBal Rs : " + acc.getBalance());
		sb.append("\n" + Util.getDisplayDate(ticket.getCreatedTime()));

		SMS sms = new SMS();
		sms.setBody(sb.toString());
		sms.setMobile(insms.getMobile());
		sms.setProcessingStatus(ProcessingStatus.SUCCESS);
		sms.setType(SmsType.RESPONSE);
		sms.setAccount(insms.getAccount());

		return createSMS(sms);

	}

	@Override
	public SMS findById(Long id) {
		return em.find(SMS.class, id);
	}

}
