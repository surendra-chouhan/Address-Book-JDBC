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
}
