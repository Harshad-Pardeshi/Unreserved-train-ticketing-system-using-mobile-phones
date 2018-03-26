
<html>
<link href="web.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, "");
	}

	function validate() {
		var name = document.registerForm.name.value;
		var mobile = document.registerForm.mobile.value;
		var pass = document.registerForm.pwd.value;
		var confirmpass = document.registerForm.confirmpwd.value;

		alert(name + ' ' + pass + ' ' + confirmpass);

		if (name == '') {
			window.alert('Please enter username!!');
			return false;
		}

		if (mobile == '') {
			window.alert('Please enter mobile number!!');
			return false;
		}

		if (pass == '') {
			window.alert('Please enter password!!');
			return false;
		}

		if (confirmpass == '') {
			window.alert('Please confirm your password!!');
			return false;
		}

		if (pass != confirmpass) {
			window.alert('Password does not match');
			return false;
		}
		document.registerForm.submit();

	}
</script>
<%
    Object errorMessage = request.getAttribute("errorMessage");
%>

<table width="100%" cellpadding="0" >
	<tr>
		<td><%=errorMessage == null ? "" : errorMessage + ""%></td>
	</tr>
</table>
<table border="10" width="100%">
	<tr>
		<td>
			<center>
				<h1>Registration Module</h1>
				<form name="registerForm" method="POST" action="Registration.do">
					<table border="0">
						<tr>
							<th>Login Id :</th>
							<td><input type="text" name="name">
							</td>
						</tr>
						<tr>
							<th>Mobile number :</th>
							<td><input type="text" name="mobile">
							</td>
						</tr>
						<tr>
							<th>Password :</th>
							<td><input type="password" name="pwd">
							</td>
						</tr>
						<tr>
							<th>Confirm Password :</th>
							<td><input type="password" name="confirmpwd">
							</td>
						</tr>
						<tr>
							<th>Role :</th>
							<td><select name="role" id="role">
									<option value="0">Administrator</option>
									<option value="1">End User</option>
							</select></td>
						</tr>
						<tr>
							<td><input type="submit" value="Register"
								onclick="javascript:validate();">
							</td>
							<td><input type="reset" value="Clear">
							</td>
						</tr>
					</table>
				</form>
			</center></td>
	</tr>
</table>
</html>