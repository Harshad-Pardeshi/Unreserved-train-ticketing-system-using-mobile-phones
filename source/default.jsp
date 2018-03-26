
<%@page import="com.account.sms.ReaderEngine"%>
<%@page import="com.account.sms.PollingEngine"%>

<%
PollingEngine lPollingEngine = new PollingEngine();
lPollingEngine.listenSMS();

// ReaderEngine lReaderEngine = new ReaderEngine();
// lReaderEngine.readSMS();

//System.out.println("In default jsp");
//response.sendRedirect("BookingListenerServlet.do");
response.sendRedirect("index.html");

%>