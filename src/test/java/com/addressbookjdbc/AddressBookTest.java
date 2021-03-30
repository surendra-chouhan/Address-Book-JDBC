package com.addressbookjdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
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
        Assert.assertEquals(7, addressBookDataList.size());
    }

    @Test
    public void givenUpdateStatementforAddressBookTable_shouldReturnTrue() throws SQLException {
        addressBook.updateData("Maharashtra",3);
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(7, addressBookDataList.size());
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
        Assert.assertEquals(3, addressBookDataList.size());
    }

    @Test
    public void count_Contacts_in_a_City() throws SQLException {
        String result = addressBook.countByCity("Mumbai");
        Assert.assertEquals("3", result);
    }

    @Test
    public void count_Contacts_in_a_State() throws SQLException {
        String result = addressBook.countByState("Maharashtra");
        Assert.assertEquals("4", result);
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
}
