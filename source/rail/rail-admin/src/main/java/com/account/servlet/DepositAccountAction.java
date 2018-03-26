package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.core.RailwayProcesser;

public class DepositAccountAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException,
            IOException {

        Long liAccountNo = Long.parseLong(WebUtil.readParameter(pRequest, "accountNo") + "");
        double ldAmount = Double.parseDouble(WebUtil.readParameter(pRequest, "amount") + "");

        Account account = new Account();
        account.setId(liAccountNo);
        try {
            account = RailwayProcesser.updateBalance(account, ldAmount);

            if (account != null) {
                String msg = "Updated balance success "; // for " + account.getId() + "\nBalance : " +
                                                         // account.getBalance();
                pRequest.setAttribute("errorMessage", msg);
                pRequest.setAttribute("accountNo", +account.getId());
                RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("ViewAccount.do");
                lRequestDispatcher.forward(pRequest, pResponse);
                return;
            }

        } catch (Exception e) {

            System.out.println("ERROR : Balance updation Failed + " + e.getMessage());
            pRequest.setAttribute("errorMessage", "Balance updation Failed : " + e.getMessage());
            RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/blank.jsp");
            lRequestDispatcher.forward(pRequest, pResponse);
            return;
        }

    }
}
