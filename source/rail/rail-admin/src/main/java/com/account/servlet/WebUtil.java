package com.account.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.Constants.TransactionType;
import net.java.railway.Transaction;

public class WebUtil {
    public static Object readParameter(HttpServletRequest pRequest, String pParam) {
        if (pRequest == null)
            return null;

        Object value = pRequest.getParameter(pParam);
        if (value == null)
            value = pRequest.getAttribute(pParam);

        return value;
    }

    public static void dispatchToErrorPage(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
        requestDispatcher.forward(request, response);
    }

    public static Transaction createTransactionObject(Account account, Double amount, TransactionType transactionType) {

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setCreatedTime(new Date());
        transaction.setTransactionType(transactionType);

        return transaction;
    }

    // public static Ticket createTicketObject(Double amount, String fromStation, String toStation) {
    //
    // Ticket ticket = new Ticket();
    //
    // ticket.setAmount(amount);
    // ticket.setFromStation(fromStation);
    // ticket.setToStation(toStation);
    // ticket.setCreatedTime(new Date());
    //
    // return ticket;
    // }
}
