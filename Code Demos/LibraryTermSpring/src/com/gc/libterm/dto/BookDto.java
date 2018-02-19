package com.gc.libterm.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by maurice on 4/30/17.
 */
public class BookDto extends LibraryItemDto {
    private int bookid;
    @NotEmpty(message = "Please enter a book title.")//server side validation - this field is required
    private String title;
    @NotEmpty(message = "Please enter a book author.")//server side validation - this field is required
    private String author;

    private LibraryInfoDto libraryInfoDto;

    /*
    * Default constructor
     */
    public BookDto() {
    }

    /*
        * Parameterized constructor
         */
    public BookDto(String title, String author, boolean status, Date dueDate) {
        super(status, dueDate);
        this.title = title;
        this.author = author;
    }

    /*
    * Parameterized constructor with default values for status and deuDate
     */
    public BookDto(String author, String title) {
        super(false, new Date());
        this.title = title;
        this.author = author;
    }

    public BookDto(String title, String author, boolean status, Date dueDate, String thumbURL) {
        super(status, dueDate, thumbURL);
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        String statusString = "Checked In";
        String dueDate = "NA";
        if(isStatus()){//if true then book is checked out
            statusString = "Checked Out";
            dueDate = getDueDate().toString();
        }
        return "Title=" + title + '\n' +
                "Author=" + author + '\n' +
                "Status=" + statusString + '\n' +
                "Due Date=" + dueDate + '\n';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public LibraryInfoDto getLibraryInfoDto() {
        return libraryInfoDto;
    }

    public void setLibraryInfoDto(LibraryInfoDto libraryInfoDto) {
        this.libraryInfoDto = libraryInfoDto;
    }
}
