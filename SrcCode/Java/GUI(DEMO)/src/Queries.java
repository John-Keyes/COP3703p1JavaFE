import java.sql.*;
//import java.io.*;

/*
* This is the modified queries class that is basically Miranda's command line program without the command line.
* These functions are called in Main.java.
*/

public class Queries {
    public static void addDepartment(Connection conn, String dParams[]) throws SQLException {
        
        //write SQL statement
        try {
            try {
        String addDeptQ = null;
        addDeptQ = "INSERT INTO DEPARTMENT VALUES (?,?,?,?,?)";
            //prepare statement
            PreparedStatement addDept = null;
            addDept = conn.prepareStatement(addDeptQ);
            
            //insert parameters
            for(int i = 0; i < 5; i++) {
                addDept.setString((i+1), dParams[i]);
            }//for()

            //execute & close statement
            addDept.executeUpdate();
            addDept.close();

            //print result
            System.out.println("\nDepartment " + dParams[0] +" added.");
            Main.OutInfo("\nDepartment " + dParams[0] +" added.");
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
        //System.out.println(dInfo); //for testing
    }//addDepartment()

    public static void addStudent(Connection conn,  String[] sParams) throws SQLException {

        //write statements to add student (student info, major, minor)
        try {
            try {
        String studentQ = null;
        studentQ = "INSERT INTO STUDENT VALUES (?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?)";
        String hasMajorQ = null;
        hasMajorQ = "INSERT INTO HAS_MAJOR VALUES (?,?,?)";
        String hasMinorQ = null;
        hasMinorQ = "INSERT INTO HAS_MINOR VALUES (?,?,?)";
            PreparedStatement addStudent = null;
            PreparedStatement addMajor = null;
            PreparedStatement addMinor = null;
            addStudent = conn.prepareStatement(studentQ);
            for(int i = 1; i < 16; i++) {
                addStudent.setString(i, sParams[i-1]);
            }//for() student info
            addMajor = conn.prepareStatement(hasMajorQ);
            addMajor.setString(1, sParams[2]);
            addMajor.setString(2, sParams[15]);
            addMajor.setString(3, sParams[16]);

            addMinor = conn.prepareStatement(hasMinorQ);
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
            Main.OutInfo("\nStudent " + sParams[2] + " added.");
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
    }//addStudent()

    public static void addCourse(Connection conn, String cParams[]) throws SQLException  {
        try {
            try {
        String addCourseQ = null;
        addCourseQ = "INSERT INTO COURSE VALUES (?,?,?,?,?,?)";
        PreparedStatement insCourse = null;
            insCourse = conn.prepareStatement(addCourseQ);
            for(int i = 0; i < 5; i++) {
                insCourse.setString((i+1), cParams[i]);
            }//for() first 5 parameters
            insCourse.setInt(6, Integer.parseInt(cParams[5])); //credit hours param (int)
            insCourse.executeUpdate();
            insCourse.close();

            System.out.println("\nCourse " + cParams[1] + " added.");
            Main.OutInfo("\nCourse " + cParams[1] + " added.");
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }

    }//addCourse()

    public static void addSection(Connection conn, String csParams[])  throws SQLException {
        try {
            try {
        String addSectionQ = null;
        addSectionQ = "INSERT INTO COURSE_SECTION VALUES (?,?,?,?,?)";
        PreparedStatement addCSec = null;
            addCSec = conn.prepareStatement(addSectionQ);
            for(int i = 0; i < 5; i++) {
                addCSec.setString((i+1), csParams[i]);
            }//for()
            addCSec.executeUpdate();
            addCSec.close();
            System.out.println("\nCourse section " + csParams[0] + "-"
                + csParams[1] + " added for " + csParams[4] + " " + csParams[3]);
                Main.OutInfo("\nCourse section " + csParams[0] + "-"
                + csParams[1] + " added for " + csParams[4] + " " + csParams[3]);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
    }//addSection()

    public static void addGrade(Connection conn, String gParams[]) throws SQLException  {
        try {
            try {
        String addGradeQ = null;
        addGradeQ = "INSERT INTO ENROLLS_IN VALUES (?,?,?,?,?,?,?)";
        PreparedStatement insGrade = null;
            insGrade = conn.prepareStatement(addGradeQ);
            for(int i = 0; i < 6; i++) {
                insGrade.setString((i+1), gParams[i]);
            }//for()
            insGrade.setDouble(7, Double.parseDouble(gParams[6]));
            insGrade.executeUpdate();
            insGrade.close();
            System.out.println("\nGrade added for " + gParams[0] + " - " + gParams[1]);
            Main.OutInfo("\nGrade added for " + gParams[0] + " - " + gParams[1]);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
    }//addGrade()

    public static void getGradeReport(Connection conn, String nNumber) throws SQLException {
        try {
            try {
        String allInfo = printStudentInfoGR(conn,nNumber) + printCoursesGR(conn,nNumber) + printGPA(conn,nNumber);
        System.out.print(allInfo);
        Main.OutInfo(allInfo);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
    }//getGradeReport()

    public static String printStudentInfoGR(Connection conn, String n) throws SQLException  {
        String sInfo = null;
        sInfo = "";
        String stInfoQ = null;
        stInfoQ = "SELECT Fname,Lname,Nnumber,Degree_prog,Class,Major_name,Minor_name,Bdate "
        + "FROM STUDENT,HAS_MAJOR,HAS_MINOR "
        + "WHERE Nnumber=has_major.nnum AND Nnumber=has_minor.nnum AND Nnumber=?";
        String sInarg1 = null;
        sInarg1 = "\n-------------------------\nSTUDENT INFORMATION\n-------------------------\n";
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(stInfoQ);
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

                sInfo = sInarg1 + "NAME:  " + rSet.getString(1) + " " + rSet.getString(2) + "\n"
                + "N#:    " + rSet.getString(3) + "\n"
                + "BDATE: " + rSet.getString(8) + "\n"
                + "DEGREE PROGRAM: " + rSet.getString(4) + "\n"
                + "CLASS: " + clName + "\n"
                + "MAJOR: " + rSet.getString(6) + "\n"
                + "MINOR: " + rSet.getString(7) + "\n";
            }//while()

            //System.out.print(sInfo);
            return sInfo;

    }//printStudentInfoGR()

    public static String printGPA(Connection conn, String nNumber) throws SQLException  {
        int CH = 0;
        double GP = 0.0;
        double qualityPoints = 0.0;
        int hours = 0;
        //double GPA = 0.0;
        double totalPoints = 0.0;
        double gpa = 0.0;


        String getCreditHrsQ = null;
        getCreditHrsQ = "SELECT Credit_hrs, Grade_point FROM COURSE, ENROLLS_IN WHERE Course_number=Cnum AND Nnum=?";

            PreparedStatement getHrs = null;
            getHrs = conn.prepareStatement(getCreditHrsQ);
            getHrs.setString(1, nNumber);
            ResultSet hrsResults = getHrs.executeQuery();
            while(hrsResults.next()) {
                CH = hrsResults.getInt("Credit_hrs");
                GP = hrsResults.getDouble("Grade_point");
                hours += CH;
                qualityPoints = (CH * GP);
                totalPoints += qualityPoints;
            }//while()
            gpa = totalPoints / hours;
            
            String arg1 = null;
            arg1 = String.valueOf(gpa);
            String arg2 = null;
            arg2 = String.valueOf(hours);
            //System.out.println("\n" + hours + " credit hours");
            String gotGPA = null;
            gotGPA = "\n" + "credit hours: " + arg2 + 
            "\n" + "-------------------------\n GPA:" + arg1
             + "\n-------------------------\n";
            //System.out.println("-------------------------");
            //System.out.printf("GPA: %3.2\n,");
            //System.out.printf("GPA: "+  gpa + "\n");
            //System.out.print(gotGPA + "\n");
            return gotGPA;
    }

    public static String printCoursesGR(Connection conn, String nNumber) throws SQLException  {
        String courses = null;
        courses =  "-------------------------\n"
                    + "ENROLLMENTS\n"
                    + "-------------------------\n";
        String coursesQ = null;
        coursesQ = "SELECT course_num, sem, year, letter_grade, grade_point "
        + "FROM course_section, enrolls_in "
        + "WHERE course_num=cnum AND nnum=?";
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(coursesQ);
            stmt.setString(1, nNumber);
            ResultSet rSet = stmt.executeQuery();
            while(rSet.next()){
                courses += rSet.getString(1) + " "  //course number
                    + rSet.getString(2) + " "       //semester
                    + rSet.getString(3) + " | "     //year
                    + rSet.getString(4) + " | "     //letter grade
                    + rSet.getString(5) + "\n";     //grade point
            }//while
        //System.out.print(courses);
        return courses;
    }//printCoursesGR()
    
    public static void listCourses(Connection conn, String dept) throws SQLException {
        try {
            try {
        String listCoursesQ = null;
        listCoursesQ = "SELECT Course_name, Course_number "
        + "FROM DEPARTMENT D, COURSE C "
        + "WHERE D.Dept_code=C.Course_dept "
        + "AND (D.Dept_name=? OR D.Dept_code=?)";
            PreparedStatement getCourses = null;
            getCourses = conn.prepareStatement(listCoursesQ);
            getCourses.setString(1, dept);
            getCourses.setString(2, dept);
            ResultSet courses = getCourses.executeQuery();
            String cName = null;
            String cNum = null;
            String courseResults = null;
            courseResults =
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
            Main.OutInfo(courseResults);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                Main.homMesAdd = "NumberFormatException, check terminal for details.";
                UI.messOut.setText(Main.homMesAdd);
                Main.homeE();
            }
        } catch(IndexOutOfBoundsException e) {
            Main.homMesAdd = "Invalid IndexOutOfBOundsException by oracle, returning home...";
            UI.messOut.setText(Main.homMesAdd);
            Main.homeE();
        }
    }//listCourses()
}