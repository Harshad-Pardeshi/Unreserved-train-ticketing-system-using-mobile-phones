<link href="web.css" rel="stylesheet" type="text/css" />
<body>
<%@page import="com.account.servlet.WebUtil"%>
<table border="0" width="100%" cellpadding="0">
<tr>
<%
if(session == null)
{
	response.sendRedirect("login.jsp");
}
String name = (String)session.getAttribute("login_name"); 
if(name == null)
{
	response.sendRedirect("login.jsp");
}
%>
<td align="right">
Welcome <%= name %> &nbsp;
<a href="logout.jsp">Log out</a>&nbsp;
<a href="registration.jsp">Register</a>&nbsp;
<a href="blank.jsp">Home</a></td>
</tr>
<% 
//<tr><td><hr></td></tr>
boolean hr = false;
String lstrMessage = (String) WebUtil.readParameter(request, "message");
if(lstrMessage != null && lstrMessage.trim().length() > 0)
{
hr=true;
 %>
 <tr><td><font color="aqua"><%= lstrMessage%></font></td></tr>
<%} %>
<% String lstrError = (String) WebUtil.readParameter(request, "error");
if(lstrError != null && lstrError.trim().length() > 0)
{hr=true;
 %>
 <tr><td><font color="red"><%= lstrError%></font></td></tr>
<%} %>
<%if(hr==true){ %>
<tr><td><hr></td></tr>
<%} %>
</table>
</body>