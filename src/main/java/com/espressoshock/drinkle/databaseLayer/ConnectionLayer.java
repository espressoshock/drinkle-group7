package com.espressoshock.drinkle.databaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLayer {

  //db name: drinkleg7
  //db hostname: den1.mysql6.gear.host

  private static String url = "jdbc:mysql://den1.mysql6.gear.host/drinkleg7";
  //private static String driverName = "com.mysql.cj.jdbc.Driver";
  private static String username = "drinkleg7";
  private static String password = "drinkle.123";
  private static Connection connection;
  private static String urlstring;


//  public static Connection getConnection() {
//    try {
//      Class.forName(driverName);
//      try {
//        connection = DriverManager.getConnection(urlstring, username, password);
//      } catch (SQLException ex) {
//        System.out.println("Failed to create the database connection.");
//      }
//    } catch (ClassNotFoundException ex) {
//      System.out.println("Driver not found.");
//    }
//    return connection;
//  }



  public static Connection getConnection() {
    try {
      connection = DriverManager.getConnection(url,username,password);
    } catch (SQLException ex) {
      System.out.println("Failed to create the database connection. Error");
      System.out.println(ex);
    }
    return connection;
  }
}
