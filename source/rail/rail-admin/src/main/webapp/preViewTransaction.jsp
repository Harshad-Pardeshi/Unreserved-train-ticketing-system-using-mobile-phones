
<%@page import="net.java.railway.core.Factory"%>
<%@page import="net.java.railway.core.AccountManager"%>
<%@page import="net.java.railway.Account"%>
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
List<Account> accountList = null;
    try {
        AccountManager accManager = Factory.getAccountManager();

        accountList = accManager.findAllAccounts();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally{}
%>

<div class="lowRightContainer">
	<table border="0" width="100%" align="center">
		<tr>
			<td>
				<center>
					<h1>View Transactions</h1>
					<table width="90%" bgcolor="white" border="1">
						<tr>
							<th>Number</th>
							<th>Name</th>
							<th>Mobile No</th>
							<th>Action</th>
						</tr>
						<%
						
						    for (int i = 0; i < accountList.size(); i++) {
						        String cssClass = (i % 2 == 0) ? "TableRowOdd" : "TableRowEven";
						%>
						<tr>
							<td style="<%=cssClass%>"><%=accountList.get(i).getId()%></td>
							<td style="<%=cssClass%>"><%=accountList.get(i).getUserName()%></td>
							<td style="<%=cssClass%>"><%=accountList.get(i).getMobileNumber()%></td>
							<td align="center">
							<a href="ViewAllTransaction.do?accountNo=<%=accountList.get(i).getId()%>" ><span title="View Transaction details"><img src="./images/view.jpg" ></span></a>
							</td>
							
							
						</tr>
						<%}%>
					</table>
				</center></td>
		</tr>
	</table>
</div>