package com.gc.dao;

import com.gc.dto.GroupDto;
import com.gc.dto.StudentDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * Created by maurice on 6/28/17.
 */
public class HibernateDao implements Dao {

    /*
     * Add student list to DB
    */
    public void addStudents(List<StudentDto> students) {
        //TODO
    }

    /*
    * Get a random list of students who have not already been selected (status = "X"). The number of students
    * selected is based on the listsize parameter submitted in the form
    */
    public List<StudentDto> getRandomStudents(int listSize, String group) {
        return null;
    }

    /*
    * Resets all students in the database to status parameter A (=available), T (=temporary), X (=unavailable)
    */
    public void resetStudentListStatus(String status, String group) {
        //TODO
    }

    /*
    * Get list of all students from DB.
    *
     */
    public List<StudentDto> getStudents(String group) {
        //TODO
        return null;
    }

    public List<GroupDto> getGroups() {
        //TODO
        return null;
    }

    public User findByUserName(String username) {
        //TODO
        return null;
    }

    /*
    * @
     */
    public void test(int value){

    }
}
