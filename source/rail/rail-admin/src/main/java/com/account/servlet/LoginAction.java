package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java.railway.Account;
import net.java.railway.Constants.AccountRole;
import net.java.railway.core.AccountManager;
import net.java.railway.core.Factory;
import net.java.railway.messaging.PollingEngine;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = WebUtil.readParameter(request, "name") + "";
		String password = WebUtil.readParameter(request, "pwd") + "";
		String errorMessage = null;

		HttpSession session = null;

		try {
			AccountManager accManager = Factory.getAccountManager();
			Account account = accManager.findAccountByUserNameAndPassword(
					userName, password);

			if (account != null) {
				session = request.getSession(true);
				session.setAttribute("loggedin_user", account);

				if (account.getRole() == AccountRole.ADMIN) {

					PollingEngine.globalPollingStatus = PollingEngine.POLLING_STATUS_RUNNING;

					PollingEngine pollingEngine = new PollingEngine();
					pollingEngine.listenSMS();
				}
			} else {

				request.setAttribute("errorMessage",
						"Invalid login credentails");
				RequestDispatcher lRequestDispatcher = request
						.getRequestDispatcher("/login.jsp");
				lRequestDispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {

			errorMessage = "Account not found";
			System.out.println(errorMessage);
			WebUtil.dispatchToErrorPage(request, response, errorMessage);

		}

		RequestDispatcher lRequestDispatcher = request
				.getRequestDispatcher("/blank.jsp");
		lRequestDispatcher.forward(request, response);
		return;
	}
}
