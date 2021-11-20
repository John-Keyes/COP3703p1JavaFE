import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
//import java.io.StringWriter;

class UI { 
    //Declarations and initial assignments.
    static Connection conn;
    static String querIn;  //user input is stored in this string.
    //static StringWriter errors = new StringWriter();
    static JFrame frame1 = new JFrame("G5's School Database"); // The window
    static JButton connButton = new JButton("Connect");
    static JButton sButton = new JButton("Select Information"); //select button
    static JButton iButton = new JButton("Add Information");    //insert button
    static JButton uButton = new JButton("Update Information"); //update button
    static JButton closeConn = new JButton("Close Connection");
    static JButton studButton = new JButton("Student");
    static JButton depButton = new JButton("Deparment");
    static JButton coButton = new JButton("Course");
    static JButton csButton = new JButton("Course Section");
    static JButton grButton= new JButton("Get Grade Report");
    static JButton fcButton = new JButton("Find Courses");
    static JButton agButton = new JButton("Add grade");
    static JButton submit = new JButton("Submit");   //Submit Queries
    static JTextArea messOut = new JTextArea();
    static JTextArea userIn = new JTextArea();

    //Dimensions of the window and its components with their initial properties.
     
    public static void Startup() {
        frame1.setSize(1000, 800);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connButton.setBounds(400, 800, 200, 30);
        sButton.setBounds(400, 200, 200, 30);
        iButton.setBounds(400, 400, 200, 30);
        uButton.setBounds(400, 600, 200, 30);
        closeConn.setBounds(400, 700, 200, 30);
        studButton.setBounds(400, 200, 200, 30);
        depButton.setBounds(400, 300, 200, 30);
        coButton.setBounds(400, 400, 200, 30);
        csButton.setBounds(400, 500, 200, 30);
        grButton.setBounds(400, 300, 200, 30);
        fcButton.setBounds(400, 500, 200, 30);
        agButton.setBounds(400, 400, 200, 30);
        submit.setBounds(400, 650, 200, 30);
        messOut.setBounds(300, 50, 400, 100);
        userIn.setBounds(300, 500, 400, 100);
        messOut.setEditable(false);
        userIn.setEditable(true);
        frame1.add(sButton);
        frame1.add(iButton);
        frame1.add(uButton);
        frame1.add(closeConn);
        frame1.add(messOut);
        frame1.add(userIn);
        frame1.add(connButton);
        frame1.add(grButton);
        frame1.add(fcButton);
        frame1.add(studButton);
        frame1.add(depButton);
        frame1.add(coButton);
        frame1.add(csButton);
        frame1.add(agButton);
        frame1.add(submit);
        sButton.setVisible(false);
        iButton.setVisible(false);
        uButton.setVisible(false);
        connButton.setVisible(false);
        closeConn.setVisible(false);
        grButton.setVisible(false);
        fcButton.setVisible(false);
        agButton.setVisible(false);
        studButton.setVisible(false);
        depButton.setVisible(false);
        coButton.setVisible(false);
        csButton.setVisible(false);
        userIn.setVisible(false);
        submit.setVisible(false);
        messOut.setVisible(true);
    }

    public static void LoadJDBC() {
        boolean valid = true;
        try {
            //System.out.println("Loading jdbc driver...");
            messOut.setText("Loading jdbc driver...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) { 
            valid = false;
            e.printStackTrace();
            //System.out.println("ERROR: Could not load jdbc driver.");
            messOut.setText("ERROR: Could not load jdbc driver.");
        }
        if (valid) {
            messOut.setText("Driver Loaded.");
            // System.out.println("Driver Loaded.");
        }
    }

    public static void InitConn() {
        connButton.setVisible(true);
        connButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connButton.setVisible(false);
                closeConn.setVisible(true);
                messOut.setText("Connecting...");
                ConnectToOracle();
            }
        });
    }

    // Connecting to oracle based on our class and group info.

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
                messOut.setText("Successfully established a connection to the database.");
                // System.out.println("Successfully established a connection to the database.");
            }
        } catch (SQLException e) { 
            e.printStackTrace();
            // System.out.println("ERROR: Failed to establish a connection.");
            messOut.setText("ERROR: Failed to establish a connection.");
        }
    }
    // Where the user can choose to select info, add info, or update info.

    public static void QuerySelUser() {
        sButton.setVisible(true);
        iButton.setVisible(true);
        uButton.setVisible(true);
        sButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                sInput();
                Queries.SelectQuery();
                //OutInfo();
                QuerySelUser();
            }
        });

        iButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                iInput();
                Queries.InsertQuery();
                //OutInfo();
                QuerySelUser();
            }
        });

        uButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                uInput();
                Queries.UpdateQuery();
                //OutInfo();
                QuerySelUser();
            }
        });

        closeConn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                closeConn.setVisible(false);
                CloseConn();
            }
        });
    }
    // User can either generate a grade report or find courses.
    public static void sInput() {
        grButton.setVisible(true);
        fcButton.setVisible(true);
        messOut.setText("Would you like to generate a student's grade report \nor find the available courses in a department.");
        grButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grButton.setVisible(false);
                fcButton.setVisible(false);
                messOut.setText("Enter a student's N-number to generate \ntheir grade report.");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        });
        fcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grButton.setVisible(false);
                fcButton.setVisible(false);
                messOut.setText("Enter the department name or code to \nfind the courses they offer.");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        }); 
    }

    /*This function displays buttons that indicate what information we want to add to a particular table.
      The user will then enter each attribute separated by a comma.
    */
    public static void iInput() {
        studButton.setVisible(true); 
        depButton.setVisible(true);
        coButton.setVisible(true);
        csButton.setVisible(true);
        messOut.setText("Would you like to add a student, department, course, or course section.");
        studButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a student with all of its component with this format: \n<first name,last name,Nnumber,SSN,Bdate,Class,Degree program,\nCurrent Phone Number,Current Address,Permanent Phone Number,Permanent street,\nPermanent city,Permanent State,Permanent Zip> ");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        });
        depButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a Department with this format: \n<Name,Code,Office number,Office phone,College>");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        }); 
        coButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a course with this format: \n<Name,Number,Credit Hours,Level,Description>");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        }); 
        csButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a course section with this format: \n<Course Number,Section Number,Instructor,Year,Semester>");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        });     
    }

    // This is the user input for adding a grade to an existing student. (UPDATE SQL)

    public static void uInput() {
        agButton.setVisible(true);
        messOut.setText("Add a grade to a given student based on the course section");
        agButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agButton.setVisible(false);
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        });
    }
    
    // Submit button that stores the text in String "querIn"

    public static void Submit() {
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messOut.setText("Submitted");
                querIn = userIn.getText();  //Input from userIn stored in querIn.
                submit.setVisible(false);
                userIn.setVisible(false);
            }
        });     
    }

    //This is where the output should be displayed

    /*public static void OutInfo() {

    }*/

    // Closing the connection based on whether the "closeConn" button was clicked.

    public static void CloseConn() {
        try {
            // System.out.println("Attempting to close the connection.");
            messOut.setText("Attempting to close the connection...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("ERROR: Could not close the connection.");
            messOut.setText("ERROR: Could not close the connection.");
        }
        // System.out.println("Connection closed.");
        messOut.setText("Connection closed.");
    }

}
