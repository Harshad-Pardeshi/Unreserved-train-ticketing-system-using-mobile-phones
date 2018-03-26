package com.account.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.railway.core.RailwayProcesser;
import net.java.railway.core.Util;

public class TotalNoOfTravellersAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest pRequest,
			HttpServletResponse pResponse) throws ServletException, IOException {

		String fromStation = WebUtil.readParameter(pRequest, "fromStation")
				+ "";
		String toStation = WebUtil.readParameter(pRequest, "toStation") + "";
		String startDate = WebUtil.readParameter(pRequest, "startDate") + "";
		String endDate = WebUtil.readParameter(pRequest, "endDate") + "";

		Long total = findTotalNoOfTravellers(fromStation, toStation, startDate,
				endDate);

		fromStation = RailwayProcesser.findRailwayStation(fromStation)
				.getStationName();
		toStation = RailwayProcesser.findRailwayStation(toStation)
				.getStationName();

		pRequest.setAttribute("total_No_Of_Travellers", total);
		pRequest.setAttribute("from_Station", fromStation);
		pRequest.setAttribute("to_Station", toStation);
		pRequest.setAttribute("start_Date", startDate);
		pRequest.setAttribute("end_Date", endDate);

		RequestDispatcher lRequestDispatcher = pRequest
				.getRequestDispatcher("/totalNoOfTravellers.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);

		return;
	}

	private Long findTotalNoOfTravellers(String fromStation, String toStation,
			String startTime, String endTime) {
		Long total = 0L;
		try {

			Date startDate = Util.getParseDate(startTime);
			Date endDate = Util.getParseDate(endTime);

			total = RailwayProcesser.getTotalNoOfTravellers(fromStation,
					toStation, startDate, endDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
