package com.account.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.Account;
import net.java.railway.Constants;
import net.java.railway.Constants.AccountRole;
import net.java.railway.Constants.AccountStatus;
import net.java.railway.core.AccountManager;
import net.java.railway.core.Factory;

public class RegistrationAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String userName = WebUtil.readParameter(request, "name") + "";
        String password = WebUtil.readParameter(request, "pwd") + "";
        String mobile = WebUtil.readParameter(request, "mobile") + "";
        String strRole = WebUtil.readParameter(request, "role") + "";
        int role = 0;
        {
            role = Integer.parseInt(strRole);
        }
        Account account = new Account();
        account.setUserName(userName);
        account.setPassword(password);
        account.setMobileNumber(mobile);
        account.setBalance(Constants.MIN_BALANCE);
        account.setRole((role == AccountRole.ADMIN.ordinal()) ? AccountRole.ADMIN : AccountRole.ENDUSER);
        account.setStatus(AccountStatus.ACTIVE);

        Calendar cal = Calendar.getInstance();
        account.setCreatedTime(cal.getTime());
        account.setModifiedTime(cal.getTime());

        try {
            AccountManager accManager = Factory.getAccountManager();

            account = accManager.createAccount(account);
            if (account != null) {
                request.setAttribute("errorMessage", "Registration success for " + userName);
                request.setAttribute("accountNo", +account.getId());
                RequestDispatcher lRequestDispatcher = request.getRequestDispatcher("ViewAccount.do");
                lRequestDispatcher.forward(request, response);
                return;
            }

        } catch (Exception e) {

            System.out.println("ERROR : Registration Failed + " + e.getMessage());
            request.setAttribute("errorMessage", "Registration failed : " + e.getMessage());
            RequestDispatcher lRequestDispatcher = request.getRequestDispatcher("/blank.jsp");
            lRequestDispatcher.forward(request, response);
            return;
        }

    }
}
