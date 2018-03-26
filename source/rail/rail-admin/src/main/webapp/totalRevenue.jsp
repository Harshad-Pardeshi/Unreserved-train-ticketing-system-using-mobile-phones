
<html>
<%
Double total = (Double) request.getAttribute("total_Revenue");
String fromStation = (String)request.getAttribute("from_Station");
String startDate = (String)request.getAttribute("start_Date");
String endDate = (String)request.getAttribute("end_Date");
%>

<link rel="stylesheet" type="text/css" href="./css/style.css" />
<jsp:include page="header.jsp" />

<div class="leftContainer">
	<jsp:include page="index.jsp" />
</div>

<div class="upRightContainer">
	<jsp:include page="loginheaderdisplay.jsp" />
</div>


<div class="lowRightContainer">
	<table border="0" width="100%" align="center">
		<tr>
			<td>
				<center>
					<h1>List Account</h1>
					<table width="90%" bgcolor="white">
						<tr>
							<th>Station :</th>
							<td><%= fromStation %></td>
						</tr>
						<tr>
							<th>Start Date :</th>
							<td><%= startDate %></td>
						</tr>
						<tr>
							<th>End Date :</th>
							<td><%= endDate %></td>
						</tr>
						<tr>
							<th>Total Revenue :</th>
							<td><%= total %></td>
						</tr>
						
					</table>			
				</center>
			</td>
		</tr>
	</table>
</div>