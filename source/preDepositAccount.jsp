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
<h1>Deposit Account</h1>
<form name="depositAccountForm" method="POST" action="DepositAccount.do">
<center>
<table>
<tr>
  <th>Id : </th>
  <td><input type="text" name="accountNo"></td>
</tr>
<tr>
  <th>Amount : </th>
  <td><input type="text" name="amount"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" name="submit" value="Save"></td>
</tr>
</table>
</center>
</form>
</center>
</td>
</tr>

</table>