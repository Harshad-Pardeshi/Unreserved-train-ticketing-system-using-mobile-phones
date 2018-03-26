package com.account.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Transaction;
import net.java.railway.core.Factory;
import net.java.railway.core.TransactionManager;

public class ViewAllTransactionAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException,
            IOException {

        String liAccountNo = WebUtil.readParameter(pRequest, "accountNo") + "";
        List<Transaction> lTransactionCollection = populateAllAccountTransaction(liAccountNo);
        pRequest.setAttribute("rail_transaction_obj_collection", lTransactionCollection);
        RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/viewAllTransaction.jsp");
        lRequestDispatcher.forward(pRequest, pResponse);
    }

    private List<Transaction> populateAllAccountTransaction(String accountNumber) {
        List<Transaction> transactionList = null;

        try {
            TransactionManager trnManager = Factory.getTransactionManager();

            transactionList = trnManager.findTransactionsByAccountNumber(accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}
