package com.example.lab;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.sql.*;


public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sambhav?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "sambhav18";
    private static final String SEARCH_QUERY = "SELECT * FROM BOOK WHERE SUBSTRING(Name, 1,?) = ? OR SUBSTRING(ID, 1,?) = ? OR Name=? OR ID=?";
    private static final String SEARCH_ALL_QUERY = "SELECT * FROM BOOK ";
    private static final String DELETE_QUERY = "DELETE FROM BOOK WHERE Name = ? OR ID = ?";
    private static final String DELETE_ALL_QUERY = "DELETE FROM BOOK";
    private static final String INSERT_QUERY = "INSERT INTO BOOK VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE BOOK SET ID=?,NAME=?,LANGAUGE=?,GENRE=?,AUTHOR=?,COVER=? WHERE ID=? OR NAME=?";
    private ObservableList<ObservableList> data;
    private TableView tableview;

    public void searchRecord(String name) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY)) {
            preparedStatement.setString(1, String.valueOf(name.length()));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, String.valueOf(name.length()));
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, name);


            String result="";
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()==false)
            {
                Alert b = new Alert(Alert.AlertType.CONFIRMATION);
                result="\nNo records found\n";
                b.setHeaderText(null);
                b.setTitle("Failure");
                b.setContentText(result);
                b.show();
            }
            else  {
                do {
                    result += rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " +
                            rs.getString(4) + "  " + rs.getString(5) +" "+rs.getString(6)+ "\n";
                }while (rs.next());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText(null);
                a.setTitle("Success");
                a.setContentText(result);
                a.show();
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void InsertRecord(int bookid, String bookname, String author,String language, String genre,String cover) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, String.valueOf(bookid));
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, author);
            preparedStatement.setString(6, cover);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

    }

    public void DeleteRecord(String bookname) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, bookname);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void UpdateRecord(int bookid, String bookname, String author, String language,String genre,String cover) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, String.valueOf(bookid));
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, author);
            preparedStatement.setString(6, cover);
            preparedStatement.setString(7, String.valueOf(bookid));
            preparedStatement.setString(8, bookname);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

    }

    public void SearchAll() throws SQLException {
        String result="";
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_ALL_QUERY)) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false) {
                result = "No records found";
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText(null);
                a.setTitle("Failure");
                a.setContentText(result);
                a.show();
            }
            else {
                do {
                    result += rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " +
                            rs.getString(4) + "  " + rs.getString(5)+" "+rs.getString(6) +"\n";
                }while (rs.next());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText(null);
                a.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                a.setTitle("Sucess");
                a.setContentText(result);
                a.show();
            }

            }



    }


    public void DeleteAll() throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_QUERY)) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setTitle("Success");
            a.setContentText("All Records Have been deleted Successfully!!");
            a.show();
            }

        }



    public String getRecord(String name,String ID) throws SQLException {
        String result="";
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY)) {
            preparedStatement.setString(1, String.valueOf(name.length()));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, String.valueOf(ID.length()));
            preparedStatement.setString(4, ID);
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, ID);



            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()==false)
            {
                result="No records found";

            }
            else  {
                do {
                    result += rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " +
                            rs.getString(4) + "  " + rs.getString(5)+" "+rs.getString(6) ;
                }while (rs.next());

            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return result;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}