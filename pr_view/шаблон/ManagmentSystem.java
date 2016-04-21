package logic;

/**
 * Created by Admin on 25.03.2016.
 */

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class ManagementSystem {
    private static Connection con;
    private static ManagementSystem instance;
    private static DataSource dataSource;

    private ManagementSystem() throws Exception{
       //connection ver 2
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            con = DriverManager.getConnection(url, "root", "126874539");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }*/
    }

    public static synchronized ManagementSystem getInstance() throws Exception{
       //connection ver 1
        /*if (instance == null) {
            try {
                instance = new ManagementSystem();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentsDS");

                con = dataSource.getConnection("root","126874539");
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }}*/
       //connection ver 2
        /*  if (instance == null) {
            instance = new ManagementSystem();
        }*/
       //connection ver 3
        if(instance == null){
        instance = new ManagementSystem();
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;
        try {
            fis = new FileInputStream("E://JREF/PROJECT3/src/main/java/logic/db.properties");
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            instance.dataSource = mysqlDS;
            con =  dataSource.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }}

    return instance;}

    public static List getGroups() throws SQLException {
        List groups = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT group_id, groupName, curator, speciality FROM groups");
        while (rs.next()) {
            Group gr = new Group();
            gr.setGroupId(rs.getInt(1));
            gr.setNameGroup(rs.getString(2));
            gr.setCurator(rs.getString(3));
            gr.setSpeciality(rs.getString(4));
            groups.add(gr);
            System.out.println(gr);
        }
        rs.close();
        stmt.close();
        return groups;
    }

    public Collection getAllStudents() throws SQLException {
        Collection students = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM students ORDER BY surName, firstName, patronymic");
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }


    public Collection getStudentsFromGroup(Group group, int year) throws SQLException {
        Collection students = new ArrayList();
        PreparedStatement stmt = con.prepareStatement("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM students "
                + "WHERE group_id =  ? AND  educationYear =  ? "
                + "ORDER BY surName, firstName, patronymic");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }


    public Student getStudentById(int studentId) throws SQLException {
        Student student = null;
        PreparedStatement stmt = con.prepareStatement("SELECT student_id, firstName, patronymic, surName,"
                + "sex, dateOfBirth, group_id, educationYear FROM students WHERE student_id = ?");
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student = new Student(rs);
        }
        rs.close();
        stmt.close();
        return student;
    }

    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE students SET group_id =  ?, educationYear=? "
                + "WHERE group_id =  ? AND  educationYear = ?");
        stmt.setInt(1, newGroup.getGroupId());
        stmt.setInt(2, newYear);
        stmt.setInt(3, oldGroup.getGroupId());
        stmt.setInt(4, oldYear);
        stmt.execute();
    }

    public void removeStudentsFromGroup(Group group, int year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE group_id = ? AND educationYear = ?");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        stmt.execute();
    }

    public void insertStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO students "
                + "(firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)"
                + "VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?)");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, new String(new char[]{student.getSex()}));
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.execute();
    }

    public void updateStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE students "
                + "SET firstName=?, patronymic=?, surName=?, sex=?, dateOfBirth=?, group_id=?,"
                + "educationYear=? WHERE student_id=?");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, new String(new char[]{student.getSex()}));
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.setInt(8, student.getStudentId());
        stmt.execute();
    }

    public void deleteStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE student_id =  ?");
        stmt.setInt(1, student.getStudentId());
        stmt.execute();
    }

   /* public static void main(String[] args) throws Exception {
        ManagementSystem ms = new ManagementSystem();
        ms.getInstance();
        ms.getGroups();
    }*/
}
