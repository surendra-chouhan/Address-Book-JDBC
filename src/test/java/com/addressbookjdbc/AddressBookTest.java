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
}
