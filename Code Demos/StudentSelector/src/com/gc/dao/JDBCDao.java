package com.gc.dao;

import com.gc.dto.CustomUserDetails;
import com.gc.dto.GroupDto;
import com.gc.dto.StudentDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

/**
 * Created by maurice on 6/27/17.
 */
public class JDBCDao implements Dao {
    //JDBC Connection details
    private static final String JDBC_MYSQL_DRIVER_STRING = "com.mysql.jdbc.Driver";
//    private String CONNECTION_URL = "jdbc:mysql://localhost:3306/studentPickerDB";//Use this part to run locally + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private String CONNECTION_URL = "jdbc:mysql://studentpickerdb.cnbrf3dwerbk.us-east-2.rds.amazonaws.com:3306/studentPickerDB";//aws DB
    private String USERNAME = "NoneOfYourBusiness";//THIS IS BAD - but OK for localhost!!! - replace with environment variables latter
    private String PWD = "ReallyNoneOfYourBusiness";//THIS IS BAD - but OK for localhost!!! - replace with environment variables latter

    //SQL statements
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students (`first_name`,`last_name`,`status`, `group`) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PICK_RANDOM_SQL = "UPDATE students SET status = \"T\" WHERE status = \"A\" AND `group` = ? ORDER BY Rand() Limit ?";
    private static final String SELECT_PICK_RANDOM_SQL = "SELECT * FROM students WHERE status = \"T\" AND `group` = ?";
    private static final String UPDATE_DISABLE_RANDOM_STUDENTS = "UPDATE students SET status = \"X\" WHERE status = \"T\" AND `group` = ?";
    private static final String UPDATE_STUDENTS_STATUS_SQL = "UPDATE students SET status = ? WHERE `group` = ?";
    private static final String SELECT_STUDENTS_SQL = "SELECT * FROM students WHERE `group` =?";
    private static final String SELECT_GROUPS_SQL = "SELECT * FROM groups";
    private static final String FIND_USER_BY_USERNAME_SQL = "SELECT username,password,enabled, userid FROM users WHERE username=?";
    private static final String AUTHORITIES_BY_USERNAME_SQL = "SELECT role from user_roles where username =?";

    /*
    * Default constructor
     */
    public JDBCDao() {
        //1. Load jdbc driver
        try {
            //Sensitive information is stored in environment variables and retreived here
            //URI uri = new URI(System.getProperty("DATABASE_URL"));//Database URL stored in environment variable for Tomcat AWS environments
            //URI uri = new URI(System.getenv("DATABASE_URL"));//Database URL stored in environment variable for Java SE environments

            //String[] userInfo = uri.getUserInfo().split(":");// get username and
            // password from
            // uri string
            //USERNAME = userInfo[0];
            //PWD = userInfo[1];
            //CONNECTION_URL = "jdbc:mysql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();//Use this part to run locally + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            Class.forName(JDBC_MYSQL_DRIVER_STRING);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//TODO replace with write to log statement
        }
    }

    /*
     * Add student list to DB
    */
    public void addStudents(List<StudentDto> students) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            stmt = connection.prepareStatement(INSERT_STUDENTS_SQL);
            for(StudentDto student: students) {//do batch insert
                stmt.setString(1, student.getFirstName());
                stmt.setString(2, student.getLastName());
                stmt.setString(3, student.getStatus());
                stmt.setString(4, student.getGroup());
                stmt.addBatch();
            }
            //4. Execute batch insert
            stmt.executeBatch();
            //close connection - prevent resource leaks
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /*
    * Get a random list of students who have not already been selected (status = "X"). The number of students
    * selected is based on the listsize parameter submitted in the form
     */
    public List<StudentDto> getRandomStudents(int listSize, String group) {
        Connection connection = null;
        PreparedStatement stmt = null;
        PreparedStatement pickRandom = null;
        List<StudentDto> students = new ArrayList<StudentDto>();
        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);

            /*
            *   Start transactions - needed because I have multiple dependant SQL statements
            *   Ref: https://www.tutorialspoint.com/jdbc/jdbc-transactions.htm
            */
            connection.setAutoCommit(false);
            //3. Create statements
            //Set the status column of a random selection of student to T (=temp)
            pickRandom = connection.prepareStatement(UPDATE_PICK_RANDOM_SQL);
            pickRandom.setString(1, group);
            pickRandom.setInt(2, listSize);
            pickRandom.execute();//select random set of students

            //Select the student with a  T (=temp) in the status column - random students
            stmt = connection.prepareStatement(SELECT_PICK_RANDOM_SQL);
            stmt.setString(1, group);
            ResultSet resultSet = stmt.executeQuery();
            //parse result set and populate students List
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                StudentDto student = new StudentDto(firstName, lastName);
                students.add(student);//add this student to array list
            }

            //Update the status column of the randomly selected students to X (=disabled)
            stmt = connection.prepareStatement(UPDATE_DISABLE_RANDOM_STUDENTS);
            stmt.setString(1, group);
            stmt.executeUpdate();

            // If there is no error. -End transaction
            connection.commit();

            //close connection - prevent resource leaks
            pickRandom.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here...." + e.getMessage());
            try{
                if(connection!=null)
                    connection.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }finally {
            //finally block used to close resources
            try {
                if(pickRandom != null)
                    pickRandom.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                // nothing we can do
            }
            
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return students;
    }

    /*
    * Resets all students in the database to status parameter A (=available), T (=temporary), X (=unavailable)
    */
    public void resetStudentListStatus(String status, String group) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);

            //3. Create statements
            //Set the status column of a random selection of student to T (=temp)
            stmt = connection.prepareStatement(UPDATE_STUDENTS_STATUS_SQL);
            stmt.setString(1, status);
            stmt.setString(2, group);
            stmt.execute();

            //close resources to prevent leaks
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
            try{
                if(connection!=null)
                    connection.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /*
    * Get list of all students from DB.
     */    
    public List<StudentDto> getStudents(String group) {
        Connection connection = null;
        PreparedStatement stmt = null;
        List<StudentDto> students = new ArrayList<StudentDto>();
        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);

            //3. Create statements
            //Set the status column of a random selection of student to T (=temp)
            stmt = connection.prepareStatement(SELECT_STUDENTS_SQL);
            stmt.setString(1, group);

            ResultSet resultSet = stmt.executeQuery();
            //parse result set and populate students List
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String status = resultSet.getString("status");
                
                StudentDto student = new StudentDto(firstName, lastName, status);
                students.add(student);//add this student to array list
            }            

            //close resources to prevent leaks
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try        
        return students;
    }

    /*
    * Get list of groups from groups DB table
    */
    public List<GroupDto> getGroups() {
        Connection connection = null;
        Statement stmt = null;
        List<GroupDto> groups = new ArrayList<GroupDto>();

        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);

            //3. Create statements
            //Set the status column of a random selection of student to T (=temp)
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SELECT_GROUPS_SQL);
            //parse result set and populate students List
            while (resultSet.next()){
                int groupId = resultSet.getInt("groupid");
                String name = resultSet.getString("name");
                String displayName = resultSet.getString("displayname");

                GroupDto group = new GroupDto(groupId, name, displayName);
                groups.add(group);//add this student to array list
            }

            //close resources to prevent leaks
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return groups;
    }

    /*
    * Get user by user name
    * Ref: http://www.baeldung.com/spring-security-authentication-with-a-database
     */
    public User findByUserName(String username) {
        Connection connection = null;
        PreparedStatement stmt = null;
        PreparedStatement stmtAuth = null;
        User user = null;
        try {
            //2. Get connection
            connection = getConnection(CONNECTION_URL, USERNAME, PWD);

            //3. Create statements
            //get authorities
            stmtAuth = connection.prepareStatement(AUTHORITIES_BY_USERNAME_SQL);
            stmtAuth.setString(1, username);
            ResultSet resultSetAuth = stmtAuth.executeQuery();
            StringBuilder roles = new StringBuilder();
            //iterate through results
            while (resultSetAuth.next()){
                //String userName = resultSetAuth.getString("username");
                String role = resultSetAuth.getString("role");
                roles.append(role).append(",");
            }
            roles.deleteCharAt(roles.length()-1);//remove trailing comma
            //Set the status column of a random selection of student to T (=temp)
            stmt = connection.prepareStatement(FIND_USER_BY_USERNAME_SQL);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            //iterate through results - should be unique or null
            while (resultSet.next()){
                int userid = resultSet.getInt("userid");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(userName, password, AuthorityUtils.commaSeparatedStringToAuthorityList(roles.toString()));
            }

            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            //close resources to prevent leaks
            stmt.close();
            stmtAuth.close();
            connection.close();
        } catch (SQLException e) {
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here...." + e.getMessage());
            try{
                if(connection!=null)
                    connection.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do

            try{
                if(stmtAuth!=null)
                    stmtAuth.close();
            }catch(SQLException se2){
            }// nothing we can do

            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return user;
    }
}
