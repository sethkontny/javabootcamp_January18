package com.gc.libterm.dto;

import java.util.Date;

/**
 * Created by maurice on 5/10/17.
 */
public abstract class LibraryItemDto {
    private boolean status;//false = checked in, true = checked out
    private Date dueDate;
    private String thumbURL;

    /*
    * Default constructor
     */
    public LibraryItemDto() {
    }

    /*
    * Parameterized constructor
     */
    public LibraryItemDto(boolean status, Date dueDate) {
        this.status = status;
        this.dueDate = dueDate;
    }

    /*
    * Parameterized constructor
     */
    public LibraryItemDto(boolean status, Date dueDate, String thumbURL) {
        this.status = status;
        this.dueDate = dueDate;
        this.thumbURL = thumbURL;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }
}
