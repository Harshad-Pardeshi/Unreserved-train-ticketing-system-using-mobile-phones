package net.java.railway.core.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.java.railway.Account;
import net.java.railway.Constants.AccountStatus;
import net.java.railway.Constants.TransactionType;
import net.java.railway.Transaction;
import net.java.railway.core.AccountManager;

@Stateless
public class AccountManagerImpl implements AccountManager {

	@PersistenceContext(unitName = "rail-core")
	EntityManager em;

	@Override
	public Account findAccountById(long id) {

		return em.find(Account.class, id);
	}

	@Override
	public Account createAccount(Account account) throws Exception {

		Account acc = findAccountByMobileNumber(account.getMobileNumber());
		if (acc != null) {
			throw new Exception("Mobile number already registered : "
					+ acc.getMobileNumber());
		}

		acc = findAccountByUser(account.getUserName());
		if (acc != null) {
			throw new Exception("Login id already registered : "
					+ acc.getUserName());
		}

		em.persist(account);

		return account;
	}

	@Override
	public Account updateAccount(Account account) throws Exception {

		Account acc = findAccountByMobileNumber(account.getMobileNumber());
		if (acc != null) {
			throw new Exception("Mobile number already registered : "
					+ acc.getMobileNumber());
		}

		acc = findAccountByUserNameAndPassword(account.getUserName(),
				account.getPassword());
		if (acc != null) {
			throw new Exception("Login id already registered : "
					+ acc.getUserName());
		}

		em.merge(account);

		return account;
	}

	@Override
	public Account findAccountByUserNameAndPassword(String userName,
			String password) throws NonUniqueResultException, NoResultException {

		Account account = null;

		Query query = em.createNamedQuery("findAccountByUserNameAndPassword");

		query.setParameter("userName", userName);
		query.setParameter("password", password);
		query.setParameter("status", AccountStatus.ACTIVE);
		try {
			account = (Account) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
			// nre.printStackTrace();
		}

		return account;
	}

	@Override
	public Account findAccountByMobileNumber(String mobileNumber)
			throws NonUniqueResultException, NoResultException {

		Account account = null;

		Query query = em.createNamedQuery("findAccountByMobileNumber");

		query.setParameter("mobileNumber", mobileNumber);
		query.setParameter("status", AccountStatus.ACTIVE);

		try {
			account = (Account) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
			// nre.printStackTrace();
		}
		return account;
	}

	@Override
	public Account findAccountByUser(String userName) throws Exception {

		Account account = null;

		Query query = em.createNamedQuery("findAccountByUser");

		query.setParameter("userName", userName);
		query.setParameter("status", AccountStatus.ACTIVE);

		try {
			account = (Account) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
		}
		return account;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> findAllAccounts() {

		List<Account> accountList = null;

		Query query = em.createNamedQuery("findAllAccounts");

		query.setParameter("status", AccountStatus.ACTIVE);

		accountList = query.getResultList();

		return accountList;
	}

	@Override
	public Account settleAccountBalance(Transaction transaction)
			throws Exception {

		Account managedAccount = em.find(Account.class, transaction
				.getAccount().getId());

		if (TransactionType.DEBIT.equals(transaction.getTransactionType())) {
			if (managedAccount.getBalance() < transaction.getAmount()) {
				throw new Exception("Insufficient Fund in your account");
			} else {
				managedAccount.setBalance(managedAccount.getBalance()
						- transaction.getAmount());
			}

		}

		else {
			managedAccount.setBalance(managedAccount.getBalance()
					+ transaction.getAmount());
		}

		return managedAccount;
	}

	@Override
	public boolean deleteAccount(Account account) throws Exception {

		try {
			// getting managed account
			account = em.find(Account.class, account.getId());
			// em.remove(account);
			account.setStatus(AccountStatus.DELETED);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
		return true;
	}
}
