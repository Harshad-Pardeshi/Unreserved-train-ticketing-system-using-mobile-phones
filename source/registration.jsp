<link href="web.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function validate()
{
	var name = document.registerForm.name.value;
	var pass = document.registerForm.pwd.value;
	var confirmpass = document.registerForm.confirmpwd.value;

	if(name == '')
	{
		window.alert('Please enter details!!');
		return false;
	}
	if(pass != confirmpass)
	{
		window.alert('Please correct details!!');
		return false;
	}
	if(pass == '')
	{
		window.alert('Please enter details!!');
		return false;
	}
}
</script>
<table border="0" width="100%">
<tr>
<td>
<%@include file="loginheaderdisplay.jsp"%>
</td>
</tr>
<tr>
<td>
<center>
	<h1>Registration Module</h1>
<form name="registerForm" method="POST" action="Registration.do" onsubmit="return validate();">	
	<table border="0">
	<tr>
	<th>Login Id :</th>
	<td><input type="text" name="name"></td>
	</tr>
	<tr>
	<th>Password :</th>
	<td><input type="password" name="pwd"></td>
	</tr>
	<tr>
	<th>Confirm Password :</th>
	<td><input type="password" name="confirmpwd"></td>
	</tr>
	<tr>
	<td><input type="submit" value="Register" onclick="return validate();return false;"></td>
	<td><input type="reset" value="Clear"></td>
	</tr>
	</table>
</form>
</center>
</td>
</tr>
</table>