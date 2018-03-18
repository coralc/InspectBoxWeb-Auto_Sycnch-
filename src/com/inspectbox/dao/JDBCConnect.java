package com.inspectbox.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;


public class JDBCConnect{
	
 
  static public final String driver = "com.mysql.jdbc.Driver";
  static public final String connection =  "jdbc:mysql://localhost:3306/inspectboxsa";
  static public final String user = "root";
  static public final String password = "Lyonpartdieu69";
//  static public final String user = "root";
//  static public final String password = "pass";
  
  public static Connection establishConnection() {
	  Connection con = null;
	  try {
	  Class.forName(driver);
	  con = (Connection) DriverManager.getConnection(connection, user, password);

	  System.out.println("Jdbc Mysql Connection String :");	  
	  } catch (Exception e) {
	  e.printStackTrace();
	  }
	  return con;
	  }
  
  public static void closeCon(Connection con){
	  try{
		  if (con!=null)
		  if (!con.isClosed()) {
			  con.close();
			  }	  
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
  }
  
  public static void execute(){
	  try{

		  Connection con=establishConnection();
  	      Statement stmt = (Statement) con.createStatement();
		 
  	      String query="";
  	      ResultSet rs = stmt.executeQuery(query);
		 
		    while (rs.next())
		        System.out.println("col Name= " + rs.getString("col name") + "col name= " + rs.getString("col name"));
		}
		  catch (Exception e) {
		  e.printStackTrace();
		  }
  }
  
}