
<%@page import="net.java.railway.core.RailwayProcesser"%>
<%@page import="net.java.railway.RailwayStation"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="./css/style.css" />
<jsp:include page="header.jsp" />

<div class="leftContainer">
	<jsp:include page="index.jsp" />
</div>

<div class="upRightContainer">
	<jsp:include page="loginheaderdisplay.jsp" />
</div>

<%
	List<RailwayStation> stationList = null;
    try {
    	stationList = RailwayProcesser.getAllStationList();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally{}
%>

<div class="lowRightContainer">

	<table border="0" width="100%">
		<tr>
			<td>
				<center>
					<h1>View Total Revenue</h1>
					<form name="totalRevenueForm" method="POST" action="TotalRevenue.do">
						<center>
							<table border="0">
								<tr>
									<th>Station :</th>
									<td>
									<select name="fromStation" id="fromStation">
									<%
									for(RailwayStation station : stationList){
									%>
										<option value="<%= station.getStationCode() %>"><%= station.getStationName()%></option>
									<%} %>
									</select>

									</td>
								</tr>
								<tr>
									<th>Start Date :</th>
									<td><input type="text" name="startDate" value="01/01/2010">
									</td>
								</tr>
								<tr>
									<th>End Date:</th>
									<td><input type="text" name="endDate" value="31/12/2013">
									</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										name="submit" value="Submit">
									</td>
								</tr>
							</table>
						</center>
					</form>
				</center>
			</td>
		</tr>

	</table>

</div>

