<%@page import="com.account.controller.CommonConstants"%>
<%@page import="java.util.Vector"%>
<%@page import="com.account.controller.Transaction"%>
<%@page import="com.account.model.Util"%><html>
<% Vector<Transaction> lTransactionCollection = (Vector<Transaction>) request.getAttribute("rail_transaction_obj_collection");%>
<link href="web.css" rel="stylesheet" type="text/css" />
<table border="0" width="100%">
<tr>
<td>
<%@include file="loginheaderdisplay.jsp"%>
</td>
</tr>
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
<% for(int i=0; i<lTransactionCollection.size(); i++) { %>
<tr>
  <td><%=lTransactionCollection.elementAt(i).getId() %></td>
  <td><%=lTransactionCollection.elementAt(i).getDisplayType() %></td>
  <td><%=lTransactionCollection.elementAt(i).getAmount()%></td>
  <td><%= Util.getDisplayDate(lTransactionCollection.elementAt(i).getCreatedTime()) %></td>
</tr>
<%}%>
</table>
</center>
</td>
</tr>

</table>