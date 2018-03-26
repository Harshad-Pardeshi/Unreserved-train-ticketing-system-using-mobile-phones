package net.java.railway.core;

import java.util.List;

import javax.ejb.Local;

import net.java.railway.Account;
import net.java.railway.Transaction;

@Local
public interface AccountManager {

    public Account createAccount(Account account) throws Exception;

    public Account updateAccount(Account account) throws Exception;

    public boolean deleteAccount(Account account) throws Exception;

    public Account findAccountByUserNameAndPassword(String userName, String password);

    public Account findAccountById(long id);

    public Account findAccountByMobileNumber(String mobileNumber);

    public List<Account> findAllAccounts();

    public Account settleAccountBalance(Transaction transaction) throws Exception;

    Account findAccountByUser(String userName) throws Exception;
}
