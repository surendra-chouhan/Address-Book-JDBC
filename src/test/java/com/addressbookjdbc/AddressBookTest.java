package com.addressbookjdbc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {
    @Test
    public void givenSelectStatement_shouldReturnList() {
        AddressBook addressBook = new AddressBook();
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(7, addressBookDataList.size());
    }

    @Test
    public void givenUpdateStatementforAddressBookTable_shouldReturnTrue() {
        AddressBook addressBook = new AddressBook();
        addressBook.updateData();
        List<AddressBookData> addressBookDataList = addressBook.readData();
        Assert.assertEquals(7, addressBookDataList.size());
    }
}
