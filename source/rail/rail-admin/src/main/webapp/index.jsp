<%@page import="net.java.railway.Constants.AccountRole"%>
<%@page import="net.java.railway.Account"%>
 
<link href="web.css" rel="stylesheet" type="text/css" />
 
<marquee bgcolor = "rgb(10,20,200)"  scrolldelay = "200"><font color = "white" face = "Arial black" size = 2> <b> WelCome To Central railway </b> </font> </marquee> <br><hr size = 3 width = 100%>
	
<marquee bgcolor = "rgb(100,200,75)" scrolldelay = "200" ><font color = "voilet" > Please Click on option. </font> </marquee> 	<hr size = 3 width = 100%>

<table border="0" width="100%">
	
	<tr><th align="left"><font color ="green" align = "center" bgcolor = "blue"> Content </font></th></tr>	
	<tr><td>&spades;&nbsp;<a href="preViewAccount.jsp" >View Customer</a></td></tr>
	<tr><td>&spades;&nbsp;<a href="preViewTransaction.jsp" >View Transactions</a></td></tr>
	
	<%
	if(request.getSession()!= null){    
	    Account acc = (Account)request.getSession().getAttribute("loggedin_user");
	    if(acc != null && acc.getRole() == AccountRole.ADMIN)
	{
	%>
	<tr><td>&spades;&nbsp;<a href="preRegistration.jsp" >New Account</a></td></tr>
	<tr><td>&spades;&nbsp;<a href="preDeleteAccount.jsp" >Delete Account</a></td></tr>
	<tr><td>&spades;&nbsp;<a href="preDepositAccount.jsp" >Re-charge Account</a></td></tr>
	<tr><td>&spades;&nbsp;<a href="preTotalNoOfTravellers.jsp" >Total No Of Travellers</a></td></tr>
	<tr><td>&spades;&nbsp;<a href="preTotalRevenue.jsp" >Total Revenue of Station</a></td></tr>
	<%} }%>

</table>
<br>
<img src = "./images/rail.jpg" height = 200 width = 200 border = 3>  
