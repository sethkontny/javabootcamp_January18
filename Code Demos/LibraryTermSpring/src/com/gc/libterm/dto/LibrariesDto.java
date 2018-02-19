package com.gc.libterm.dto;

import java.util.Set;

/**
 * Created by maurice on 8/8/17.
 */
public class LibrariesDto {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Set<LibraryItemDto> books;

    public Set<LibraryItemDto> getBooks() {
        return books;
    }

    public void setBooks(Set<LibraryItemDto> books) {
        this.books = books;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LibrariesDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", books=" + books +
                '}';
    }
}
