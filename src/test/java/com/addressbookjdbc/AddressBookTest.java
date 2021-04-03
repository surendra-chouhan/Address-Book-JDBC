package com.addressbookjdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddressBookTest {
    AddressBook addressBook;
    @Before
    public void set() {
        addressBook = new AddressBook();
    }

    @Test
    public void givenSelectStatement_shouldReturnList() throws SQLException {
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(10, addressBookDataList.size());
    }

    @Test
    public void givenUpdateStatementforAddressBookTable_shouldReturnTrue() throws SQLException {
        addressBook.updateData("Maharashtra",3);
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(10, addressBookDataList.size());
    }

    @Test
    public void update_contact_details() throws SQLException {
        String firstName = "Adwait";
        String lastName = "Dev";
        String city = "Bay";
        String address = "Tampa";
        String state = "UP";
        int zip = 400091;
        String phoneNumber = "7896541230";
        String email = "adwait@yahoo.com";

        addressBook.updateContactDetails(lastName, address, city, state, zip, phoneNumber, email, firstName);
    }

    @Test
    public void return_Values_between_Particular_DateRange() throws SQLException {
        List<AddressBookData> addressBookDataList = addressBook.return_Values_between_Particular_DateRange("2019-01-01");
        Assert.assertEquals(6, addressBookDataList.size());
    }

    @Test
    public void count_Contacts_in_a_City() throws SQLException {
        String result = addressBook.countByCity("Mumbai");
        Assert.assertEquals("4", result);
    }

    @Test
    public void count_Contacts_in_a_State() throws SQLException {
        String result = addressBook.countByState("Maharashtra");
        Assert.assertEquals("5", result);
    }

    @Test
    public void insert_into_address_book() throws SQLException {
        String firstName = "Tony";
        String lastName = "Stark";
        String address = "Marine Lines";
        String city = "Mumbai";
        String state = "Maharashtra";
        int zip = 400003;
        String phoneNumber = "9966332255";
        String email = "tony@stark.com";
        String entry_date = "2020-11-01";

        addressBook.insertNewContact(firstName, lastName, address, city, state, zip, phoneNumber, email, entry_date);
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(8, addressBookDataList.size());
    }

    @Test
    public void insert_into_addressBook_using_Threads() throws SQLException {
        List<AddressBookData> addressBookDataList = new ArrayList<>();
        addressBookDataList.add(new AddressBookData(9, "Bruce","Wayne", "1007 Mountain Drive", "Gotham", "New Jersey", 53540, "7351857301", "batman@wayne.com", "2019-05-19"));
        addressBookDataList.add(new AddressBookData(10, "Steve","Rogers", "569 Leaman Place", "Brooklyn", "New York", 11201, "6781367092", "steve@avenger.com", "2020-06-21"));
        Instant start = Instant.now();
        addressBook.addEmployeetoPayrollWithThreads(addressBookDataList);
        Instant end = Instant.now();
        System.out.println("Duration of non thread process is : " + Duration.between(start, end));
        List<AddressBookData> bookData = addressBook.readData();
        Assert.assertEquals(10, bookData.size());
    }
}
