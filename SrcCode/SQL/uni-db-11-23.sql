/* * * * * * * * * * * * * *
 *  1. Create all tables   *
 * * * * * * * * * * * * * */


CREATE TABLE STUDENT (
Fname       VARCHAR(30)     NOT NULL,
Lname       VARCHAR(30)     NOT NULL,
Nnumber     CHAR(9)         PRIMARY KEY,
SSN         CHAR(9)         UNIQUE       NOT NULL,
Bdate       DATE            NOT NULL,
Sex	    CHAR(1)	    NOT NULL,
Class       CHAR(1)         NOT NULL     CHECK(Class between 1 and 6),
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
College         VARCHAR(100)     NOT NULL
);

CREATE TABLE HAS_MAJOR (
Nnum        CHAR(9),
Major_dept  CHAR(3),
Major_name  VARCHAR(50) UNIQUE,
PRIMARY KEY(Nnum, Major_dept),
FOREIGN KEY(Nnum) REFERENCES STUDENT(Nnumber),
FOREIGN KEY(Major_dept) REFERENCES DEPARTMENT(Dept_code)
);

CREATE TABLE HAS_MINOR (
Nnum        CHAR(9),
Minor_dept  CHAR(3),
Minor_name  VARCHAR(50) UNIQUE,
PRIMARY KEY(Nnum, Minor_dept),
FOREIGN KEY(Nnum) REFERENCES STUDENT(Nnumber),
FOREIGN KEY(Minor_dept) REFERENCES DEPARTMENT(Dept_code)
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
Semester        CHAR(1)     NOT NULL, /* s, u, or f */
PRIMARY KEY(Course_num, Section_num, Year, Semester),
FOREIGN KEY(Course_num) REFERENCES COURSE(Course_number)
);

CREATE TABLE ENROLLS_IN (
Nnum            CHAR(9),
Cnum            CHAR(7)     UNIQUE, /* one grade allowed per course */
Snum		VARCHAR(2)  NOT NULL,
Yr		CHAR(4)     NOT NULL,
Sem		CHAR(1)     NOT NULL,
Letter_grade    CHAR(1)     NOT NULL,
Number_grade    INT  	NOT NULL 	CHECK(Number_grade between 0 and 100),
PRIMARY KEY(Nnum, Cnum, Snum, Yr, Sem),
FOREIGN KEY(Nnum) REFERENCES STUDENT(Nnumber),
FOREIGN KEY(Cnum,Snum,Yr,Sem) REFERENCES COURSE_SECTION
);


/* * * * * * * * * * * * * * * * *
 * 2. Populate with Departments  *
 * * * * * * * * * * * * * * * * */


INSERT INTO DEPARTMENT
VALUES ('Health',1,101,9041111111,'Brooks College of Health');

INSERT INTO DEPARTMENT
VALUES ('Business',2,201,9042222222,'Coggin College of Business');

INSERT INTO DEPARTMENT
VALUES ('COAS',3,301,9043333333,'College of Arts and Sciences');

INSERT INTO DEPARTMENT
VALUES ('CCEC',4,401,9044444444,'College of Computing, Engineering, and Construction');

INSERT INTO DEPARTMENT
VALUES ('Education',5,501,9045555555,'College of Education and Human Services');


/* * * * * * * * * * * * * * * * * * *
 * 3. Populate with Courses          *
 * (COURSE, OFFERS, COURSE_SECTION)  *
 * * * * * * * * * * * * * * * * * * */


/* COURSES FROM HEALTH DEPARTMENT */


/* bio1000 */

INSERT INTO COURSE VALUES ('Intro to Biology','bio1000',3,1000,'Introduction to biology. Cells, life, etc.');
INSERT INTO OFFERS VALUES ('bio1000','1');
INSERT INTO COURSE_SECTION VALUES ('bio1000','1','Instructor1','2021','s');
INSERT INTO COURSE_SECTION VALUES ('bio1000','2','Instructor1','2021','s');
INSERT INTO COURSE_SECTION VALUES ('bio1000','1','Instructor1','2021','f');

/* bio2000 */

INSERT INTO COURSE VALUES ('Intermediate Biology','bio2000',3,1000,'Continuation of bio1000.');
INSERT INTO OFFERS VALUES ('bio2000','1');
INSERT INTO COURSE_SECTION VALUES ('bio2000','1','Instructor1','2021','u');
INSERT INTO COURSE_SECTION VALUES ('bio2000','1','Instructor1','2021','f');
INSERT INTO COURSE_SECTION VALUES ('bio2000','2','Instructor1','2021','f');

/* hun2000 */

INSERT INTO COURSE VALUES ('Human Nutrition','hun2000',3,2000,'Human nutrient needs and sources.');
INSERT INTO OFFERS VALUES ('hun2000','1');
INSERT INTO COURSE_SECTION VALUES ('hun2000','1','Instructor2','2021','u');
INSERT INTO COURSE_SECTION VALUES ('hun2000','1','Instructor2','2021','f');
INSERT INTO COURSE_SECTION VALUES ('hun2000','2','Instructor3','2021','f');



/* COURSES FROM BUSINESS DEPARTMENT */


/* fin2000 */

INSERT INTO COURSE VALUES ('Financial Accounting','fin2000',3,2000,'Principles of financial accounting, accounting equation, creating financial statements.');
INSERT INTO OFFERS VALUES ('fin2000','2');
INSERT INTO COURSE_SECTION VALUES ('fin2000','1','Instructor4','2021','f');
INSERT INTO COURSE_SECTION VALUES ('fin2000','2','Instructor4','2021','f');
INSERT INTO COURSE_SECTION VALUES ('fin2000','3','Instructor4','2021','f');

/* eco1000 */

INSERT INTO COURSE VALUES ('Economics','eco1000',3,1000,'Introduction to local and global economics.');
INSERT INTO OFFERS VALUES ('eco1000','2');
INSERT INTO COURSE_SECTION VALUES ('eco1000','1','Instructor5','2021','s');
INSERT INTO COURSE_SECTION VALUES ('eco1000','1','Instructor5','2021','u');
INSERT INTO COURSE_SECTION VALUES ('eco1000','1','Instructor5','2021','f');

/* mgm3000 */

INSERT INTO COURSE VALUES ('Business Management','mgm3000',3,3000,'Principles of managing a business.');
INSERT INTO OFFERS VALUES ('mgm3000','2');
INSERT INTO COURSE_SECTION VALUES ('mgm3000','1','Instructor4','2021','s');
INSERT INTO COURSE_SECTION VALUES ('mgm3000','2','Instructor4','2021','s');
INSERT INTO COURSE_SECTION VALUES ('mgm3000','1','Instructor5','2021','f');



/* COURSES FROM ARTS & SCIENCES DEPARTMENT */


INSERT INTO COURSE VALUES ('Drawing I','art1000',4,1000,'Drawing introduction.');

INSERT INTO OFFERS VALUES ('art1000','3');

/********/

INSERT INTO COURSE VALUES ('Chemistry I','chm1000',4,1000,'Chemistry introduction.');

INSERT INTO OFFERS VALUES ('chm1000','3');

/********/

INSERT INTO COURSE VALUES ('Calculus I','mac2000',4,2000,'Calculus.');

INSERT INTO OFFERS VALUES ('mac2000','3');



/* COURSES FROM CCEC DEPARTMENT */


INSERT INTO COURSE VALUES ('Operating Systems','cop4000',3,4000,'Windows, Mac, Linux, etc.');

INSERT INTO OFFERS VALUES ('cop4000','4');

/********/

INSERT INTO COURSE VALUES ('Physics I','phy2000',3,2000,'Everything is math.');

INSERT INTO OFFERS VALUES ('phy2000','4');

/********/

INSERT INTO COURSE VALUES ('Linear Control Systems','eel4000',4,4000,'Something to do with electricity.');

INSERT INTO OFFERS VALUES ('eel4000','4');



/* COURSES FROM EDU + HUMAN SVCS DEPARTMENT */


INSERT INTO COURSE
VALUES ('American Sign Language I','asl1000',3,1000,'Foundational American Sign Language.');

INSERT INTO OFFERS
VALUES ('asl1000','5');

/********/

INSERT INTO COURSE
VALUES ('Instructional Design','edu3000',3,3000,'Principles of classroom and curriculum design.');

INSERT INTO OFFERS
VALUES ('edu3000','5');

/********/

INSERT INTO COURSE
VALUES ('Sports','spo2000',3,2000,'Sports????');

INSERT INTO OFFERS
VALUES ('spo2000','5');


/* * * * * * * * * * * * * * * *
 * 4. Populate with Students   *
 * * * * * * * * * * * * * * * */


INSERT INTO STUDENT
VALUES (
    'Michelangelo',
    'Simoni',
    'n00000003',
    '999999999',
    TO_DATE('2000-03-06','YYYY-MM-DD'),
    'M',
    4,
    'Bachelor of Arts',
    null,
    null,
    '2071234567',
    '1 Ashbury Street',
    'Los Angeles',
    'CA',
    '12345'
);


INSERT INTO HAS_MAJOR VALUES ('n00000003','3','Religious Studies');
INSERT INTO HAS_MINOR VALUES ('n00000003','3','Sculpture');

INSERT INTO STUDENT
VALUES (
    'Skylar',
    'McCloud',
    'n00000002',
    '101001001',
    TO_DATE('1995-02-14','YYYY-MM-DD'),
    'N',
    3,
    'Bachelor of Science',
    null,
    null,
    '9041234567',
    '4 Pennsylvania Avenue',
    'Washington',
    'DC',
    '80808'
);

INSERT INTO HAS_MAJOR VALUES ('n00000002','3','Political Science');
INSERT INTO HAS_MINOR VALUES ('n00000002','1','Environmental Studies');


INSERT INTO STUDENT
VALUES (
    'Apple',
    'Jackson',
    'n00000001',
    '111223333',
    TO_DATE('1990-01-01','YYYY-MM-DD'),
    'F',
    1,
    'Bachelor of Arts',
    null,
    null,
    '8081234567',
    '123 ABC Street',
    'New York',
    'NY',
    '10001'
);

INSERT INTO HAS_MAJOR VALUES ('n00000001',3,'Music');
INSERT INTO HAS_MINOR VALUES ('n00000001',2,'Marketing');



/* * * * * * * * * * * *
 * 5. Generate queries *
 * * * * * * * * * * * */


/* ADD GRADE TO STUDENT FOR GIVEN COURSE/SECTION */

/*
 * format:
 *
 * INSERT INTO ENROLLS_IN
 * VALUES('Nnumber',
 * 'courseNumber',
 * 'sectionNumber',
 * 'Year',
 * 'semester',
 * 'letterGrade',
 * numberGrade);    *type int between 0 and 100
 */

INSERT INTO ENROLLS_IN VALUES('n00000001','bio1000','1','2021','s','A',97);
INSERT INTO ENROLLS_IN VALUES('n00000001','bio2000','1','2021','f','B',84);


/* LIST OF COURSES OFFERED BY DEPARTMENT GIVEN DEPARTMENT NAME OR CODE */

/* format:
 * SELECT Dept_name, Course_name, Course_number
 * FROM DEPARTMENT D, OFFERS O, COURSE C
 * WHERE C.Course_number=O.Course_no AND D.Dept_code=O.Dcode AND
 * (D.Dept_name='given-value' OR D.Dept_code='given-value');
 */

SELECT Dept_name, Course_name, Course_number
FROM DEPARTMENT D, OFFERS O, COURSE C
WHERE C.Course_number=O.Course_no AND D.Dept_code=O.Dcode AND
  (D.Dept_name='CCEC' OR D.Dept_code='CCEC');

SELECT Dept_name, Course_name, Course_number
FROM DEPARTMENT D, OFFERS O, COURSE C
WHERE C.Course_number=O.Course_no AND D.Dept_code=O.Dcode AND
  (D.Dept_name='1' OR D.Dept_code='1');

/* GENERATE GRADE REPORT - TODO */
