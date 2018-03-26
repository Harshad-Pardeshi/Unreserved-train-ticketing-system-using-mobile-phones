package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.core.AccountManager;
import net.java.railway.core.Factory;

public class ViewAccountAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Account account = null;

        String accountNumber = WebUtil.readParameter(request, "accountNo") + "";

        int liAccountNo = Integer.parseInt(accountNumber);
        account = loadAccount(liAccountNo);

        request.setAttribute("rail_account_obj", account);
        RequestDispatcher lRequestDispatcher = request.getRequestDispatcher("/viewAccount.jsp");
        lRequestDispatcher.forward(request, response);

    }

    private Account loadAccount(long accountNumber) {
        Account account = null;
        try {
            AccountManager accManager = Factory.getAccountManager();

            account = accManager.findAccountById(accountNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

}
