package net.java.railway.core.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.java.railway.PriceMaster;
import net.java.railway.RailwayStation;
import net.java.railway.Ticket;
import net.java.railway.Transaction;
import net.java.railway.core.AccountManager;
import net.java.railway.core.StationAndPriceManager;
import net.java.railway.core.TicketManager;
import net.java.railway.core.TransactionManager;

@Stateless
public class TicketManagerImpl implements TicketManager {

	@PersistenceContext(unitName = "rail-core")
	EntityManager em;

	@EJB
	AccountManager accountManager;

	@EJB
	TransactionManager transactionManager;

	@EJB
	StationAndPriceManager stationAndPriceManager;

	@Override
	public Ticket createTicket(Ticket ticket, Transaction transaction)
			throws Exception {

		transactionManager.createTransaction(transaction);
		accountManager.settleAccountBalance(transaction);

		em.persist(ticket);
		return ticket;
	}

	@Override
	public double calculateTicketAmount(String from, String to, int noofTickets)
			throws Exception {
		Query query = em.createNamedQuery("findJourneyPrice");

		query.setParameter("from", from);
		query.setParameter("to", to);
		PriceMaster priceMaster = null;
		try {
			priceMaster = (PriceMaster) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
		}
		double price = priceMaster.getPrice() * noofTickets;

		return price;
	}

	@Override
	public Long findNoOfTravellers(String from, String to, Date startDate,
			Date endDate) throws Exception {

		System.out.println(from);
		System.out.println(to);
		System.out.println(startDate);
		System.out.println(endDate);

		RailwayStation fromStation = stationAndPriceManager
				.findRailwayStationByCode(from);
		RailwayStation toStation = stationAndPriceManager
				.findRailwayStationByCode(to);

		Query query = em.createNamedQuery("findNoOfTravellers");

		query.setParameter("from", fromStation);
		query.setParameter("to", toStation);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		Long noOfTravellers = 0L;

		try {
			noOfTravellers = (Long) query.getSingleResult();

			System.out.println("INFO: " + "noOfTravellers : " + noOfTravellers);
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
		}

		return noOfTravellers;
	}

	@Override
	public Double findTotalRevenue(String from, Date startDate, Date endDate)
			throws Exception {

		RailwayStation fromStation = stationAndPriceManager
				.findRailwayStationByCode(from);

		Query query = em.createNamedQuery("findTotalRevenue");

		query.setParameter("from", fromStation);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		Double totalRevenue = 0D;

		try {
			totalRevenue = (Double) query.getSingleResult();

			System.out.println("INFO: " + "totalRevenue : " + totalRevenue);
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
		}

		return totalRevenue;
	}

}
