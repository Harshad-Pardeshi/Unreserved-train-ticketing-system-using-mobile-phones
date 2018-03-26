
<link rel="stylesheet" type="text/css" href="./css/style.css" />
<jsp:include page="header.jsp" />

<div class="leftContainer">
	<jsp:include page="index.jsp" />
</div>

<div class="upRightContainer">
	<jsp:include page="loginheaderdisplay.jsp" />
</div>


<div class="lowRightContainer">
	<table border="0" width="100%">
		<tr>
			<td>
				<center>
					<h1>Deposit Account</h1>
					<form name="depositAccountForm" method="POST"
						action="DepositAccount.do">
						<center>
							<table>
								<tr>
									<th>Id :</th>
									<td><input type="text" name="accountNo">
									</td>
								</tr>
								<tr>
									<th>Amount :</th>
									<td><input type="text" name="amount">
									</td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="submit"
										value="Save">
									</td>
								</tr>
							</table>
						</center>
					</form>
				</center></td>
		</tr>

	</table>
</div>

