package net.java.railway.core;

import static net.java.railway.Constants.DEFAULT_MESSAGE;
import static net.java.railway.Constants.HELP_MESSAGE;
import static net.java.railway.Constants.TOKEN_BALANCE;
import static net.java.railway.Constants.TOKEN_BOOK;
import static net.java.railway.Constants.TOKEN_HELP;
import static net.java.railway.Constants.TOKEN_TICKET;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.java.railway.Account;
import net.java.railway.Constants.ProcessingStatus;
import net.java.railway.Constants.SmsType;
import net.java.railway.Constants.TransactionType;
import net.java.railway.ParsedSMS;
import net.java.railway.RailwayStation;
import net.java.railway.SMS;
import net.java.railway.Ticket;
import net.java.railway.Transaction;
import net.java.railway.messaging.SMSHandler;

public class RailwayProcesser {

	private static SMSManager smsManager = Factory.getSMSManager();

	private static StationAndPriceManager stationAndPriceManager = Factory
			.getStationAndPriceManager();

	private static AccountManager accountManager = Factory.getAccountManager();

	private static TicketManager ticketManager = Factory.getTicketManager();

	private static TransactionManager transactionManager = Factory
			.getTransactionManager();

	public Set<SMS> processSMS() throws Exception {

		Set<SMS> setOutSMS = new HashSet<SMS>();
		SMS outsms = null;

		Set<Long> successBookedTicket = new HashSet<Long>();
		Set<Long> failedBookedTicket = new HashSet<Long>();

		java.util.List<SMS> listSms = smsManager.findQueuedSMS();
		System.out.println("INFO :no of queued sms : " + listSms.size());
		for (SMS sms : listSms) {

			ParsedSMS p = new ParsedSMS(sms);
			try {

				outsms = processMenu(p);

				if (outsms != null)
					setOutSMS.add(outsms);

				successBookedTicket.add(p.getId());
				System.out
						.println("INFO Sucessfully booked ticket for sms no: "
								+ p.getId());

			} catch (Exception e) {
				System.out.println("ERROR sms no: " + p.getId() + "\n"
						+ e.getMessage());
				failedBookedTicket.add(p.getId());
				continue;
			}
		}

		try {
			if (successBookedTicket.size() > 0) {
				smsManager.ticketBooked(successBookedTicket,
						ProcessingStatus.SUCCESS);
			}
			if (failedBookedTicket.size() > 0) {
				smsManager.ticketBooked(failedBookedTicket,
						ProcessingStatus.FAILED);
			}

		} catch (Exception e) {
			System.out.println("Error: booking confirmed tickets ");
			e.printStackTrace();
		}

		return setOutSMS;
	}

	private SMS processMenu(ParsedSMS p) throws Exception {
		SMS outsms = null;

		if (p.getAction().equalsIgnoreCase(TOKEN_BOOK)) {
			if (p.validateBookingSMS()) {
				outsms = processTicket(p);
			} else {
				System.out.println("ERROR : invalid booking ticket sms" + p);
				outsms = new SMS(p.getMobileNumber(), DEFAULT_MESSAGE);
			}

		} else if (p.getAction().equalsIgnoreCase(TOKEN_HELP)) {
			outsms = new SMS(p.getMobileNumber(), HELP_MESSAGE);
		} else if (p.getAction().equalsIgnoreCase(TOKEN_BALANCE)) {

			Account acc = accountManager.findAccountByMobileNumber(p
					.getMobileNumber() + "");
			String bal_msg = "Account Balance is : Rs. " + acc.getBalance()
					+ "/-";
			outsms = new SMS(p.getMobileNumber(), bal_msg);
		} else {
			System.out.println("ERROR : invalid menu " + p.getAction());
			outsms = new SMS(p.getMobileNumber(), DEFAULT_MESSAGE);
		}

		return outsms;
	}

	private SMS processTicket(ParsedSMS psms) throws Exception {

		Account account = accountManager.findAccountByMobileNumber(psms
				.getMobileNumber() + "");

		double liAmount = ticketManager.calculateTicketAmount(psms.getFrom(),
				psms.getTo(), psms.getNoOfTicket());

		SMS inSMS = smsManager.findById(psms.getId());

		RailwayStation fromStation = stationAndPriceManager
				.findRailwayStationByCode(psms.getFrom());
		RailwayStation toStation = stationAndPriceManager
				.findRailwayStationByCode(psms.getTo());

		Calendar lcal = Calendar.getInstance();
		Ticket ticket = new Ticket();
		ticket.setAmount(liAmount);
		ticket.setNoofTickets(psms.getNoOfTicket());
		ticket.setFromStation(fromStation);
		ticket.setToStation(toStation);
		ticket.setCreatedTime(lcal.getTime());
		ticket.setInSMS(inSMS);

		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(liAmount);
		transaction.setCreatedTime(lcal.getTime());
		transaction.setTransactionType(TransactionType.DEBIT);

		ticket = ticketManager.createTicket(ticket, transaction);

		SMS outSMS = smsManager.saveTicketSMS(inSMS, ticket);
		// TODO: Update out sms in ticket entity , call on managed ticker object
		// ticket.setInSMS(outSMS);

		return outSMS;

	}

	public boolean storeReceivedSMS(String pMobile, String pBody) {
		System.out.println(" STORE RECIEVED SMS");

		Account account = null;
		account = accountManager.findAccountByMobileNumber(pMobile);
		if (account == null) {
			System.out.println("ERROR : Invalid sms Sender");
			return false;
		}

		String token = pBody.substring(0, 3);
		if (TOKEN_TICKET.equals(token) == false) {
			System.out.println("@@@@ Invalid sms token");
			SMSHandler.sendSMS(pMobile, DEFAULT_MESSAGE);
			return false;
		}

		SMS sms = new SMS();
		sms.setBody(pBody);
		sms.setMobile(Long.parseLong(pMobile));
		sms.setAccount(account);
		sms.setType(SmsType.REQUEST);
		sms.setProcessingStatus(ProcessingStatus.INITAL);

		try {
			smsManager.createSMS(sms);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static List<RailwayStation> getAllStationList() {
		List<RailwayStation> stationList = null;

		stationList = stationAndPriceManager.findAllStations();

		return stationList;
	}

	public static Double getJourneyPrice(String fromStation, String toStation) {

		Double price = 0d;

		price = stationAndPriceManager.findJourneyPrice(fromStation, toStation);

		return price;
	}

	public static Long getTotalNoOfTravellers(String fromStation,
			String toStation, Date startDate, Date endDate) {

		Long noOfTravellers = 0L;

		try {
			noOfTravellers = ticketManager.findNoOfTravellers(fromStation,
					toStation, startDate, endDate);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return noOfTravellers;
	}

	public static Double getTotalRevenue(String fromStation, Date startDate,
			Date endDate) {

		Double revenue = 0d;

		try {
			revenue = ticketManager.findTotalRevenue(fromStation, startDate,
					endDate);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return revenue;
	}

	public static RailwayStation findRailwayStation(String code) {
		try {

			return stationAndPriceManager.findRailwayStationByCode(code);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	public static boolean deleteAccount(Account account) {
		try {

			return accountManager.deleteAccount(account);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return false;
	}

	public static Account updateBalance(Account account, Double amount)
			throws Exception {

		Calendar lcal = Calendar.getInstance();

		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(amount);
		transaction.setCreatedTime(lcal.getTime());
		transaction.setTransactionType(TransactionType.CREDIT);

		transactionManager.createTransaction(transaction);
		accountManager.settleAccountBalance(transaction);

		account = accountManager.findAccountById(account.getId());
		return account;
	}

	public static void main(String[] args) throws Exception {

		RailwayProcesser railwayProcesser = new RailwayProcesser();

		String smsBody = "TKT KLYN CST 1";
		String mobileNo = "9930783001";

		if (railwayProcesser.storeReceivedSMS(mobileNo, smsBody) == true) {
			System.out.println("processing");
			railwayProcesser.processSMS();
		}
	}

}
