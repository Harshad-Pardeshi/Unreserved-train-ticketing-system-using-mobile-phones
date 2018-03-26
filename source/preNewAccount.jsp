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
<h1>Create New Account</h1>
<form name="newAccountForm" method="POST" action="NewAccount.do">
<center>
<table>
<tr>
  <th>Name : </th>
  <td><input type="text" name="name"></td>
</tr>
<tr>
  <th>Mobile : </th>
  <td><input type="text" name="mobile"></td>
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