package com.gc.controller;

import com.gc.dao.Dao;
import com.gc.dto.CustomUserDetails;
import com.gc.dto.GroupDto;
import com.gc.dto.StudentDto;
import com.gc.factory.DaoFactory;
import com.gc.util.AppUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurice on 6/27/17.
 */

@Controller
@SessionAttributes("groupslist")//session variable
public class HomeController {

    private List<GroupDto> groupslist;

    @RequestMapping("/")
    public ModelAndView helloWorld(Model model) {
        //initialize groups dropdown
        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);
        List<GroupDto> groups = dao.getGroups();//get groups list
        model.addAttribute("groupslist", groups);//save session scope variable to persist across pages

        return new ModelAndView("index", "message", "Hello World!");
    }


    @RequestMapping("admin")
    public ModelAndView admin(Model model) {
        /*Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);
        List<GroupDto> groups = dao.getGroups();//get groups list
        model.addAttribute("groupslist", groups);//save session scope variable to persist across pages*/
        return new ModelAndView("admin", "message", "Welcome");
    }

    /*
    * Accepts comma separated list of student names and adds them to the data base
     */
    @RequestMapping(value = "addstudents", method = RequestMethod.POST)
    public ModelAndView addStudents(Authentication authentication, @RequestParam("namecsv") String studentList, @RequestParam("group") String group){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);

        if(AppUtil.isAuthorized(userDetails, group)){
            List<StudentDto> students = AppUtil.parseStudentList(studentList.trim(), group);
            dao.addStudents(students);
            return new ModelAndView("admin", "message", "Students Added to DB");
        }
        return new ModelAndView("admin", "message", "Access denied for " + userDetails.getUsername() + " to perform this operation!");
    }

    /*
    * Get a random list of students who have not already been selected (status = "X"). The number of students
    * selected is based on the listsize parameter submitted in the form
    * Ref: http://www.baeldung.com/get-user-in-spring-security
     */
    @RequestMapping(value = "getrandomstudents", method = RequestMethod.GET)
    public ModelAndView getRandomStudents(Authentication authentication, @RequestParam("numberofstudents") int listSize, @RequestParam("group") String group){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);

        if(AppUtil.isAuthorized(userDetails, group)){
            List<StudentDto> students  = dao.getRandomStudents(listSize, group);
            return new ModelAndView("admin", "studentlist", students);
        }
        return new ModelAndView("admin", "message", "Access denied for " + userDetails.getUsername() + " to perform this operation!");
    }

    /*
    * Resets all students in the database to available status A (=available)
     */
    @RequestMapping(value = "resetstudentlist", method = RequestMethod.GET)
    public ModelAndView resetStudentList(Authentication authentication, @RequestParam("group") String group){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);

        if(AppUtil.isAuthorized(userDetails, group)){
            dao.resetStudentListStatus("A", group);
            return new ModelAndView("admin", "message", "StudentDto List has been reset");
        }
        return new ModelAndView("admin", "message", "Access denied for " + userDetails.getUsername() + " to perform this operation!");
    }

    /*
    * Get list of all students from DB.
     */
    @RequestMapping(value = "getstudentlist", method = RequestMethod.GET)
    public ModelAndView getStudentList(@RequestParam("group") String group){
        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);
        List<StudentDto> students = dao.getStudents(group);

        return new ModelAndView("list", "results", students);
    }

    /*
     * Login Spring security controller Ref:
     * http://www.beingjavaguys.com/2014/05/spring-security-authentication-and.
     * html
     * http://docs.spring.io/spring-security/site/docs/4.1.0.RELEASE/reference/
     * htmlsingle/#what-is-acegi-security
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginForm(@RequestParam(required = false) String authfailed, String logout, String denied, HttpServletRequest request) {
        String message = "Please login";

        if (authfailed != null) {
            message = "Invalid username or password, try again!";
        } else if (logout != null) {
            message = "Logged Out successfully, login again to continue!";
        } else if (denied != null) {
            message = "Access denied for this user!";
        }
        //return new ModelAndView("login_page", "message", message);
        return new ModelAndView("login_page", "message", message);
    }

    /*
     * Login failed
     */
    @RequestMapping("403page")
    public String ge403denied() {
        return "redirect:/login?denied";
    }

}
