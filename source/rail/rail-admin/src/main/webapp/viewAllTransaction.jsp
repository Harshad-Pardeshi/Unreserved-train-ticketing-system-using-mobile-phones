
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.java.railway.Transaction"%>
<html>
<%
	List<Transaction> lTransactionCollection = (ArrayList<Transaction>) request
			.getAttribute("rail_transaction_obj_collection");
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
	<table border="1" align="center" width="100%">
		<tr>
			<td>
				<center>
					<h1>List Transaction</h1>
					<table width="80%" bgcolor="white">
						<tr>
							<th>Transaction No</th>
							<th>Transaction Type</th>
							<th>Amount</th>
							<th>Transaction Date</th>
						</tr>
						<%
							for (Transaction transaction : lTransactionCollection) {
						%>
						<tr>
							<td><%=transaction.getId()%></td>
							<td><%=transaction.getTransactionType()%></td>
							<td><%=transaction.getAmount()%></td>
							<td><%=transaction.getCreatedTime()%></td>
						</tr>
						<%
							}
						%>
					</table>
				</center>
			</td>
		</tr>

	</table>
</div>