
<html>
<body>
	<jsp:include page="header.jsp" />
	<table border="1" width="100%">
		<%
		    Object errorMessage = request.getAttribute("errorMessage");
		%>
		<tr>
			<td><%=errorMessage == null ? "" : errorMessage + ""%></td>
		</tr>

		<tr>
			<td>
				<center>
					<h1>Login Module</h1>
					<form name="loginForm" method="POST" action="Login.do">
						<table border="0">
							<tr>
								<th>Login Id :</th>
								<td><input type="text" name="name" value="admin">
								</td>
							</tr>
							<tr>
								<th>Password :</th>
								<td><input type="password" name="pwd" value="admin">
								</td>
							</tr>
							<tr>
								<td><input type="submit" value="Login">
								</td>
								<td><input type="reset" value="Clear">
								</td>
							</tr>
						</table>
					</form>
				</center></td>
		</tr>
	</table>
</body>
</html>