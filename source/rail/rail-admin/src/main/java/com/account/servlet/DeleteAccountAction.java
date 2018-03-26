package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.core.RailwayProcesser;

public class DeleteAccountAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException,
            IOException {

        String lstrAccountNo = WebUtil.readParameter(pRequest, "accountNo") + "";

        Long liAccountNo = Long.parseLong(lstrAccountNo);
        Account account = new Account();
        account.setId(liAccountNo);
        boolean success = RailwayProcesser.deleteAccount(account);

        if (success) {
            pRequest.setAttribute("errorMessage", "Account deleted for " + lstrAccountNo);
            RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/preDeleteAccount.jsp");
            lRequestDispatcher.forward(pRequest, pResponse);
            return;

        } else {

            System.out.println("ERROR : Deletion Failed !");
            pRequest.setAttribute("errorMessage", "Deletion Failed ");
            RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/blank.jsp");
            lRequestDispatcher.forward(pRequest, pResponse);
            return;
        }
    }

}
