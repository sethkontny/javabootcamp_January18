package com.gc.util;

import com.gc.dao.Dao;
import com.gc.dto.CustomUserDetails;
import com.gc.factory.DaoFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by maurice on 8/20/17.
 * Ref: http://www.baeldung.com/spring-security-authentication-with-a-database
 */
public class CustomDetailsService implements UserDetailsService{

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Dao dao = DaoFactory.getInstance(DaoFactory.JDBC);
        User user = dao.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
