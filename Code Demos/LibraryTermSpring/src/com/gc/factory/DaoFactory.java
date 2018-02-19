package com.gc.factory;


import com.gc.libterm.dao.Dao;
import com.gc.libterm.dao.HibernateDao;
import com.gc.libterm.dao.JDBCDao;

/**
 * Created by maurice on 5/10/17.
 */
public class DaoFactory {
    public static final int JDBC = 1;//we could also use enums here
    public static final int HIBERNATE = 2;
    public static final int FLAT_FILE = 3;

    /*
    * Return a concrete instance of the requested dao type
     */
    public static Dao getInstance(int daoType) {

        Dao dao = null;//this is bad, change later

        switch (daoType){
            case JDBC:
                dao = new JDBCDao();
                break;
            case HIBERNATE:
                dao = new HibernateDao();
                break;

            case FLAT_FILE:
                dao = new FileDao();
                break;

            default:
                //TODO null for now
                break;
        }
        return dao;
    }
}
