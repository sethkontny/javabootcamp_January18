package com.gc.dto;

/**
 * Created by maurice on 6/27/17.
 */
public class StudentDto {

    private String firstName;
    private String lastName;
    private String status;
    private String group;

    /*
    * Parameterized constructor
     */
    public StudentDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = "A";
    }

    /*
    * Parameterized constructor - includes status
     */
    public StudentDto(String firstName, String lastName, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    /*
    * Parameterized constructor - includes status and group
     */
    public StudentDto(String firstName, String lastName, String status, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
