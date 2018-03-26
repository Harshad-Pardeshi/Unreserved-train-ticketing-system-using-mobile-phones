
<%@page import="net.java.railway.Account"%>

<link rel="stylesheet" type="text/css" href="./css/style.css" />
<jsp:include page="header.jsp" />

<div class="leftContainer">
	<jsp:include page="index.jsp" />
</div>

<div class="upRightContainer">
	<jsp:include page="loginheaderdisplay.jsp" />
</div>


<div class="lowRightContainer">
	<table width="100%">
		<tr>
			<td>
				<center>
					<%
	Account lAccount = (Account) request.getAttribute("rail_account_obj");
%>
					<h1>Account Details</h1>
					<table width="80%" bgcolor="white" align="center" border="1">
						<tr>
							<th align="left">Account No :</th>
							<td align="left"><%=lAccount.getId() %></td>
						</tr>
						<tr>
							<th align="left">Name :</th>
							<td align="left"><%=lAccount.getUserName() %></td>
						</tr>
						<tr>
							<th align="left">Mobile No:</th>
							<td align="left"><%=lAccount.getMobileNumber() %></td>
						</tr>
						<tr>
							<th align="left">Amount :</th>
							<td align="left"><%=lAccount.getBalance() %></td>
						</tr>
					</table>
				</center></td>
		</tr>

	</table>
</div>
