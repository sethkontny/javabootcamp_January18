package com.gc.util;

import com.gc.dao.Dao;
import com.gc.dto.CustomUserDetails;
import com.gc.dto.GroupDto;
import com.gc.dto.StudentDto;
import com.gc.factory.DaoFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by maurice on 6/27/17.
 */
public class AppUtil {

    /*
    * Convert comma separated text of student names and return list of student objects.
     */
    public static List<StudentDto> parseStudentList(String studentList) {
        String[] studentNames = studentList.split(",");
        List<StudentDto> students =  new ArrayList<StudentDto>();

        for(String name: studentNames){
            String[] studentName = name.trim().split(" ");//split at space to get first name and last name
            students.add(new StudentDto(studentName[0], studentName[1]));
        }
        return students;
    }

    /*
    * Convert comma separated text of student names and return list of student objects
    * and apply group value.
    */
    public static List<StudentDto> parseStudentList(String studentList, String group) {
        String[] studentNames = studentList.split(",");
        List<StudentDto> students =  new ArrayList<StudentDto>();

        for(String name: studentNames){
            String[] studentName = name.trim().split("\\s+");//split at spaces to get first name and last name
            students.add(new StudentDto(studentName[0], studentName[1], "A", group));
        }
        return students;
    }

    /*
    * Determine if user roles is allow to access this group
     */
    public static boolean isAuthorized(CustomUserDetails userDetails, String group) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        //System.out.println("TESTNG#####:" + userDetails.getAuthorities() + "," + group);

        //TODO this is dumb, find a better way later
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_" + group.toUpperCase())){
                return true;
            }
        }
            return false;
    }
}
