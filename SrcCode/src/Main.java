//import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Main {
    static Connection conn;
    public static void main(String[] args) {
        UI.Startup();
        LoadJDBC(); 
    }

    public static void LoadJDBC() {
        boolean valid = true;
        try {
            UI.messOut.setText("Loading jdbc driver...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) { 
            valid = false;
            e.printStackTrace();
            UI.messOut.setText("ERROR: Could not load jdbc driver.\nSee terminal for details.");
        }
        if (valid) {
            UI.messOut.setText("Driver Loaded.");
            InitConn();
        }
    }

    public static void InitConn() {
        UI.connButton.setVisible(true);
        UI.messOut.setText("Initiate Connection.");
        UI.connButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                UI.connButton.setVisible(false);
                UI.messOut.setText("Connecting...");
                ConnectToOracle();
            }
        });
    }
    
    public static int ConnectToOracle() {

        UI.connButton.setVisible(false);
        UI.messOut.setText("Connecting...");
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
                UI.messOut.setText("Successfully established a connection to the database.");
                UI.closeConn.setVisible(true);
                QuerySelUser();
            }
        } catch (SQLException e) { 
            e.printStackTrace();
            CloseConn();
            UI.messOut.setText("ERROR: Failed to establish a connection.\nSee terminal for details.");
        }
        return 0;
    }

    public static void QuerySelUser() throws SQLException {
        UI.ShowButtons();
        UI.messOut.setText("This is the Home Page.");
        UI.studButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                UI.HideButtons();
                UI.home.setVisible(true);
                homeB();
                UI.messOut.setText("Add a Student.");
                UI.AddStudArgs();
                UI.next.setVisible(true);
                UI.next.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent nxt) {
                        UI.HideStud1Args();
                        UI.AddMajnMinArgs();
                        UI.next.setVisible(false);
                        UI.submit.setVisible(true);
                        try {
                            studSubmit();
                        }
                        catch(SQLException e) {
                            UI.messOut.setText("Couldn't add student, check terminal for details.");
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        UI.depButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                UI.HideButtons();
                UI.home.setVisible(true);
                homeB();
                UI.messOut.setText("Add a Department.");
                UI.AddDepArgs();
                UI.submit.setVisible(true);
                //try {
                    depSubmit();
                /*}
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add department, check terminal for details.");
                    e.printStackTrace();
                }*/
            }
        });

        UI.coButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                UI.HideButtons();
                UI.home.setVisible(true);
                homeB();
                UI.messOut.setText("Add a Course.");
                UI.AddCoArgs();
                UI.submit.setVisible(true);
                //try {
                    coSubmit();
                /*}
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add course, check terminal for details.");
                    e.printStackTrace();
                }*/
            }
        });

        UI.csButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                UI.HideButtons();
                UI.home.setVisible(true);
                homeB();
                UI.messOut.setText("Add a Course Section.");
                UI.AddCsArgs();
                UI.submit.setVisible(true);
                //try {
                    csSubmit();
                /*}
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add course section, check terminal for details.");
                    e.printStackTrace();
                }*/
            }
        });
        
        UI.grButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UI.HideButtons();
               UI.home.setVisible(true);
               homeB();
                UI.messOut.setText("Generate a student's grade report by entering their N#.");
               UI.argLab1.setText("N#");
               UI.argLab1.setVisible(true);
               UI.arg1.setVisible(true);
                UI.submit.setVisible(true);
                grSubmit();
            }
        });

        UI.fcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                UI.HideButtons();
               UI.home.setVisible(true);
               homeB();
                UI.messOut.setText("Enter the Department Name or Code to find the courses they provide.");
               UI.argLab1.setText("Dept Name/Code");
               UI.argLab1.setVisible(true);
               UI.arg1.setVisible(true);
                UI.submit.setVisible(true);
                fcSubmit();
            }
        }); 
        UI.agButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent g) {
                UI.HideButtons();
                UI.messOut.setText("Add grade to an existing student.");
               UI.home.setVisible(true);
               homeB();
                UI.AddAgArgs();
                UI.submit.setVisible(true);
                agSubmit();
            } //after submitting input, generate query
        });

        //Display courses offered by given department

        UI.closeConn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent close) {
                CloseConn();
            }
        });
    }//homepage

    // User can either generate a grade report or find courses.

    /*
    * This function displays buttons that indicate what information we want to add to a particular table.
    * The user will then enter each attribute separated by a comma.
    */

    // This is the user input for adding a grade to an existing student.

    public static void studSubmit() throws SQLException {
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent stud) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                //getArgs();
                String sParams[] = new String[18];
                UI.GetStudArgs(sParams);
                try {
                    Queries.addStudent(conn, sParams);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add student, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }
    public static void depSubmit() {
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent dep) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String dParams[] = new String[5];
                UI.GetDepArgs(dParams);
                try {
                    Queries.addDepartment(conn, dParams);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add department, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }
    public static void coSubmit() {
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent co) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String coParams[] = new String[5];
                UI.GetCoArgs(coParams);
                try {
                    Queries.addCourse(conn, coParams);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add course, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void csSubmit() {
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent cs) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String csParams[] = new String[5];
                UI.GetCsArgs(csParams);
                try {
                    Queries.addSection(conn, csParams);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add course section, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void grSubmit() { //submitting grade report 
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent gr) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String grParam =UI.arg1.getText();
                try {
                    Queries.getGradeReport(conn, grParam);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't generate grade report, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void fcSubmit() { //Finding course
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent fc) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String fcParam = UI.arg1.getText();
                try {
                    Queries.listCourses(conn, fcParam);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't find course, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void agSubmit() { //Add Student Grade
        UI.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ag) {
                UI.messOut.setText("Submitted");
                UI.submit.setVisible(false);
                String agParams[] = new String[7];
                UI.GetAgArgs(agParams);
                try {
                    Queries.addGrade(conn, agParams);
                }
                catch(SQLException e) {
                    UI.messOut.setText("Couldn't add student grade, check terminal for details.");
                    UI.submit.setVisible(true);
                    e.printStackTrace();
                }
            }
        });

    }

    public static void homeB() {
        UI.home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ho) {
                UI.ResetArgs();
                UI.HideButtons();
                UI.next.setVisible(false);
                UI.home.setVisible(false);
                UI.submit.setVisible(false);
                UI.messOut.setVisible(true);
                try {
                    QuerySelUser();
                } catch (SQLException e) {
                    UI.messOut.setText("Could not reach the home page, see terminal for details.");
                    UI.home.setVisible(true);
                    e.printStackTrace();
                }
            }
        });
    }

    //the output is shown from the generated Query

    public static void OutInfo(String genMes) {
        UI.messOut.setVisible(false);
        UI.out.setVisible(true);
        UI.HideArgs();
        UI.out.setText(genMes);
    }

    // Closing the connection based on whether the "closeConn" button was clicked.

    public static void CloseConn() {
        boolean valid = true;
        try {
            UI.messOut.setText("Attempting to close the connection...");
            conn.close();
        } catch (Exception e) {
            valid = false;
            e.printStackTrace();
            UI.messOut.setText("ERROR: Could not close the connection.\nSee terminal for details.");
        }
        if(valid) {
            UI.ResetArgs();
            UI.HideButtons();
            UI.closeConn.setVisible(false);
            UI.next.setVisible(false);
            UI.home.setVisible(false);
            UI.submit.setVisible(false);
            UI.messOut.setVisible(true);
            UI.messOut.setText("Connection closed.");
            InitConn();
        }
    }
}
