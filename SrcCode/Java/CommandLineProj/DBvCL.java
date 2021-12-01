import java.sql.*;
import java.io.*;

public class DBvCL {
    public static void main(String args []) throws SQLException{
        System.out.println("Connecting to database, please wait...");
        
        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
        String user = "G5";
        String password = "Fall2021G5";

        //load driver
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        //connect to database
        Connection conn = DriverManager.getConnection(url, user, password);
    
        //System.out.println("Connection: " + conn);

        System.out.println("Note: Departments must be added before students can be added.");
        System.out.println("Student information refers to departments for major and minor.");
        System.out.println("If no minor, enter \"n/a\" when prompted.");

        int menu = 1; //menu options

        while(menu != 0) {
            System.out.println();
            System.out.println("1. Add department");
            System.out.println("2. Add student");
            System.out.println("3. Add course");
            System.out.println("4. Add course section");
            System.out.println("5. Add grade for student/course");
            System.out.println("6. Generate grade report for student");
            System.out.println("7. List courses offered by department");
            System.out.println("0. Exit");
            System.out.print("\nSelect: ");
            menu = getInt();
            System.out.println();

            switch(menu) {
            case 1:
                addDepartment(conn);
                break;
            case 2:
                addStudent(conn);
                break;
            case 3:
                addCourse(conn);
                break;
            case 4:
                addSection(conn);
                break;
            case 5:
                addGrade(conn);
                break;
            case 6:
                getGradeReport(conn);
                break;
            case 7:
                listCourses(conn);
                break;
            default:
                System.out.println("Enter 0-7");
                break;
            }//switch(menu)

        }//while(!=exit)

        System.out.println("Exiting...");
    
    }//main()

    public static void addDepartment(Connection conn) {
        System.out.println("Enter department information: ");

        //get dept info and parse for statement parameters
        String[] dParams = deptInfo().split(",");
        
        //write SQL statement
        String addDeptQ = "INSERT INTO DEPARTMENT VALUES (?,?,?,?,?)";
        
        try {
            //prepare statement
            PreparedStatement addDept = conn.prepareStatement(addDeptQ);
            
            //insert parameters
            for(int i = 0; i < 5; i++) {
                addDept.setString((i+1), dParams[i]);
            }//for()

            //execute & close statement
            addDept.executeUpdate();
            addDept.close();

            //print result
            System.out.println("\nDepartment " + dParams[0] +" added.");
        } catch (SQLException e) {
            System.out.println("\nCouldn't add department.");
            e.printStackTrace();
        }//try-catch
        //System.out.println(dInfo); //for testing
    }//addDepartment()

    public static String deptInfo() {
        String info;
        System.out.print("Name: ");
        info = getString() + ",";
        System.out.print("Code: ");
        info += getString() + ",";
        System.out.print("Office number: ");
        info += getString() + ",";
        System.out.print("Office phone: ");
        info += getString() + ",";
        System.out.print("College: ");
        info += getString();
        return info;
    }//deptInfo()

    public static void addStudent(Connection conn) {
        System.out.println("Enter student information: ");

        //get dept info and parse for statement parameters
        String[] sParams = studentInfo().split(",");

        //write statements to add student (student info, major, minor)
        String studentQ = "INSERT INTO STUDENT VALUES (?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?)";
        String hasMajorQ = "INSERT INTO HAS_MAJOR VALUES (?,?,?)";
        String hasMinorQ = "INSERT INTO HAS_MINOR VALUES (?,?,?)";

        try {
            PreparedStatement addStudent = conn.prepareStatement(studentQ);
            for(int i = 1; i < 16; i++) {
                addStudent.setString(i, sParams[i-1]);
            }//for() student info

            PreparedStatement addMajor = conn.prepareStatement(hasMajorQ);
            addMajor.setString(1, sParams[2]);
            addMajor.setString(2, sParams[15]);
            addMajor.setString(3, sParams[16]);

            PreparedStatement addMinor = conn.prepareStatement(hasMinorQ);
            addMinor.setString(1, sParams[2]);
            addMinor.setString(2, sParams[17]);
            addMinor.setString(3, sParams[18]);

            addStudent.executeUpdate(); //add student info
            addStudent.close();
            addMajor.executeUpdate(); //add student's major
            addMajor.close();
            addMinor.executeUpdate(); //add student's minor
            addMinor.close();
            System.out.println("\nStudent " + sParams[2] + " added.");
        } catch(SQLException e) {
            System.out.println("Couldn't add student.");
            e.printStackTrace();
        }//try-catch

    }//addStudent()

    public static String studentInfo() {
        String info;
        System.out.print("First name: ");
        info = getString() + ",";
        System.out.print("Last name: ");
        info += getString() + ",";
        System.out.print("Nnumber: ");
        info += getString() + ",";
        System.out.print("SSN: ");
        info += getString() + ",";
        System.out.print("Birth date (YYYY-MM-DD): ");
        info += getString() + ",";
        System.out.print("Sex (one character): ");
        info += getString() + ",";
        System.out.print("Class (1-6): ");
        info += getString() + ",";
        System.out.print("Degree program: ");
        info += getString() + ",";
        System.out.print("Current phone #: ");
        info += getString() + ",";
        System.out.print("Current address: ");
        info += getString() + ",";
        System.out.print("Permanent phone #: ");
        info += getString() + ",";
        System.out.print("Permanent street: ");
        info += getString() + ",";
        System.out.print("Permanent city: ");
        info += getString() + ",";
        System.out.print("Permanent state (ST): ");
        info += getString() + ",";
        System.out.print("Permanent zipcode: ");
        info += getString() + ",";
        System.out.print("Major department: ");
        info += getString() + ",";
        System.out.print("Title of major: ");
        info += getString() + ",";
        System.out.print("Minor department: ");
        info += getString() + ",";
        System.out.print("Title of minor: ");
        info += getString();
        return info;
    }//studentInfo()

    public static void addCourse(Connection conn) {
        System.out.println("Enter course information: ");
        String[] cParams = courseInfo().split(",");
        String addCourseQ = "INSERT INTO COURSE VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement insCourse = conn.prepareStatement(addCourseQ);
            for(int i = 0; i < 5; i++) {
                insCourse.setString((i+1), cParams[i]);
            }//for() first 5 parameters
            insCourse.setInt(6, Integer.parseInt(cParams[5])); //credit hours param (int)
            insCourse.executeUpdate();
            insCourse.close();

            System.out.println("\nCourse " + cParams[1] + " added.");
        } catch (SQLException e) {
            System.out.println("\nCouldn't add course.");
            e.printStackTrace();
        }//try-catch

    }//addCourse()

    public static String courseInfo() {
        String info;
        System.out.print("Course name: ");
        info = getString() + ",";
        System.out.print("Course number: ");
        info += getString() + ",";
        System.out.print("Course level: ");
        info += getString() + ",";
        System.out.print("Description: ");
        info += getString() + ",";
        System.out.print("Offering department: ");
        info += getString() + ",";
        System.out.print("Credit hours: ");
        info += getString();
        return info;
    }//courseInfo

    public static void addSection(Connection conn) {
        System.out.println("Enter course section information: ");
        String[] csParams = sectionInfo().split(",");
        String addSectionQ = "INSERT INTO COURSE_SECTION VALUES (?,?,?,?,?)";
        try {
            PreparedStatement addCSec = conn.prepareStatement(addSectionQ);
            for(int i = 0; i < 5; i++) {
                addCSec.setString((i+1), csParams[i]);
            }//for()
            addCSec.executeUpdate();
            addCSec.close();
            System.out.println("\nCourse section " + csParams[0] + "-"
                + csParams[1] + " added for " + csParams[4] + " " + csParams[3]);
        } catch (SQLException e) {
            System.out.println("\nCouldn't add course section.");
            e.printStackTrace();
        }
    }//addSection()

    public static String sectionInfo() {
        String info;
        System.out.print("Course number: ");
        info = getString() + ",";
        System.out.print("Section number: ");
        info += getString() + ",";
        System.out.print("Instructor: ");
        info += getString() + ",";
        System.out.print("Year: ");
        info += getString() + ",";
        System.out.print("Semester (F,S,U): ");
        info += getString();
        return info;
    }//sectionInfo()

    public static void addGrade(Connection conn) {
        System.out.println("Enter grade information: ");
        String[] gParams = gradeInfo().split(",");
        String addGradeQ = "INSERT INTO ENROLLS_IN VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement insGrade = conn.prepareStatement(addGradeQ);
            for(int i = 0; i < 6; i++) {
                insGrade.setString((i+1), gParams[i]);
            }//for()
            insGrade.setDouble(7, Double.parseDouble(gParams[6]));
            insGrade.executeUpdate();
            insGrade.close();
            System.out.println("\nGrade added for " + gParams[0] + " - " + gParams[1]);
        } catch (SQLException e) {
            System.out.println("\nCouldn't add grade.");
            e.printStackTrace();
        }//try-catch
    }//addGrade()

    public static String gradeInfo() {
        String info;
        System.out.print("Nnumber: ");
        info = getString() + ",";
        System.out.print("Course number: ");
        info += getString() + ",";
        System.out.print("Section number: ");
        info += getString() + ",";
        System.out.print("Year: ");
        info += getString() + ",";
        System.out.print("Semester: ");
        info += getString() + ",";
        System.out.print("Letter grade: ");
        info += getString() + ",";
        System.out.print("Grade point (0.0-4.0): ");
        info += getString();
        return info;
    }

    public static void getGradeReport(Connection conn) {
        //get nNumber
        System.out.print("Enter Nnumber to get grade report: ");
        String nNumber = getString();
        
        //print student info
        printStudentInfoGR(conn,nNumber);
        
        //print enrollments
        printCoursesGR(conn,nNumber);

        //calculate and print GPA
        printGPA(conn,nNumber);
        
    }//getGradeReport()

    public static void printStudentInfoGR(Connection conn, String n) {
        String sInfo = "";
        String stInfoQ = "SELECT Fname,Lname,Nnumber,Degree_prog,Class,Major_name,Minor_name,Bdate "
        + "FROM STUDENT,HAS_MAJOR,HAS_MINOR "
        + "WHERE Nnumber=has_major.nnum AND Nnumber=has_minor.nnum AND Nnumber=?";

        System.out.println("\n-------------------------");
        System.out.println("STUDENT INFORMATION");
        System.out.println("-------------------------");

        try{
            PreparedStatement stmt = conn.prepareStatement(stInfoQ);
            stmt.setString(1, n);
            ResultSet rSet = stmt.executeQuery();
            String clName = null;

            while(rSet.next()) {
                int cl = rSet.getInt(5);
                if(cl == 1)
                    clName = "Freshman";
                else if(cl == 2)
                    clName = "Sophomore";
                else if(cl == 3)
                    clName = "Junior";
                else if(cl == 4)
                    clName = "Senior";
                else if(cl == 5)
                    clName = "Master";
                else if(cl == 6)
                    clName = "Doctor";

                sInfo = "NAME:  " + rSet.getString(1) + " " + rSet.getString(2) + "\n"
                + "N#:    " + rSet.getString(3) + "\n"
                + "BDATE: " + rSet.getString(8) + "\n"
                + "DEGREE PROGRAM: " + rSet.getString(4) + "\n"
                + "CLASS: " + clName + "\n"
                + "MAJOR: " + rSet.getString(6) + "\n"
                + "MINOR: " + rSet.getString(7);
            }//while()

            System.out.println(sInfo);

        } catch (SQLException e) {
            System.out.println("Couldn't get student info.");
            e.printStackTrace();
        }//try-catch

    }//printStudentInfoGR()

    public static void printGPA(Connection conn, String nNumber) {
        int CH = 0;
        double GP = 0.0;
        double qualityPoints = 0.0;
        int hours = 0;
        //double GPA = 0.0;
        double totalPoints = 0.0;

        String getCreditHrsQ = "SELECT Credit_hrs, Grade_point FROM COURSE, ENROLLS_IN WHERE Course_number=Cnum AND Nnum=?";

        try {
            PreparedStatement getHrs = conn.prepareStatement(getCreditHrsQ);
            getHrs.setString(1, nNumber);
            ResultSet hrsResults = getHrs.executeQuery();
            while(hrsResults.next()) {
                CH = hrsResults.getInt("Credit_hrs");
                GP = hrsResults.getDouble("Grade_point");
                hours += CH;
                qualityPoints = (CH * GP);
                totalPoints += qualityPoints;
            }//while()
            
            //System.out.println("\n" + hours + " credit hours");
            System.out.println("-------------------------");
            System.out.printf("GPA: %3.2f\n", (totalPoints / hours));
            System.out.println("-------------------------");
        } catch (SQLException e) {
            System.out.println("Couldn't get grade report.");
            e.printStackTrace();
        }//try-catch
    }

    public static void printCoursesGR(Connection conn, String nNumber) {
        String courses =  "-------------------------\n"
                        + "ENROLLMENTS\n"
                        + "-------------------------\n";
        String coursesQ = "SELECT course_num, sem, year, letter_grade, grade_point "
        + "FROM course_section, enrolls_in "
        + "WHERE course_num=cnum AND nnum=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(coursesQ);
            stmt.setString(1, nNumber);
            ResultSet rSet = stmt.executeQuery();
            while(rSet.next()){
                courses += rSet.getString(1) + " "  //course number
                    + rSet.getString(2) + " "       //semester
                    + rSet.getString(3) + " | "     //year
                    + rSet.getString(4) + " | "     //letter grade
                    + rSet.getString(5) + "\n";     //grade point
            }//while
        } catch (SQLException e) {
            System.out.println("Couldn't get enrollments.");
            e.printStackTrace();
        }//try-catch
        System.out.print(courses);
    }//printCoursesGR()
    
    public static void listCourses(Connection conn) {
        System.out.print("Enter department name or code to see courses offered: ");
        String dept = getString();
        String listCoursesQ = "SELECT Course_name, Course_number "
        + "FROM DEPARTMENT D, COURSE C "
        + "WHERE D.Dept_code=C.Course_dept "
        + "AND (D.Dept_name=? OR D.Dept_code=?)";
        try {
            PreparedStatement getCourses = conn.prepareStatement(listCoursesQ);
            getCourses.setString(1, dept);
            getCourses.setString(2, dept);
            ResultSet courses = getCourses.executeQuery();
            String cName;
            String cNum;
            String courseResults = 
              "\nCourses for department " + dept + ":\n\n"
            + "Course#  |  Course Name\n"
            + "---------|-----------------------------------\n";
            while(courses.next()) {
                //dName = courseResultSet.getString("Dept_name");
                cName = courses.getString("Course_name");
                cNum = courses.getString("Course_number");
                courseResults += cNum + "  |  " + cName + "\n";
            }//while()

            System.out.print(courseResults);
        } catch (SQLException e) {
            System.out.println("\nCouldn't find courses.");
            e.printStackTrace();
        }//try-catch
    }//listCourses()

    public static String getString() {
        try {
            StringBuffer buffer = new StringBuffer();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char)c);
                c = System.in.read();
            }//while()
            return buffer.toString().trim();
        } catch (IOException e) {
            return ""; 
        }//try-catch
    }//getString()
    
    public static int getInt() {
        String s = getString();
        return Integer.parseInt(s);
    }//getInt()

    public static double getDouble() {
        String s = getString();
        return Double.parseDouble(s);
    }//getDouble()

}//DBvCL class (database, command line version)