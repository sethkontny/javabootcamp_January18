package com.gc.libterm.dao;

import com.gc.libterm.dto.BookDto;
import com.gc.libterm.dto.LibrariesDto;
import com.gc.libterm.dto.LibraryInfoDto;
import com.gc.libterm.dto.LibraryItemDto;
import org.hibernate.cfg.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.getConnection;

/**
 * Created by maurice on 5/10/17.
 */
public class JDBCDao implements Dao{

    private static final String JDBC_MYSQL_DRIVER_STRING = "com.mysql.jdbc.Driver";
    private String CONNECTION_URL = "";
    private static final String LIBRARYLIST_SQL = "SELECT * FROM libitems";
    private String USERNAME = "";
    private String PWD = "";
    private static final String GET_BY_AUTHOR_SQL = "SELECT * FROM libitems WHERE author LIKE concat('%', ?,'%')";
    private static final String GET_BY_TITLE_KEYWORD_SQL = "SELECT * FROM libitems WHERE title LIKE concat('%', ?, '%')";
    private static final String GET_ITEM_BY_ID_SQL = "SELECT * FROM libitems WHERE bookid = ?";
    private static final String INSERT_ITEM_SQL = "INSERT INTO libitems (`title`,`author`,`status`,`thumbURL`) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ITEM_SQL = "UPDATE libitems SET status = ?, dueDate = ? WHERE bookid = ?";

    public JDBCDao() {
        //1. Load jdbc driver
        try {
            Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
            URI uri = new URI(System.getProperty("DATABASE_URL"));//Database URL stored in environment variable for Tomcat AWS environments
            //URI uri = new URI(System.getenv("DATABASE_URL"));//Database URL stored in environment variable for Java SE environments

            String[] userInfo = uri.getUserInfo().split(":");// get username and
            // password from
            // uri string
            USERNAME = userInfo[0];
            PWD = userInfo[1];
            CONNECTION_URL = "jdbc:mysql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();//Use this part to run locally + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

            Class.forName(JDBC_MYSQL_DRIVER_STRING);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//TODO replace with write to log statement
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<LibraryItemDto> getLibraryItemList() {
        ArrayList<LibraryItemDto> resultList = new ArrayList<LibraryItemDto>();

        try {

        //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
        //3. Create statement
            Statement stmt = connection.createStatement();

        //4. Execute query
            ResultSet resultSet = stmt.executeQuery(LIBRARYLIST_SQL);

        //5. Process resultSet & create Book object from resultSet data
            while (resultSet.next()){
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                boolean status = resultSet.getBoolean("status");
                Date dueDate = resultSet.getDate("dueDate");
                String thumbURL = resultSet.getString("thumbURL");

                LibraryItemDto book = new BookDto(title, author, status, dueDate, thumbURL);
                //6. Add book object to ArrayList
                resultList.add(book);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //7. Return ArrayList of Book objects
        return resultList;
    }


    public ArrayList<LibraryItemDto> getLibraryItemByAuthor(String author) {
        ArrayList<LibraryItemDto> resultList = new ArrayList<LibraryItemDto>();

        try {

            //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            PreparedStatement stmt = connection.prepareStatement(GET_BY_AUTHOR_SQL);
            stmt.setString(1, author);

            //4. Execute query
            ResultSet resultSet = stmt.executeQuery();

            //5. Process resultSet & create Book object from resultSet data
            while (resultSet.next()){
                String title = resultSet.getString("title");
                String authorResult = resultSet.getString("author");
                boolean status = resultSet.getBoolean("status");
                Date dueDate = resultSet.getDate("dueDate");
                String thumbURL = resultSet.getString("thumbURL");

                LibraryItemDto book = new BookDto(title, authorResult, status, dueDate, thumbURL);
                //6. Add book object to ArrayList
                resultList.add(book);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //7. Return ArrayList of Book objects
        return resultList;
    }


    public ArrayList<LibraryItemDto> getLibraryItemByTitle(String titleKeyWord) {
        ArrayList<LibraryItemDto> resultList = new ArrayList<LibraryItemDto>();

        try {
            //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            PreparedStatement stmt = connection.prepareStatement(GET_BY_TITLE_KEYWORD_SQL);
            stmt.setString(1, titleKeyWord);

            //4. Execute query
            ResultSet resultSet = stmt.executeQuery();

            //5. Process resultSet & create Book object from resultSet data
            while (resultSet.next()){
                String title = resultSet.getString("title");
                String authorResult = resultSet.getString("author");
                boolean status = resultSet.getBoolean("status");
                Date dueDate = resultSet.getDate("dueDate");
                String thumbURL = resultSet.getString("thumbURL");

                LibraryItemDto book = new BookDto(title, authorResult, status, dueDate, thumbURL);
                //6. Add book object to ArrayList
                resultList.add(book);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //7. Return ArrayList of Book objects
        return resultList;
    }


    public LibraryItemDto getLibraryItemById(int itemId) {
        LibraryItemDto book = null;
        try {
            //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            PreparedStatement stmt = connection.prepareStatement(GET_ITEM_BY_ID_SQL);
            stmt.setInt(1, itemId);

            //4. Execute query
            ResultSet resultSet = stmt.executeQuery();

            //5. Process resultSet & create Book object from resultSet data
            while (resultSet.next()){
                String title = resultSet.getString("title");
                String authorResult = resultSet.getString("author");
                boolean status = resultSet.getBoolean("status");
                Date dueDate = resultSet.getDate("dueDate");
                String thumbURL = resultSet.getString("thumbURL");

                book = new BookDto(title, authorResult, status, dueDate, thumbURL);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //7. Return ArrayList of Book objects
        return book;
    }


    public LibraryItemDto checkOutBook(int itemId, boolean status, int dueDateOffSet) {
        Date dueDate = null;
        try {
            //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            PreparedStatement stmt = connection.prepareStatement(UPDATE_ITEM_SQL);
            stmt.setBoolean(1, status);
            //calculate date 7 days from now - format for mysql should be YYYY-MM-FF
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, dueDateOffSet);

            SimpleDateFormat df = (SimpleDateFormat) DateFormat.getInstance();
            df.applyPattern("YYYY-MM-FF");

            //String dueDate = df.format(calendar.getTime());
            dueDate = calendar.getTime();

            if(dueDateOffSet < 0){//reset due date
                dueDate = null;
            }

            stmt.setString(2, df.format(dueDate));

            stmt.setInt(3, itemId);//item id
            //4. Execute query
            stmt.execute();
            //close connection - prevent resource leaks
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new BookDto("","", status, dueDate);
    }


    public void addItem(LibraryItemDto item) {
        BookDto book = (BookDto) item;
        try {
            //2. Get connection
            Connection connection = getConnection(CONNECTION_URL, USERNAME, PWD);
            //3. Create statement
            PreparedStatement stmt = connection.prepareStatement(INSERT_ITEM_SQL);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isStatus());
            stmt.setString(4, book.getThumbURL());

            //4. Execute query
            stmt.execute();
            //close connection - prevent resource leaks
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Get library list - returns a list of library names in the DB
     */
    @Override
    public List<LibraryInfoDto> getLibraryNames() {
        //TODO
        return null;
    }

    /*
    * Get books belonging to a library with the given id
    */
    @Override
    public List<LibraryItemDto> getLibraryItemListByLibId(String libId) {
        //TODO
        return null;
    }
}
