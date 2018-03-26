<%@page import="com.account.controller.Account"%>
<%@page import="com.account.controller.CommonConstants"%>
<%@page import="java.util.Vector"%><html>
<% Vector<Account> lAccountCollection = (Vector<Account>) request.getAttribute("rail_account_obj_collection");%>
<link href="web.css" rel="stylesheet" type="text/css" />
<body>
<table border="0" width="100%">
<tr>
<td>
<%@include file="loginheaderdisplay.jsp"%>
</td>
</tr>
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
</tr>
<% for(int i=0; i<lAccountCollection.size(); i++) { 
String cssClass = (i%2 == 0)? "TableRowOdd" : "TableRowEven";%>
<tr>
  <td style="<%=cssClass %>"><%=lAccountCollection.elementAt(i).getId() %></td>
  <td style="<%=cssClass %>"><%=lAccountCollection.elementAt(i).getName() %></td>
  <td style="<%=cssClass %>"><%=lAccountCollection.elementAt(i).getMobileNo() %></td>
  <td style="<%=cssClass %>"><%=lAccountCollection.elementAt(i).getBalance() %></td>
</tr>
<%}%>
</table>
</center>
</td>
</tr>
</table>
</body>