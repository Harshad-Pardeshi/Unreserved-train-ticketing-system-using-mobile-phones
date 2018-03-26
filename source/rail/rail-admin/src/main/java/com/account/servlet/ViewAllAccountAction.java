package com.account.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.core.AccountManager;
import net.java.railway.core.Factory;

public class ViewAllAccountAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException,
            IOException {

        List<Account> lAccountCollection = populateAllAccounts();
        pRequest.setAttribute("rail_account_obj_collection", lAccountCollection);
        RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/viewAllAccount.jsp");
        lRequestDispatcher.forward(pRequest, pResponse);

    }

    private List<Account> populateAllAccounts() {
        List<Account> accountList = null;

        try {
            AccountManager accManager = Factory.getAccountManager();

            accountList = accManager.findAllAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

}
