<link href="web.css" rel="stylesheet" type="text/css" />
<%@page import="com.account.controller.Account"%>
<%@page import="com.account.controller.CommonConstants"%>
<table width="100%">
<tr>
<td>
<%@include file="loginheaderdisplay.jsp"%>
</td>
</tr>
<tr>
<td>
<center>
<% Account lAccount = (Account) request.getAttribute("rail_account_obj"); %>
<h1>Account Details</h1>
<table width="80%" bgcolor="white">
<tr>
  <th align="left">Account No : </th>
  <td><%=lAccount.getId() %></td>
</tr>
<tr>
  <th align="left">Name : </th>
  <td><%=lAccount.getName() %></td>
</tr>
<tr>
  <th align="left">Mobile No: </th>
  <td><%=lAccount.getMobileNo() %></td>
</tr>
<tr>
  <th align="left">Amount : </th>
  <td><%=lAccount.getBalance() %></td>
</tr>
</table>
</center>
</td>
</tr>

</table>