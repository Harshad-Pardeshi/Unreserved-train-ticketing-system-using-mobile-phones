package com.account.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException,
            IOException {

        // PollingEngine.listenSMS();
        // RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("index.html");
        // lRequestDispatcher.forward(pRequest, pResponse);

        super.doPost(pRequest, pResponse);
    }
}
