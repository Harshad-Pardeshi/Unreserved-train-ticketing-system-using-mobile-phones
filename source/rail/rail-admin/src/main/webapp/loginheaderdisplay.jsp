
<%@page import="net.java.railway.Account"%>
<%@page import="com.account.servlet.WebUtil"%>
<%@page import="net.java.railway.messaging.PollingEngine"%>
<%
    if (session == null) {
        response.sendRedirect("login.jsp");
    }
    Account acc = (Account) session.getAttribute("loggedin_user");
    String name = acc.getUserName();
    if (name == null) {
        response.sendRedirect("login.jsp");
    }

    String btnName = "";
    Object errorMessage = request.getAttribute("errorMessage");
%>

<table width="100%" cellpadding="0" >
	<tr>
		<td><font color="RED"><%=errorMessage == null ? "" : errorMessage + ""%></font></td>
	</tr>
	<tr>
		<td align="right">Welcome <%=name%> &nbsp; <a href="logout.jsp">Log
				out</a>&nbsp; <a href="blank.jsp">Home</a></td>
	</tr>
	<tr>
		<td align="right">
			<form name="f1" action="ChangeSmsProcessingState.do">
				<%
				    if (PollingEngine.globalPollingStatus == PollingEngine.POLLING_STATUS_PAUSED) {
				        btnName = "Run";
				%>
				<input type="hidden" name="sms_state" value="resume_sms_sending">
				<%
				    } else {
				        btnName = "Pause";
				%>
				<input type="hidden" name="sms_state" value="pause_sms_sending">
				<%
				    }
				%>
				<%--
				<input type="submit" value="<%=btnName%>">
				--%>
			</form></td>
	</tr>

</table>