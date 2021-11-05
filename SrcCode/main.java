import java.sql.*;
import java.io.*; 

class main {
  static Connection conn;
  public static void main(String[] args) throws SQLException {
    LoadJBC();
    ConnectToOracle();
    //Execute Queries
    //Use tables to navigate through results.
    CloseConn();
  }

  public static void LoadJBC() {
    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
    catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	    boolean reachable = conn.isValid(10); // 10 sec
	    if(reachable) {
	      System.out.println("Sucessfully connected to Oracle Servers.");
	    }
		} 
    catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public static void CloseConn() {
    try {
      conn.close();
    }
    catch(Exception e) {
      System.out.println("ERROR: Could not close the connection.");
    }
  }

}