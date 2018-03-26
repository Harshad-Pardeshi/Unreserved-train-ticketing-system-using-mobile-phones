package com.account.model;

import static com.account.controller.CommonConstants.*;
import java.sql.*;
import javax.sql.DataSource;

public class DB{

	private static Connection mConnection;
	static DataSource dataSource = null;
	public static Connection getConnection() throws SQLException{
		if(mConnection == null || mConnection.isClosed())
		{
			try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				mConnection = DriverManager.getConnection("jdbc:odbc:"+DB_DSN_NAME);
//				mConnection = DriverManager.getConnection("jdbc:odbc:rail1");
			}catch(ClassNotFoundException e){
				throw new SQLException("Cannot load JDBC driver");
			}
		}
		return mConnection;
	}
	
	/*public static Connection getConnection() throws Exception{
		if(mConnection == null)
		{
			 Context initCtx = new InitialContext(); 
			 DataSource dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/indianrail");
			 mConnection = dataSource.getConnection();
		}
		return mConnection;
	}*/
}