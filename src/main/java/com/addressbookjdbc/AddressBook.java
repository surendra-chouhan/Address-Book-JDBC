package com.addressbookjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddressBook {
    private List<AddressBook> addressBookList;

    private Connection getConnection() throws SQLException {
        String jdbcurl = "jdbc:mysql://localhost:3306/addressbookservice?useSSL=false";
        String username = "root";
        String password = "surendra123";
        Connection con;

        System.out.println("Connecting to database : " + jdbcurl);
        con = DriverManager.getConnection(jdbcurl, username, password);
        System.out.println("Connection Successful : " + con);
        return con;
    }

    public List<AddressBookData> readData() throws SQLException {
       String sql_query = "Select * from addressbooktable;";
       List<AddressBookData> addressBookList = new ArrayList<>();
       Connection connection = this.getConnection();

       try{
           connection.setAutoCommit(false);
           PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               AddressBookData addressBook = new AddressBookData(resultSet.getInt(1),resultSet.getString(2),
                                            resultSet.getString(3),resultSet.getString(4), resultSet.getString(5),
                                            resultSet.getString(6),resultSet.getInt(7),resultSet.getString(8),
                                            resultSet.getString(9), resultSet.getString(10));
               addressBookList.add(addressBook);
               connection.commit();
           }
           System.out.println(addressBookList.toString());
       }
       catch (SQLException throwables){
           throwables.printStackTrace();
           connection.rollback();
       }
       return addressBookList;
    }

    public void updateData(String state, int id) throws SQLException {
        String query = "Update addressbooktable set state = ? where id = ?;";
        Connection connection = this.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, state);
            preparedStatement.setInt(2,3);
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public void updateContactDetails (String lastName, String address, String city, String state,int zip, String phoneNumber, String email, String firstName) throws SQLException {
        Connection connection = this.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Update addressbooktable set lastName = ?, address = ?, city = ?, state = ?, zip = ?, phoneNumber = ?, email = ? where firstName = ?;");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setInt(5, zip);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, firstName);

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public List<AddressBookData> return_Values_between_Particular_DateRange(String date) throws SQLException{
        List<AddressBookData> addressBookList = new ArrayList<>();
        Connection connection = this.getConnection();

        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from addressbooktable where entry_date >= ?;");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                AddressBookData addressBook = new AddressBookData(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(7),resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10));
                addressBookList.add(addressBook);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }
}
