package net.java.railway.core;

import java.util.List;

import javax.ejb.Local;

import net.java.railway.Transaction;

@Local
public interface TransactionManager {

    public List<Transaction> findTransactionsByAccountNumber(String accountNumber);

    public Transaction createTransaction(Transaction transaction);

}
