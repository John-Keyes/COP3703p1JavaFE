CREATE TABLE STUDENT (
Fname       VARCHAR(30)     NOT NULL,
Lname       VARCHAR(30)     NOT NULL,
Nnumber     CHAR(9)         PRIMARY KEY,
SSN         CHAR(9)         UNIQUE       NOT NULL,
Bdate       DATE            NOT NULL,
Class       CHAR(1)         NOT NULL     CHECK(Class between 1 and 4),
Degree_prog VARCHAR(30),
cPhone      CHAR(10),
cAddress    VARCHAR(30),
pPhone      CHAR(10)        NOT NULL,
pStreet     VARCHAR(30)     NOT NULL,
pCity       VARCHAR(30)     NOT NULL,
pState      CHAR(2)         NOT NULL,
pZip        CHAR(5)         NOT NULL
);

CREATE TABLE DEPARTMENT (
Dept_name       VARCHAR(30)     NOT NULL    UNIQUE,
Dept_code       CHAR(3)         PRIMARY KEY,
Office_number   VARCHAR(5)      NOT NULL,
Office_phone    CHAR(10)        NOT NULL,
College         VARCHAR(50)     NOT NULL
);

CREATE TABLE HAS_MAJOR (
Nnumber     CHAR(9),
MajorDept   CHAR(3),
Major_name  VARCHAR(50)   UNIQUE,
PRIMARY KEY(Nnumber, MajorDept),
FOREIGN KEY(Nnumber) REFERENCES STUDENT,
FOREIGN KEY(MajorDept) REFERENCES DEPARTMENT
);

CREATE TABLE HAS_MINOR (
Nnumber     CHAR(9),
MinorDept   CHAR(3),
Minor_name  VARCHAR(50)   UNIQUE,
PRIMARY KEY(Nnumber, MinorDept),
FOREIGN KEY(Nnumber) REFERENCES STUDENT,
FOREIGN KEY(MinorDept) REFERENCES DEPARTMENT
);

CREATE TABLE COURSE (
Course_name     VARCHAR(30)     UNIQUE      NOT NULL,
Course_number   CHAR(7)         PRIMARY KEY,
Credit_hrs      INT,
Course_level    INT,
Course_desc     VARCHAR(500)
);

CREATE TABLE OFFERS (
Course_no   CHAR(7),
Dcode       CHAR(3),
FOREIGN KEY(Course_no) REFERENCES COURSE(Course_number),
FOREIGN KEY(Dcode) REFERENCES DEPARTMENT(Dept_code)
);

CREATE TABLE COURSE_SECTION (
Course_num      CHAR(7)     NOT NULL,
Section_num     VARCHAR(2)  NOT NULL,
Instructor      VARCHAR(50) NOT NULL,
Year            CHAR(4)     NOT NULL,
Semester        CHAR(1)     NOT NULL,
PRIMARY KEY(Course_num, Section_num),
FOREIGN KEY(Course_num) REFERENCES COURSE(Course_number)
);

CREATE TABLE ENROLLS_IN (
Nnum            CHAR(9),
Cnum            CHAR(7),
Letter_grade    CHAR(1)     NOT NULL,
Number_grade    VARCHAR(3)  NOT NULL,
PRIMARY KEY(Nnum, Cnum),
FOREIGN KEY(Nnum) REFERENCES STUDENT(Nnumber),
FOREIGN KEY(Cnum) REFERENCES COURSE(Course_number)
);
