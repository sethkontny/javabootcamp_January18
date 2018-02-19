package com.gc.factory;

import com.gc.libterm.dao.Dao;
import com.gc.libterm.dto.LibrariesDto;
import com.gc.libterm.dto.LibraryInfoDto;
import com.gc.libterm.dto.LibraryItemDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurice on 7/20/17.
 */
public class FileDao implements Dao {
    @Override
    public ArrayList<LibraryItemDto> getLibraryItemList() {
        return null;
    }

    @Override
    public ArrayList<LibraryItemDto> getLibraryItemByAuthor(String author) {
        return null;
    }

    @Override
    public ArrayList<LibraryItemDto> getLibraryItemByTitle(String titleKeyWord) {
        return null;
    }

    @Override
    public LibraryItemDto getLibraryItemById(int itemId) {
        return null;
    }

    @Override
    public LibraryItemDto checkOutBook(int itemId, boolean b, int returnDayOffSet) {
        return null;
    }

    @Override
    public void addItem(LibraryItemDto item) {

    }

    /*
    * Get library list - returns a list of library names in the DB
    */
    @Override
    public List<LibraryInfoDto> getLibraryNames() {
        return null;
    }

    /*
    * Get books belonging to a library with the given id
    */
    @Override
    public List<LibraryItemDto> getLibraryItemListByLibId(String libId) {
        return null;
    }

}
