
package com.leibict.ussd;

import java.sql.*;

public class DBconnection {

    static Connection connection;    
    private static Statement stmt;
    private static ResultSet rset;
    public static Connection dbConnection() {
        try{
            try {
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                    connection = DriverManager.getConnection(

                "jdbc:oracle:thin:@localhost:1521:xe", "rail", "rail");
            connection.setAutoCommit(false);

            
        } catch (SQLException se) {
            System.out.println("SQL Error while connecting to the database : "+
                                se.toString());
        } catch (Exception ne) {
            System.out.println("Error while connecting to the database : "+
                                ne.toString());
        }
        return connection;
      }

      public static void destroyDbConnection() throws SQLException {
         if (connection != null || !connection.isClosed() ) {
           connection.close(); // close the connection
         }
      }

      public static ResultSet data() throws Exception
      {
      
          connection = dbConnection();
          
          try {
          if (connection == null || connection.isClosed() ) {
            throw new Exception("No Database Connection!");
          }

          stmt  = connection.createStatement();

          rset  = stmt.executeQuery("SELECT * FROM Test");

          ResultSetMetaData md = rset.getMetaData();

    if (rset == null  ) {
        System.out.println("NO_DATA");
    }

    if(rset.next()) {
      if(rset==null) {
        System.out.println("NO_DATA");
    }
      
    
    }
        } catch (SQLException e) {
          e.printStackTrace();
        } 
        return rset;

  }
}
