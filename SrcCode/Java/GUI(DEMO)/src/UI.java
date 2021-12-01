import javax.swing.*;
//import java.io.StringWriter;

/**
 * UI class is where all the buttons, frames, and their parameters are declared
 */

class UI { 

    //Declarations and initial assignments.
    //static StringWriter errors = new StringWriter();
    static JFrame frame1 = new JFrame("G5's School Database"); // The window
    static JButton connButton = new JButton("Connect");
    static JButton closeConn = new JButton("Close Connection");
    static JButton studButton = new JButton("Add Student");    
    static JButton depButton = new JButton("Add Department");
    static JButton coButton = new JButton("Add Course");
    static JButton csButton = new JButton("Add Course Section");
    static JButton grButton= new JButton("Get Grade Report");
    static JButton fcButton = new JButton("Find Courses");
    static JButton agButton = new JButton("Add Student Grade");
    static JButton next = new JButton("Next");  // Next only used for args that don't fit the page(Student).
    static JButton home = new JButton("Home");  //Back to Home page where all the buttons are. Main.homeB
    static JButton submit = new JButton("Submit");   //Submit Queries
    static JTextArea messOut = new JTextArea();
    static JTextArea out = new JTextArea();


    //static JButton next = new JButton("Next");

    static JLabel argLab1 = new JLabel();
    static JLabel argLab2 = new JLabel();
    static JLabel argLab3 = new JLabel();
    static JLabel argLab4 = new JLabel();
    static JLabel argLab5 = new JLabel();
    static JLabel argLab6 = new JLabel();
    static JLabel argLab7 = new JLabel();
    static JLabel argLab8 = new JLabel();
    static JLabel argLab9 = new JLabel();
    static JLabel argLab10 = new JLabel();
    static JLabel argLab11 = new JLabel();
    static JLabel argLab12 = new JLabel();
    static JLabel argLab13 = new JLabel();
    static JLabel argLab14 = new JLabel();
    static JLabel argLab15 = new JLabel();
    static JLabel argLab16 = new JLabel();
    static JLabel argLab17 = new JLabel();
    static JLabel argLab18 = new JLabel();
    static JLabel argLab19 = new JLabel();

    static JTextArea arg1 = new JTextArea();
    static JTextArea arg2 = new JTextArea();
    static JTextArea arg3 = new JTextArea();
    static JTextArea arg4 = new JTextArea();
    static JTextArea arg5 = new JTextArea();
    static JTextArea arg6 = new JTextArea();
    static JTextArea arg7 = new JTextArea();
    static JTextArea arg8 = new JTextArea();
    static JTextArea arg9 = new JTextArea();
    static JTextArea arg10 = new JTextArea();
    static JTextArea arg11 = new JTextArea();
    static JTextArea arg12 = new JTextArea();
    static JTextArea arg13 = new JTextArea();
    static JTextArea arg14 = new JTextArea();
    static JTextArea arg15 = new JTextArea();
    static JTextArea arg16 = new JTextArea();
    static JTextArea arg17 = new JTextArea();
    static JTextArea arg18 = new JTextArea();
    static JTextArea arg19 = new JTextArea();
    //static JScrollPane outPut = new JScrollPane(out);

    //main function

    //Dimensions of the window and its components with their initial properties.
    public static void Startup() {
        frame1.setSize(1000, 800);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connButton.setBounds(400, 700, 200, 30);
        closeConn.setBounds(400, 700, 200, 30);
        studButton.setBounds(150, 200, 200, 30);
        depButton.setBounds(150, 400, 200, 30);
        coButton.setBounds(150, 600, 200, 30);
        csButton.setBounds(650, 200, 200, 30);
        grButton.setBounds(400, 400, 200, 30);
        fcButton.setBounds(650, 400, 200, 30);
        agButton.setBounds(650, 600, 200, 30);
        submit.setBounds(400, 650, 200, 30);
        next.setBounds(400, 650, 200, 30);
        home.setBounds(50, 650, 100, 30);
        messOut.setBounds(100,50,800,100); //(300, 50, 400, 100);
        out.setBounds(100,50,800,600);
        out.setEditable(false);
        messOut.setEditable(false);
        frame1.add(closeConn);
        frame1.add(messOut);
        frame1.add(connButton);
        frame1.add(grButton);
        frame1.add(fcButton);
        frame1.add(studButton);
        frame1.add(depButton);
        frame1.add(coButton);
        frame1.add(csButton);
        frame1.add(agButton);
        frame1.add(submit);
        frame1.add(next);
        frame1.add(home);
        frame1.add(out);
        connButton.setVisible(false);
        closeConn.setVisible(false);
        grButton.setVisible(false);
        fcButton.setVisible(false);
        agButton.setVisible(false);
        studButton.setVisible(false);
        depButton.setVisible(false);
        coButton.setVisible(false);
        csButton.setVisible(false);
        submit.setVisible(false);
        next.setVisible(false);
        home.setVisible(false);
        frame1.setVisible(true);
        messOut.setVisible(true);
        out.setVisible(false);

        //adding these
        argLab1.setBounds(100,180,125,20);
        arg1.setBounds(100,200,125,20);
        argLab2.setBounds(100,280,125,20);
        arg2.setBounds(100,300,125,20);
        argLab3.setBounds(100,380,125,20);
        arg3.setBounds(100,400,125,20);
        argLab4.setBounds(275,180,125,20);
        arg4.setBounds(275,200,125,20);
        argLab5.setBounds(275,280,125,20);
        arg5.setBounds(275,300,125,20);
        argLab6.setBounds(275,380,125,20);
        arg6.setBounds(275,400,125,20);
        argLab7.setBounds(450,180,125,20);
        arg7.setBounds(450,200,125,20);
        argLab8.setBounds(450,280,125,20);
        arg8.setBounds(450,300,125,20);
        argLab9.setBounds(450,380,125,20);
        arg9.setBounds(450,400,125,20);
        argLab10.setBounds(625,180,125,20);
        arg10.setBounds(625,200,125,20);
        argLab11.setBounds(625,280,125,20);
        arg11.setBounds(625,300,125,20);
        argLab12.setBounds(625,380,125,20);
        arg12.setBounds(625,400,125,20);
        argLab13.setBounds(800,180,125,20);
        arg13.setBounds(800,200,125,20);
        argLab14.setBounds(800,280,125,20);
        arg14.setBounds(800,300,125,20);
        argLab15.setBounds(800,380,125,20);
        arg15.setBounds(800,400,125,20);
        argLab16.setBounds(100,180,125,20);
        arg16.setBounds(100,200,125,20);
        argLab17.setBounds(100,280,125,20);
        arg17.setBounds(100,300,125,20);
        argLab18.setBounds(275,280,125,20);
        arg18.setBounds(275,300,125,20);
        argLab19.setBounds(275,180,125,20);
        arg19.setBounds(275,200,125,20);
        frame1.add(argLab1);
        frame1.add(arg1);
        frame1.add(argLab2);
        frame1.add(arg2);
        frame1.add(argLab3);
        frame1.add(arg3);
        frame1.add(argLab4);
        frame1.add(arg4);
        frame1.add(argLab5);
        frame1.add(arg5);
        frame1.add(argLab6);
        frame1.add(arg6);
        frame1.add(argLab7);
        frame1.add(arg7);
        frame1.add(argLab8);
        frame1.add(arg8);
        frame1.add(argLab9);
        frame1.add(arg9);
        frame1.add(argLab10);
        frame1.add(arg10);
        frame1.add(argLab11);
        frame1.add(arg11);
        frame1.add(argLab12);
        frame1.add(arg12);
        frame1.add(argLab13);
        frame1.add(arg13);
        frame1.add(argLab14);
        frame1.add(arg14);
        frame1.add(argLab15);
        frame1.add(arg15);
        frame1.add(argLab16);
        frame1.add(arg16);
        frame1.add(argLab17);
        frame1.add(arg17);
        frame1.add(argLab18);
        frame1.add(arg18);
        frame1.add(argLab19);
        frame1.add(arg19);
        ResetArgs();
    }

    // Connecting to oracle based on our class and group info.
    // Where the user can choose to select info, add info, or update info.

    public static void AddStudArgs() {
        argLab1.setText("First Name");
        argLab1.setVisible(true);
        arg1.setVisible(true);
        argLab2.setText("Last Name");
        argLab2.setVisible(true);
        arg2.setVisible(true);
        argLab3.setText("N#");
        argLab3.setVisible(true);
        arg3.setVisible(true);
        argLab4.setVisible(true);
        argLab4.setText("SSN");
        arg4.setVisible(true);
        argLab5.setVisible(true);
        argLab5.setText("Bdate(YYYY-MM-DD)");
        arg5.setVisible(true);
        argLab6.setVisible(true);
        argLab6.setText("Sex");
        arg6.setVisible(true);
        argLab7.setVisible(true);
        argLab7.setText("Class");
        arg7.setVisible(true);
        argLab8.setVisible(true);
        argLab8.setText("Degree Program");
        arg8.setVisible(true);
        argLab9.setVisible(true);
        argLab9.setText("Current Phone");
        arg9.setVisible(true);
        argLab10.setVisible(true);
        argLab10.setText("Current Address");
        arg10.setVisible(true);
        argLab11.setVisible(true);
        argLab11.setText("Permanent Phone");
        arg11.setVisible(true);
        argLab12.setVisible(true);
        argLab12.setText("Permanent Street");
        arg12.setVisible(true);
        argLab13.setVisible(true);
        argLab13.setText("Permanent City");
        arg13.setVisible(true);
        argLab14.setVisible(true);
        argLab14.setText("Permanent State");
        arg14.setVisible(true);;
        argLab15.setVisible(true);
        argLab15.setText("Permanent Zip");
        arg15.setVisible(true);
    }
    public static void GetStudArgs(String sParams[]) {
        sParams[0] = arg1.getText();
        sParams[1] = arg2.getText();
        sParams[2] = arg3.getText();
        sParams[3] = arg4.getText();
        sParams[4] = arg5.getText();
        sParams[5] = arg6.getText();
        sParams[6] = arg7.getText();
        sParams[7] = arg8.getText();
        sParams[8] = arg9.getText();
        sParams[9] = arg10.getText();
        sParams[10] = arg11.getText();
        sParams[11] = arg12.getText();
        sParams[12] = arg13.getText();
        sParams[13] = arg14.getText();
        sParams[14] = arg15.getText();
        sParams[15] = arg16.getText();
        sParams[16] = arg17.getText();
        sParams[17] = arg18.getText();
        sParams[18] = arg19.getText();
    }

    public static void AddMajnMinArgs() {
        argLab16.setText("Major Dept Code");
        argLab16.setVisible(true);
        arg16.setVisible(true);
        argLab17.setText("Major Name");
        argLab17.setVisible(true);
        arg17.setVisible(true);
        argLab18.setText("Minor Dept Code");
        argLab18.setVisible(true);
        arg18.setVisible(true);
        argLab19.setVisible(true);
        argLab19.setText("Minor Name");
        arg19.setVisible(true);
    }
    public static void AddDepArgs() {
        argLab1.setText("Name");
        argLab1.setVisible(true);
        arg1.setVisible(true);
        argLab2.setText("Code");
        argLab2.setVisible(true);
        arg2.setVisible(true);
        argLab3.setText("Office #");
        argLab3.setVisible(true);
        arg3.setVisible(true);
        argLab4.setVisible(true);
        argLab4.setText("Office Phone");
        arg4.setVisible(true);
        argLab5.setVisible(true);
        argLab5.setText("College");
        arg5.setVisible(true);
    }

    public static void GetDepArgs(String dParams[]) {
        dParams[0] = arg1.getText();
        dParams[1] = arg2.getText();
        dParams[2] = arg3.getText();
        dParams[3] = arg4.getText();
        dParams[4] = arg5.getText();
    }

    public static void AddCoArgs() {
        argLab1.setText("Name");
        argLab1.setVisible(true);
        arg1.setVisible(true);
        argLab2.setText("Number");
        argLab2.setVisible(true);
        arg2.setVisible(true);
        argLab3.setText("Credit Hours");
        argLab3.setVisible(true);
        arg3.setVisible(true);
        argLab4.setVisible(true);
        argLab4.setText("Level(1-7)");
        arg4.setVisible(true);
        argLab5.setVisible(true);
        argLab5.setText("Description");
        arg5.setVisible(true);
    }

    public static void GetCoArgs(String coParams[]) {
        coParams[0] = arg1.getText();
        coParams[1] = arg2.getText();
        coParams[2] = arg3.getText();
        coParams[3] = arg4.getText();
        coParams[4] = arg5.getText();
    }

    public static void AddCsArgs() {
        argLab1.setText("Course #");
        argLab1.setVisible(true);
        arg1.setVisible(true);
        argLab2.setText("Section #");
        argLab2.setVisible(true);
        arg2.setVisible(true);
        argLab3.setText("Instructor");
        argLab3.setVisible(true);
        arg3.setVisible(true);
        argLab4.setVisible(true);
        argLab4.setText("Year");
        arg4.setVisible(true);
        argLab5.setVisible(true);
        argLab5.setText("Semester");
        arg5.setVisible(true);
        /*argLab6.setVisible(true);
        argLab6.setText("Sex");
        arg6.setVisible(true);*/
    }

    public static void GetCsArgs(String csParams[]) {
        csParams[0] = arg1.getText();
        csParams[1] = arg2.getText();
        csParams[2] = arg3.getText();
        csParams[3] = arg4.getText();
        csParams[4] = arg5.getText();
    }
    public static void AddAgArgs() {
        argLab1.setText("Nnumber");
        argLab1.setVisible(true);
        arg1.setVisible(true);
        argLab2.setText("Course Number");
        argLab2.setVisible(true);
        arg2.setVisible(true);
        argLab3.setText("Section Number");
        argLab3.setVisible(true);
        arg3.setVisible(true);
        argLab4.setVisible(true);
        argLab4.setText("Year");
        arg4.setVisible(true);
        argLab5.setVisible(true);
        argLab5.setText("Semester");
        arg5.setVisible(true);
        argLab6.setVisible(true);
        argLab6.setText("Letter Grade");
        arg6.setVisible(true);
        argLab7.setVisible(true);
        argLab7.setText("Grade Point(0.0-4.0)");
        arg7.setVisible(true);
    }

    public static void GetAgArgs(String agParams[]) {
        agParams[0] = arg1.getText();
        agParams[1] = arg2.getText();
        agParams[2] = arg3.getText();
        agParams[3] = arg4.getText();
        agParams[4] = arg5.getText();
        agParams[5] = arg6.getText();
        agParams[6] = arg7.getText();
    }

    public static void ResetArgs() {
        argLab1.setVisible(false);
        argLab1.setText(null);
        arg1.setVisible(false);
        arg1.setText(null);
        argLab2.setVisible(false);
        argLab2.setText(null);
        arg2.setVisible(false);
        arg2.setText(null);
        argLab3.setVisible(false);
        argLab3.setText(null);
        arg3.setVisible(false);
        arg3.setText(null);
        argLab4.setVisible(false);
        argLab4.setText(null);
        arg4.setVisible(false);
        arg4.setText(null);
        argLab5.setVisible(false);
        argLab5.setText(null);
        arg5.setVisible(false);
        arg5.setText(null);
        argLab6.setVisible(false);
        argLab6.setText(null);
        arg6.setVisible(false);
        arg6.setText(null);
        argLab7.setVisible(false);
        argLab7.setText(null);
        arg7.setVisible(false);
        arg7.setText(null);
        argLab8.setVisible(false);
        argLab8.setText(null);
        arg8.setVisible(false);
        arg8.setText(null);
        argLab9.setVisible(false);
        argLab9.setText(null);
        arg9.setVisible(false);
        arg9.setText(null);
        argLab10.setVisible(false);
        argLab10.setText(null);
        arg10.setVisible(false);
        arg10.setText(null);
        argLab11.setVisible(false);
        argLab11.setText(null);
        arg11.setVisible(false);
        arg11.setText(null);
        argLab12.setVisible(false);
        argLab12.setText(null);
        arg12.setVisible(false);
        arg12.setText(null);
        argLab13.setVisible(false);
        argLab13.setText(null);
        arg13.setVisible(false);
        arg13.setText(null);
        argLab14.setVisible(false);
        argLab14.setText(null);
        arg14.setVisible(false);
        arg14.setText(null);
        argLab15.setVisible(false);
        argLab15.setText(null);
        arg15.setVisible(false);
        arg15.setText(null);
        argLab16.setVisible(false);
        argLab16.setText(null);
        arg16.setVisible(false);
        arg16.setText(null);
        argLab17.setVisible(false);
        argLab17.setText(null);
        arg17.setVisible(false);
        arg17.setText(null);
        argLab18.setVisible(false);
        argLab18.setText(null);
        arg18.setVisible(false);
        arg18.setText(null);
        argLab19.setVisible(false);
        argLab19.setText(null);
        arg19.setVisible(false);
        arg19.setText(null);
        out.setText(null);
        out.setVisible(false);
    }

    public static void ShowButtons() {
        studButton.setVisible(true); // used for inserting student
        depButton.setVisible(true); //used for inserting department
        coButton.setVisible(true); //used for inserting course
        csButton.setVisible(true); // used for inserting course section
        grButton.setVisible(true); //used for selecting grade report
        fcButton.setVisible(true); //used for finding course (select)
        agButton.setVisible(true); //used for adding a grade to an existing student (Add grade)
    }

    public static void HideButtons() {
        studButton.setVisible(false); // used for inserting student
        depButton.setVisible(false); //used for inserting department
        coButton.setVisible(false); //used for inserting course
        csButton.setVisible(false); // used for inserting course section
        grButton.setVisible(false); //used for selecting grade report
        fcButton.setVisible(false); //used for finding course (select)
        agButton.setVisible(false); //used for adding a grade to an existing student (Add grade)
    }

    public static void HideArgs() {
        argLab1.setVisible(false);
        arg1.setVisible(false);
        argLab2.setVisible(false);
        arg2.setVisible(false);
        argLab3.setVisible(false);
        arg3.setVisible(false);
        argLab4.setVisible(false);
        arg4.setVisible(false);
        argLab5.setVisible(false);
        arg5.setVisible(false);
        argLab6.setVisible(false);
        arg6.setVisible(false);
        argLab7.setVisible(false);
        arg7.setVisible(false);
        argLab8.setVisible(false);
        arg8.setVisible(false);
        argLab9.setVisible(false);
        arg9.setVisible(false);
        argLab10.setVisible(false);
        arg10.setVisible(false);
        argLab11.setVisible(false);
        arg11.setVisible(false);
        argLab12.setVisible(false);
        arg12.setVisible(false);
        argLab13.setVisible(false);
        arg13.setVisible(false);
        argLab14.setVisible(false);
        arg14.setVisible(false);
        argLab15.setVisible(false);
        arg15.setVisible(false);
        argLab16.setVisible(false);
        arg16.setVisible(false);
        argLab17.setVisible(false);
        arg17.setVisible(false);
        argLab18.setVisible(false);
        arg18.setVisible(false);
        argLab19.setVisible(false);
        arg19.setVisible(false);
    }

    public static void HideStud1Args() {
        argLab1.setVisible(false);
        arg1.setVisible(false);
        argLab2.setVisible(false);
        arg2.setVisible(false);
        argLab3.setVisible(false);
        arg3.setVisible(false);
        argLab4.setVisible(false);
        arg4.setVisible(false);
        argLab5.setVisible(false);
        arg5.setVisible(false);
        argLab6.setVisible(false);
        arg6.setVisible(false);
        argLab7.setVisible(false);
        arg7.setVisible(false);
        argLab8.setVisible(false);
        arg8.setVisible(false);
        argLab9.setVisible(false);
        arg9.setVisible(false);
        argLab10.setVisible(false);
        arg10.setVisible(false);
        argLab11.setVisible(false);
        arg11.setVisible(false);
        argLab12.setVisible(false);
        arg12.setVisible(false);
        argLab13.setVisible(false);
        arg13.setVisible(false);
        argLab14.setVisible(false);
        arg14.setVisible(false);
        argLab15.setVisible(false);
        arg15.setVisible(false);
    }
    public static void HideStud2Args() {
        argLab16.setVisible(false);
        arg16.setVisible(false);
        argLab17.setVisible(false);
        arg17.setVisible(false);
        argLab18.setVisible(false);
        arg18.setVisible(false);
        argLab19.setVisible(false);
        arg19.setVisible(false);
    }
}
