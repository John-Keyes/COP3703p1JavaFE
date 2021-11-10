import java.sql.*;
import java.io.*;
//import java.util.*;

class main {
  static Connection conn;

  public static void main(String[] args) {
    LoadJDBC(); // Find and load JDBC driver.
    ConnectToOracle(); // Establish a connection to oracle.
    Queries.UserMenu(); // Execute Queries
    // Use tables to navigate through results.
    CloseConn(); // close the Connection
  }

  public static void LoadJDBC() {
    try {
      System.out.println("Attempting to load the jdbc driver.");
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static void ConnectToOracle() {
    String serverName = "cisvm-oracle.unfcsd.unf.edu";
    String portNumber = "1521";
    String sid = "orcl";
    String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
    String username = "G5";
    String password = "Fall2021G5";
    try {
      conn = DriverManager.getConnection(url, username, password);
      boolean reachable = conn.isValid(10); // After 10 seconds
      if (reachable) {
        System.out.println("Sucessfully connected to Oracle Servers.");
        return;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static void CloseConn() {
    try {
      System.out.println("Attempting to close the connection.");
      conn.close();
    } catch (Exception e) {
      System.out.println("ERROR: Could not close the connection.");
      return;
    }
    System.out.println("Connection closed.");
  }
}