package com.gc.libterm.util;

import com.gc.factory.DaoFactory;
import com.gc.libterm.dao.Dao;
import com.gc.libterm.dto.LibraryItemDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurice on 4/30/17.
 */
public class Library {
    private static final int DUE_DATE_OFFSET = 7;
    private ArrayList<LibraryItemDto> bookList;

    /*
    * Default constructor
     */
    public Library() {
        this.bookList = readBooks();
    }


    /*
    * Intializes bookList from file, hardcoded, URL or database etc...
     */
    private ArrayList<LibraryItemDto> readBooks() {

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        ArrayList<LibraryItemDto> tempList = dao.getLibraryItemList();

        return  tempList;
    }

    /*
    * Get arraylist of book objects
     */
    public ArrayList<LibraryItemDto> getBookList() {

        bookList = readBooks();
        return bookList;
    }

    /*
    * Return current list of books in this library as a string including index numbers
     */
    public String getBookListAsString() {
        getBookList();
        StringBuffer buff = new StringBuffer();
        int index = 1;
        for(LibraryItemDto book: this.bookList){
            buff.append(index + ". \n" + book + "\n");
            index++;
        }
        return buff.toString();
    }

    /*
    * Return book objects for specified author name
     */
    public ArrayList<LibraryItemDto> searchByAuthor(String author) {

        //Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);
        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        ArrayList<LibraryItemDto> searchResults = dao.getLibraryItemByAuthor(author);

        return searchResults;
    }

    /*
    * Return book objects for specified title keyword
    */
    public ArrayList<LibraryItemDto> searchByTitleKeyWord(String titleKeyWord) {

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        ArrayList<LibraryItemDto> searchResults = dao.getLibraryItemByTitle(titleKeyWord);
        return searchResults;
    }

    /*
    * Change the status of the selected book to checkedOut and calculate the due date
    * false = checked in, true = checked out
     */
    public LibraryItemDto checkOutBook(int itemId) {

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        LibraryItemDto book = dao.getLibraryItemById(itemId);
        if(book.isStatus() == false) {//only allow checkout for items with a status of false (=checked in)
            book = dao.checkOutBook(itemId, true, DUE_DATE_OFFSET);
        }
        return book;
    }

    /*
    * Change the status of the selected book to checkedIn and reset due date
    * false = checked in, true = checked out
     */
    public LibraryItemDto checkInBook(int itemId) {

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        LibraryItemDto book = dao.getLibraryItemById(itemId);
        if(book.isStatus() == true) {//only allow checkin for items with a status of true (=checked out)
            book = dao.checkOutBook(itemId, false, -1);
        }
        return book;
    }

    /*
    * add book to library book list.
    * This could be a save to file code or save to database code.
     * For now we are using an arraylist to temporarily persist our book data
     */
    public void addBook(LibraryItemDto item) {
        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        dao.addItem(item);
    }

    /*
    * Returns only book belonging to a library with the given Library Id
     */
    public List<LibraryItemDto> getBookListByLibId(String libId) {
        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        List<LibraryItemDto> tempList = dao.getLibraryItemListByLibId(libId);

        return tempList;
    }
}
