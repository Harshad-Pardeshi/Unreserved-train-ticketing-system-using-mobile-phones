package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.messaging.PollingEngine;

/**
 * Servlet implementation class ChangeSmsProcessingStateAction
 */
public class ChangeSmsProcessingStateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    boolean flag = false;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String action = WebUtil.readParameter(request, "sms_state") + "";
        if ("pause_sms_sending".equals(action)) {
            PollingEngine.globalPollingStatus = PollingEngine.POLLING_STATUS_PAUSED;
        } else {
            PollingEngine.globalPollingStatus = PollingEngine.POLLING_STATUS_RUNNING;
            if (flag == false) {
                new PollingEngine().listenSMS();
                flag = true;
            }
        }

        RequestDispatcher lRequestDispatcher = request.getRequestDispatcher("/blank.jsp");
        lRequestDispatcher.forward(request, response);
    }

}
