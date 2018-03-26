
<%@page import="net.java.railway.Account"%>
<%@page import="java.util.List"%>
<html>
<%
    List<Account> lAccountCollection = (List<Account>) request.getAttribute("rail_account_obj_collection");
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
							<th>Account No</th>
							<th>Name</th>
							<th>Mobile No</th>
							<th>Balance</th>
							<th>Details</th>
						</tr>
						<%
						    for (int i = 0; i < lAccountCollection.size(); i++) {
						        String cssClass = (i % 2 == 0) ? "TableRowOdd" : "TableRowEven";
						%>
						<tr>
							<td style="<%=cssClass%>"><%=lAccountCollection.get(i).getId()%></td>
							<td style="<%=cssClass%>"><%=lAccountCollection.get(i).getUserName()%></td>
							<td style="<%=cssClass%>"><%=lAccountCollection.get(i).getMobileNumber()%></td>
							<td style="<%=cssClass%>"><%=lAccountCollection.get(i).getBalance()%></td>
							<td style="<%=cssClass%>"><%=lAccountCollection.get(i).getId()%></td>
						</tr>
						<%}%>
					</table>
				</center></td>
		</tr>
	</table>
</div>