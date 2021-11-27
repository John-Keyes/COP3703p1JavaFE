import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
//import java.io.StringWriter;

class UI { 

    //Declarations and initial assignments.
    static Connection conn;
    static String querIn = null;  //user input is stored in this string.
    //static StringWriter errors = new StringWriter();
    static JFrame frame1 = new JFrame("G5's School Database"); // The window
    static JButton connButton = new JButton("Connect");
    static JButton sButton = new JButton("Select Information"); //select button
    static JButton iButton = new JButton("Add Information");    //insert button
    static JButton uButton = new JButton("Update Information"); //update button
    static JButton closeConn = new JButton("Close Connection");
    static JButton studButton = new JButton("Student");
    static JButton depButton = new JButton("Department");
    static JButton coButton = new JButton("Course");
    static JButton csButton = new JButton("Course Section");
    static JButton grButton= new JButton("Get Grade Report");
    static JButton fcButton = new JButton("Find Courses");
    static JButton agButton = new JButton("Add grade");
    static JButton submit = new JButton("Submit");   //Submit Queries
    static JTextArea messOut = new JTextArea();
    static JTextArea userIn = new JTextArea();
    //static JScrollPane scrollMsg = new JScrollPane(messOut);
    //static JScrollPane scrollInput = new JScrollPane(userIn);

    //main function
    public static void main(String[] args) {
        UI.Startup();
        UI.LoadJDBC(); 
    }

    //Dimensions of the window and its components with their initial properties.
    public static void Startup() {
        frame1.setSize(1000, 800);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connButton.setBounds(400, 700, 200, 30);
        sButton.setBounds(400, 200, 200, 30);
        iButton.setBounds(400, 300, 200, 30);
        uButton.setBounds(400, 400, 200, 30);
        closeConn.setBounds(400, 700, 200, 30);
        studButton.setBounds(400, 200, 200, 30);
        depButton.setBounds(400, 300, 200, 30);
        coButton.setBounds(400, 400, 200, 30);
        csButton.setBounds(400, 500, 200, 30);
        grButton.setBounds(400, 200, 200, 30);
        fcButton.setBounds(400, 300, 200, 30);
        agButton.setBounds(400, 200, 200, 30);
        submit.setBounds(400, 650, 200, 30);
        messOut.setBounds(100,50,800,100); //(300, 50, 400, 100);
        userIn.setBounds(100,500,800,100); //(300, 500, 400, 100);
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
        frame1.setVisible(true);
        messOut.setVisible(true);
    }

    public static void LoadJDBC() {
        boolean valid = true;
        try {
            messOut.setText("Loading jdbc driver...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) { 
            valid = false;
            e.printStackTrace();
            messOut.setText("ERROR: Could not load jdbc driver.\nSee terminal for details.");
        }
        if (valid) {
            messOut.setText("Driver Loaded.");
            InitConn();
        }
    }

    public static void InitConn() {
        connButton.setVisible(true);
        messOut.setText("Initiate Connection.");
        connButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                connButton.setVisible(false);
                messOut.setText("Connecting...");
                ConnectToOracle();
            }
        });
    }

    // Connecting to oracle based on our class and group info.

    public static int ConnectToOracle() {
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
                closeConn.setVisible(true);
                QuerySelUser();
            }
        } catch (SQLException e) { 
            e.printStackTrace();
            CloseConn();
            messOut.setText("ERROR: Failed to establish a connection.\nSee terminal for details.");
        }
        return 0;
    }
    // Where the user can choose to select info, add info, or update info.

    public static void QuerySelUser() {
        messOut.setText("Select, Add, or Update information in our database.");
        sButton.setVisible(true); //This works
        iButton.setVisible(true);
        uButton.setVisible(true);
        sButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                sButton.setVisible(false); //This does not work
                iButton.setVisible(false);
                uButton.setVisible(false);
                sInput();
//                Queries.SelectQuery();
                //OutInfo();
            }
        });

        iButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                iInput();
//                Queries.InsertQuery();
                //OutInfo();
            }
        });

        uButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                uInput();
//                Queries.UpdateQuery();
                //OutInfo();
            }
        });

        closeConn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                sButton.setVisible(false);
                iButton.setVisible(false);
                uButton.setVisible(false);
                closeConn.setVisible(false);
                CloseConn();
            }
        });
    }//homepage
    
    // User can either generate a grade report or find courses.
    public static void sInput() {
        grButton.setVisible(true);
        fcButton.setVisible(true);
        messOut.setText("Would you like to generate a student's grade report \nor find the available courses in a department?");
        grButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                grButton.setVisible(false);
                fcButton.setVisible(false);
                messOut.setText("Enter a student's N-number to generate \ntheir grade report.");
                userIn.setVisible(true);
                submit.setVisible(true);
                Submit();
            }
        });

        //Display courses offered by given department
        fcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                grButton.setVisible(false);
                fcButton.setVisible(false);
                messOut.setText("Enter the department name or code to find the courses they offer.");
                userIn.setVisible(true);
                submit.setVisible(true);
                
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent viewCourses) {
                        //messOut.setText("Submitted");
                        
                        String deptID = userIn.getText(); //store input from userIn
                        String getCoursesStmt = "SELECT Dept_name, Course_name, Course_number "
                        + "FROM DEPARTMENT D, COURSE C "
                        + "WHERE D.Dept_code=C.Course_dept AND (D.Dept_name=? OR D.Dept_code=?)";    

                        //try to execte statement
                        try {
                            PreparedStatement getCourses = conn.prepareStatement(getCoursesStmt);
                            getCourses.setString(1, deptID);
                            getCourses.setString(2, deptID);
                            ResultSet courseResultSet = getCourses.executeQuery();
                            String dName;
                            String cName;
                            String cNum;
                            String courseResults = "";

                            while(courseResultSet.next()) {
                                dName = courseResultSet.getString("Dept_name");
                                cName = courseResultSet.getString("Course_name");
                                cNum = courseResultSet.getString("Course_number");
                                courseResults += "Department Name: " + dName + "\n";
                                courseResults += "Course Name: " + cName + "\n";
                                courseResults += "Course Number: " + cNum + "\n\n";
                            }

                            messOut.setText(courseResults);
                        } catch (SQLException e) {
                            messOut.setText("Couldn't get courses.");
							e.printStackTrace();
                        }//try-catch

                        submit.setVisible(false);
                        userIn.setVisible(false);

                        //TODO need to give option to go back to main menu after done looking at results
                        //QuerySelUser(); //currently stops at this point w/o options other than closeConnection so we can see results
                    }
                });
            }
        }); 
    }//select information

    /*
     * This function displays buttons that indicate what information we want to add to a particular table.
     * The user will then enter each attribute separated by a comma.
    */
    public static void iInput() {
        studButton.setVisible(true); 
        depButton.setVisible(true);
        coButton.setVisible(true);
        csButton.setVisible(true);
        messOut.setText("Would you like to add a student, department, course, or course section?");

        //add student
        studButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a student with all of its component with this format:\n\n"
                + "First name,Last name,Nnumber,SSN,Bdate(in the form YYYY-MM-DD),Sex,Class,Degree program,\n"
                + "Current Phone Number,Current Address,Permanent Phone Number,Permanent street,Permanent city,\n"
                + "Permanent State,Permanent Zip,Major Dept,Major Title,Minor Dept,Minor Title");
                userIn.setVisible(true);
                submit.setVisible(true);
                //Submit();
                
                //after submitting input, generate query
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent insertStudent) {
                        messOut.setText("Submitted");
                        String sInfo = userIn.getText();  //Input from userIn stored in sInfo
                        String[] studentParams = sInfo.split(","); //parse sInfo to get separate values
                        for(int i=0; i< studentParams.length; i++) {
                        	System.out.println(studentParams[i]);
                        }
                        //prepare statements to add student (student info, major, minor)
                        String studentInfo = "INSERT INTO STUDENT VALUES (?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?)";
                        String hasMajor = "INSERT INTO HAS_MAJOR VALUES (?,?,?)";
                        String hasMinor = "INSERT INTO HAS_MINOR VALUES (?,?,?)";
                        try {
							PreparedStatement addStudent = conn.prepareStatement(studentInfo);
							for(int i = 1; i < 16; i++) {
								addStudent.setString(i, studentParams[i-1]);
                            }//for() student info

                            PreparedStatement addMajor = conn.prepareStatement(hasMajor);
                            addMajor.setString(1, studentParams[2]);
                            addMajor.setString(2, studentParams[15]);
                            addMajor.setString(3, studentParams[16]);

                            PreparedStatement addMinor = conn.prepareStatement(hasMinor);
                            addMinor.setString(1, studentParams[2]);
                            addMinor.setString(2, studentParams[17]);
                            addMinor.setString(3, studentParams[18]);

                            addStudent.executeUpdate(); //add student info
							addStudent.close();
                            addMajor.executeUpdate(); //add student's major
                            addMajor.close();
                            addMinor.executeUpdate(); //add student's minor
                            addMinor.close();

						} catch (SQLException e) {
							messOut.setText("Couldn't add student.");
							e.printStackTrace();
						} catch (ArrayIndexOutOfBoundsException e) {
							
						}//try-catch
                        //querIn = null;
                        userIn.setText(null);
                        submit.setVisible(false);
                        userIn.setVisible(false);
                        QuerySelUser();
                    }
                }); 
            }
        });//add student
       
       //add department    
        depButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a Department with this format: \nName,Code,Office number,Office phone,College");
                userIn.setVisible(true);
                submit.setVisible(true);
                //Submit();
                //after submitting input, generate query
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent insertDept) {
                        messOut.setText("Submitted");
                        String dInfo = userIn.getText();  //Input from userIn stored in dInfo
                        String[] dParams = dInfo.split(","); //parse dInfo to get separate values
                        
                        // for(int i = 0; i < dParams.length; i++) {
                        // 	System.out.println(dParams[i]);
                        // }

                        //prepare statement to add department
                        String deptInfo = "INSERT INTO DEPARTMENT VALUES (?,?,?,?,?)";
                        try {
							PreparedStatement addDept = conn.prepareStatement(deptInfo);
							for(int i = 0; i < 5; i++) {
								addDept.setString((i+1), dParams[i]);
                            }
                            addDept.executeUpdate();
						        addDept.close();
						} catch (SQLException e) {
							messOut.setText("Couldn't add department.");
							e.printStackTrace();
						}//try-catch

                        userIn.setText(null);
                        submit.setVisible(false);
                        userIn.setVisible(false);
                        QuerySelUser();
                    }
                }); 
            }
        });//add department
        
       //add course
        coButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a course with this format:\n\n"
                +"<Name,Number,Level,Description,Department,Credit Hours>");
                userIn.setVisible(true);
                submit.setVisible(true);
                //Submit();

                //after submitting input, generate query
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent insertCourse) {
                        messOut.setText("Submitted");
                        String cInfo = userIn.getText();  //Input from userIn stored in cInfo
                        String[] cParams = cInfo.split(","); //parse cInfo to get separate values
                        for(int i = 0; i < cParams.length; i++) {
                        	System.out.println(cParams[i]);
                        }
                        //prepare statement to add course
                        String courseInfo = "INSERT INTO COURSE VALUES (?,?,?,?,?,?)";
                        try {
							PreparedStatement addCourse = conn.prepareStatement(courseInfo);
							for(int i = 0; i < 5; i++) {
								addCourse.setString((i+1), cParams[i]);
                            }//for() course info to sql update (first 5 params, Strings)
                            addCourse.setInt(6, Integer.parseInt(cParams[5])); //credit hours param (int)
                            addCourse.executeUpdate();
							addCourse.close();
						} catch (SQLException e) {
							messOut.setText("Couldn't add course.");
							e.printStackTrace();
						}//try-catch
                        
                        userIn.setText(null);
                        submit.setVisible(false);
                        userIn.setVisible(false);
                        QuerySelUser();
                    }
                });
            }
        });//add course
        
        //add section
        csButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                studButton.setVisible(false);
                depButton.setVisible(false);
                coButton.setVisible(false);
                csButton.setVisible(false);
                messOut.setText("Add a course section with this format: \n<Course Number,Section Number,Instructor,Year,Semester>");
                userIn.setVisible(true);
                submit.setVisible(true);
                
                //after submitting input, generate query
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent insertSection) {
                        messOut.setText("Submitted");
                        String csInfo = userIn.getText();  //Input from userIn stored in csInfo
                        String[] csParams = csInfo.split(","); //parse csInfo to get separate values
                        for(int i = 0; i < csParams.length; i++) {
                        	System.out.println(csParams[i]);
                        }
                        //prepare statement to add section
                        String sectionInfo = "INSERT INTO COURSE_SECTION VALUES (?,?,?,?,?)";
                        try {
							PreparedStatement addSection = conn.prepareStatement(sectionInfo);
							for(int i = 0; i < 5; i++) {
								addSection.setString((i+1), csParams[i]);
                            }//for() course section info to sql params
                            addSection.executeUpdate();
							addSection.close();
						} catch (SQLException e) {
							messOut.setText("Couldn't add course section.");
							e.printStackTrace();
						}//try-catch
                        
                        userIn.setText(null);
                        submit.setVisible(false);
                        userIn.setVisible(false);
                        QuerySelUser();
                    }//execute section insert
                });//submit entered section info
            }//choose to insert section
        });//add section
    
    }//add - student, department, course, or section

    // This is the user input for adding a grade to an existing student.

    //add grade
    public static void uInput() { 
        agButton.setVisible(true);
        messOut.setText("Add a grade to a given student based on the course section");
        agButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                agButton.setVisible(false);
                userIn.setVisible(true);
                submit.setVisible(true);
                
                //after submitting input, generate query
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent insertGrade) {
                        messOut.setText("Submitted");
                        String gInfo = userIn.getText();  //Input from userIn stored in gInfo
                        String[] gParams = gInfo.split(","); //parse gInfo to get separate values
                        for(int i = 0; i < gParams.length; i++) {
                        	System.out.println(gParams[i]);
                        }
                        //prepare statement to add grade
                        String gradeInfo = "INSERT INTO ENROLLS_IN VALUES (?,?,?,?,?,?,?)";
                        try {
				PreparedStatement addGrade = conn.prepareStatement(gradeInfo);
				for(int i = 0; i < 7; i++) {
					addGrade.setString((i+1), gParams[i]);
				}//for() grade info to sql params (first 6, all Strings)
                            	addGrade.setInt(7, Integer.parseInt(gParams[6])); //last param, number grade

                            	addGrade.executeUpdate();
				addGrade.close();
			} catch (SQLException e) {
				messOut.setText("Couldn't add grade.");
				e.printStackTrace();
			}//try-catch
                        
                        userIn.setText(null);
                        submit.setVisible(false);
                        userIn.setVisible(false);
                        QuerySelUser();
                    }//execute grade insert
                });//submit entered grade info
            }//display input box
        });//add grade chosen
    }//add grade
    
    // Submit button that stores the text in String "querIn"
    // prob don't need this anymore 

    public static void Submit() {
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                messOut.setText("Submitted");
                querIn = userIn.getText();  //Input from userIn stored in querIn.
                submit.setVisible(false);
                userIn.setVisible(false);
                QuerySelUser();
            }
        });     
    }

    //This is where the output should be displayed

    /*public static void OutInfo() {
    }*/

    // Closing the connection based on whether the "closeConn" button was clicked.

    public static void CloseConn() {
        boolean valid = true;
        try {
            messOut.setText("Attempting to close the connection...");
            conn.close();
        } catch (Exception e) {
            valid = false;
            e.printStackTrace();
            messOut.setText("ERROR: Could not close the connection.\nSee terminal for details.");
        }
        if(valid) {
            sButton.setVisible(false);
            iButton.setVisible(false);
            uButton.setVisible(false);
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
            messOut.setText("Connection closed.");
            InitConn();
        }
    }

}
