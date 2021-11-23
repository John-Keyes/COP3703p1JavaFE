



//This is the top level of the code, for easy readability and clear procedures.
public class Main {
    public static void main(String[] args) {
        UI.Startup();
        UI.LoadJDBC(); // Find and load JDBC driver.
        UI.InitConn(); // Establish a connection to oracle.
        // Use tables to navigate through results.
    }
}

