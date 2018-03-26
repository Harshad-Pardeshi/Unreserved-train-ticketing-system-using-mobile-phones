<link href="web.css" rel="stylesheet" type="text/css" />
<tr>
<td>
<%@include file="loginheaderdisplay.jsp"%>
</td>
</tr>
<table border="0" width="100%">
<tr>
<td>
<center>
	<h1>View Account</h1>
	<form name="viewAccountForm" method="POST" action="ViewAccount.do">
	<center>
	<table border="0">
	<tr>
	  <th>Account No : </th>
	  <td><input type="text" name="accountNo"></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
	  <td colspan="2" align="center"><input type="submit" name="submit" value="Submit"></td>
	</tr>
	</table>
	</center>
	</form>
</center>
</td>
</tr>

</table>