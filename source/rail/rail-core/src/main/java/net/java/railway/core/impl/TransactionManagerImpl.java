package net.java.railway.core.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.java.railway.Transaction;
import net.java.railway.core.TransactionManager;


@Stateless
public class TransactionManagerImpl implements TransactionManager {

    @PersistenceContext(unitName = "rail-core")
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Transaction> findTransactionsByAccountNumber(String accountNumber) {

        List<Transaction> transactionList = null;

        Query query = em.createNamedQuery("findTransactionsByAccountNumber");
        query.setParameter("id", new Long(accountNumber));

        transactionList = query.getResultList();

        return transactionList;
    }

    public Transaction createTransaction(Transaction transaction) {

        if (transaction != null) {
            em.persist(transaction);
        }
        return transaction;
    }
}
