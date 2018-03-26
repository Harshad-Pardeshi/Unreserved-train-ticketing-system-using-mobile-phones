package com.account.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.core.RailwayProcesser;
import net.java.railway.core.Util;

public class TotalRevenueAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest pRequest,
			HttpServletResponse pResponse) throws ServletException, IOException {

		String fromStation = WebUtil.readParameter(pRequest, "fromStation")
				+ "";
		String startDate = WebUtil.readParameter(pRequest, "startDate") + "";
		String endDate = WebUtil.readParameter(pRequest, "endDate") + "";

		Double total = findTotalRevenue(fromStation, startDate, endDate);

		fromStation = RailwayProcesser.findRailwayStation(fromStation)
				.getStationName();

		pRequest.setAttribute("total_Revenue", total);
		pRequest.setAttribute("from_Station", fromStation);
		pRequest.setAttribute("start_Date", startDate);
		pRequest.setAttribute("end_Date", endDate);

		RequestDispatcher lRequestDispatcher = pRequest
				.getRequestDispatcher("/totalRevenue.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);

		return;
	}

	private Double findTotalRevenue(String fromStation, String startTime,
			String endTime) {
		Double total = 0d;
		try {

			Date startDate = Util.getParseDate(startTime);
			Date endDate = Util.getParseDate(endTime);

			total = RailwayProcesser.getTotalRevenue(fromStation, startDate,
					endDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
