package com.account.servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.core.AccountManager;
import net.java.railway.core.Factory;

public class NewAccountAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String lstrName = WebUtil.readParameter(request, "name") + "";
        String lstrMobile = WebUtil.readParameter(request, "mobile") + "";
        double ldAmount = Double.parseDouble(WebUtil.readParameter(request, "amount") + "");

        Account account = null;
        try {
            account = saveAccount(lstrName, lstrMobile, ldAmount);
        } catch (NamingException e) {

            String errorMessage = "Exception while getting AccountManager bean";
            System.out.println(errorMessage);
            WebUtil.dispatchToErrorPage(request, response, errorMessage);
        } catch (Exception e) {

            String errorMessage = "Exception while creating account";
            System.out.println(errorMessage);
            WebUtil.dispatchToErrorPage(request, response, errorMessage);
        }

        saveMessage(request, "Account created successfully : ''{0}({1,number,#})''", new Object[] {
                lstrName, new Long(account.getId())
        });
        request.setAttribute("accountNo", new Long(account.getId()) + "");
        RequestDispatcher lRequestDispatcher = request.getRequestDispatcher("/ViewAccount.do");
        lRequestDispatcher.forward(request, response);

    }

    private Account saveAccount(String userName, String mobileNumber, double amount) throws NamingException {

        Account account = new Account();
        account.setUserName(userName);
        account.setMobileNumber(mobileNumber);
        account.setBalance(amount);

        AccountManager accManager = Factory.getAccountManager();

        String errorMessage = "Exception while getting AccountManager bean";
        System.out.println(errorMessage);

        try {
            accManager.updateAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

}
