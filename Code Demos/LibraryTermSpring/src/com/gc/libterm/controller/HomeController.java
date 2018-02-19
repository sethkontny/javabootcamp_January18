package com.gc.libterm.controller;

import com.gc.factory.DaoFactory;
import com.gc.libterm.dao.Dao;
import com.gc.libterm.dto.BookDto;
import com.gc.libterm.dto.LibrariesDto;
import com.gc.libterm.dto.LibraryInfoDto;
import com.gc.libterm.dto.LibraryItemDto;
import com.gc.libterm.util.Library;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurice on 5/14/17.
 */
@Controller
@SessionAttributes("librarylist")
public class HomeController {

    @RequestMapping("/")
    public ModelAndView helloWorld(Model model) {

        return new ModelAndView("index", "message", "Hello World!");
    }

    @RequestMapping("demo")
    public ModelAndView getLibrariesInfo(){

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        List<LibraryInfoDto> libInfo = dao.getLibraryNames();

        for(LibraryInfoDto librariesInfoDto: libInfo){
            System.out.println(librariesInfoDto);
        }

        return new ModelAndView("index", "librarylist", libInfo);
    }

    /*
    * Show home page
     */
    @RequestMapping("/home")
    public ModelAndView home(Model model){

        Dao dao = DaoFactory.getInstance(DaoFactory.HIBERNATE);
        List<LibraryInfoDto> libInfo = dao.getLibraryNames();

        model.addAttribute("librarylist", libInfo);

        return new ModelAndView("home", "command", new BookDto());
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("mode") String mode, @RequestParam("searchtxt") String searchtxt, @RequestParam("library") String libId, Model model){
        ArrayList<LibraryItemDto> results = null;
        Library lib = new Library();
        switch (mode){
            case "listbooks"://get list of books
                //results = lib.getBookList();
                results = (ArrayList<LibraryItemDto>) lib.getBookListByLibId(libId);
                break;

            case "authorsearch"://get list of books by author
                results = lib.searchByAuthor(searchtxt);
                break;

            case "titlesearch"://get list of title keyword
                results = lib.searchByTitleKeyWord(searchtxt);
                break;

            default:
                break;
        }
        model.addAttribute("command", new BookDto());//binding for addbook form
        return new ModelAndView("home", "results", results);
    }

    /*
    * Add a new book to DB. Using server side validation.
    * Ref: http://www.codejava.net/frameworks/spring/spring-mvc-form-validation-example-with-bean-validation-api
     */
    @RequestMapping("/addbook")
    public ModelAndView addBook(@Valid @ModelAttribute("command") BookDto book, BindingResult result, @RequestParam("libraryadd") String libId, Model model){
        if (result.hasErrors()) {
            return new ModelAndView("home", "message", "helloworld");
        }

        int id = Integer.parseInt(libId);
        book.setLibraryInfoDto(new LibraryInfoDto(id));

        Library lib = new Library();
        lib.addBook(book);
        return new ModelAndView("home", "message", "helloworld");
    }

    /*
    * Modify library item status - return updated book object in json format
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String checkOut(@RequestParam("status") boolean status, @RequestParam("id") int id){

        Library lib = new Library();

        LibraryItemDto libItem;

        if(status){//set status to checked out = true
            libItem = lib.checkOutBook(id);
        }else{//set status to checked in = false
            libItem = lib.checkInBook(id);
        }

        //convert to json string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(libItem);

        return jsonOutput;
    }
}
