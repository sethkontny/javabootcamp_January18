package com.gc.libterm.dao;


import com.gc.libterm.dto.BookDto;
import com.gc.libterm.dto.LibrariesDto;
import com.gc.libterm.dto.LibraryInfoDto;
import com.gc.libterm.dto.LibraryItemDto;
import com.gc.libterm.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by maurice on 5/10/17.
 */
public class HibernateDao implements Dao {

    //private Configuration config;
    private static SessionFactory sessionFactory;

    public HibernateDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public ArrayList<LibraryItemDto> getLibraryItemList() {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Criteria crit = session.createCriteria(BookDto.class);

        ArrayList<LibraryItemDto> list = (ArrayList<LibraryItemDto>)crit.list();

        session.getTransaction().commit();
        //tx.commit();
        //session.close();
        return list;
    }


    /*
    * Return books by the specified author
     */
    public ArrayList<LibraryItemDto> getLibraryItemByAuthor(String author) {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(BookDto.class);
        crit.add(Restrictions.like("author","%".concat(author).concat("%")));

        ArrayList<LibraryItemDto> list = (ArrayList<LibraryItemDto>)crit.list();

        session.getTransaction().commit();
        //tx.commit();
        //session.close();
        return list;
    }

    /*
    * Return books with the specified title keyword
     */
    public ArrayList<LibraryItemDto> getLibraryItemByTitle(String titleKeyWord) {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(BookDto.class);
        crit.add(Restrictions.like("title","%".concat(titleKeyWord).concat("%")));

        ArrayList<LibraryItemDto> list = (ArrayList<LibraryItemDto>)crit.list();

        session.getTransaction().commit();
        //tx.commit();
        //session.close();
        return list;
    }


    public LibraryItemDto getLibraryItemById(int itemId) {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(BookDto.class);
        crit.add(Restrictions.eq("bookid",itemId));

        ArrayList<LibraryItemDto> list = (ArrayList<LibraryItemDto>)crit.list();

        session.getTransaction().commit();
        //tx.commit();
        //session.close();
        return list.get(0);
    }


    public LibraryItemDto checkOutBook(int itemId, boolean status, int dueDateOffSet) {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        //calculate date 7 days from now - format for mysql should be YYYY-MM-FF
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, dueDateOffSet);

        SimpleDateFormat df = (SimpleDateFormat) DateFormat.getInstance();
        df.applyPattern("YYYY-MM-FF");

        //String dueDate = df.format(calendar.getTime());
        Date dueDate = calendar.getTime();

        if(dueDateOffSet < 0){//reset due date
            dueDate = null;
        }

        //update specified book
        BookDto bookDto = session.get(BookDto.class, itemId);
        bookDto.setStatus(status);
        //bookDto.setDueDate(calendar.getTime());
        bookDto.setDueDate(dueDate);

        session.update(bookDto);

        session.getTransaction().commit();
        //tx.commit();
        //session.close();

        return bookDto;
    }


    /*
    * Add book to DB table
     */
    public void addItem(LibraryItemDto item) {
        //Configuration config = new Configuration().configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        session.save(item);

        session.getTransaction().commit();
        //tx.commit();
        //session.close();
    }

    /*
    * Get library list - returns a list of library names in the DB
     */
    @Override
    public List<LibraryInfoDto> getLibraryNames() {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(LibraryInfoDto.class);

        ArrayList<LibraryInfoDto> list = (ArrayList<LibraryInfoDto>)crit.list();

        session.getTransaction().commit();
        return list;
    }

    @Override
    public List<LibraryItemDto> getLibraryItemListByLibId(String libId) {

        int id = Integer.parseInt(libId);

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(LibrariesDto.class);
        crit.add(Restrictions.eq("id",id));

        LibrariesDto list = (LibrariesDto) crit.uniqueResult();//.list();

        session.getTransaction().commit();

        List<LibraryItemDto> result = new ArrayList<>();
        result.addAll(list.getBooks());

        return result;
    }
}
