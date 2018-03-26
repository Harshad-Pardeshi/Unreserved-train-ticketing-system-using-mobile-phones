package net.java.railway.core;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Factory {

	private static Context initialContext;
	static {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		try {
			initialContext = new InitialContext(p);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private Factory() {
	}

	private static AccountManager accountManager = null;
	private static SMSManager smsManager = null;
	private static StationAndPriceManager stationAndPriceManager = null;
	private static TicketManager ticketManager = null;
	private static TransactionManager transactionManager = null;

	public static Object getBeanByJndiName(String name) throws NamingException {
		Object bean = null;
		bean = initialContext.lookup(name + "Local");
		return bean;
	}

	public static AccountManager getAccountManager() {
		if (accountManager == null) {
			try {
				accountManager = (AccountManager) initialContext
						.lookup("AccountManagerImpl" + "Local");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return accountManager;
	}

	public static SMSManager getSMSManager() {
		if (smsManager == null) {
			try {
				smsManager = (SMSManager) getBeanByJndiName("SMSManagerImpl");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return smsManager;
	}

	public static StationAndPriceManager getStationAndPriceManager() {
		if (stationAndPriceManager == null) {
			try {
				stationAndPriceManager = (StationAndPriceManager) getBeanByJndiName("StationAndPriceManagerImpl");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return stationAndPriceManager;
	}

	public static TicketManager getTicketManager() {
		if (ticketManager == null) {
			try {
				ticketManager = (TicketManager) getBeanByJndiName("TicketManagerImpl");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return ticketManager;
	}

	public static TransactionManager getTransactionManager() {
		if (transactionManager == null) {
			try {
				transactionManager = (TransactionManager) getBeanByJndiName("TransactionManagerImpl");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return transactionManager;
	}
}
