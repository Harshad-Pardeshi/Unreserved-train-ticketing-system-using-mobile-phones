
<%@page import="com.account.model.Util"%>
<%@page import="java.util.Vector"%><link href="web.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function validate()
{
	var mobileNo = document.bookTicketForm.mobileNo;
	if(mobileNo.value.length == 0)
	{
		window.alert('Please mobile no!!');
		return false;
	}
		
	var from = document.bookTicketForm.from_address;
	// window.alert('' + from.selectedIndex);
	var to = document.bookTicketForm.to_address;
	// window.alert('' + to.selectedIndex);
	if(from.selectedIndex == to.selectedIndex)
	{
		window.alert('Please do proper Selection !!');
		return false;
	}
	return true;
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
<h1>Book Ticket</h1>
<form name="bookTicketForm" method="POST" action="BookTicket.do" onsubmit="return validate();">
<% 

Vector<String[]> station = Util.getAllStationList();
		/*{"CST","CST"},
		{"KUR","Kurla"},
		{"DAD","Dadar"},
		{"MUL","Mulund"},
		{"THA","Thane"},
		{"DOM","Dombivali"},
		{"KAL","Kalyan"}};*/
%>
<center>
<table>
<tr>
  <th colspan="1">Mobile No : </th>
  <td colspan="3"><input type="text" name="mobileNo"></td>
</tr>
<tr>
  <td>From: </td>
  <td>
  <select name="from_address">
  <%for(int i=0; i<station.size();i++) {%>
  <option value="<%= (station.elementAt(i))[0]%>"><%= (station.elementAt(i))[1] %></option>
  <%} %>
  </select>
  </td>
  <td>To: </td>
  <td>
  <select name="to_address">
  <%for(int i=0; i<station.size();i++) {%>
  <option value="<%= (station.elementAt(i))[0]%>"><%= (station.elementAt(i))[1] %></option>
  <%} %>
  </select>
  </td>
</tr>
<tr>
  <td colspan="4"><input type="submit" name="submit" value="Generate Ticket" onclick="return onSubmit();return false;"></td>
</tr>
</table>
</center>
</form>
</center>
</td>
</tr>

</table>