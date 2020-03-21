package com.fmapplication.service;


import com.fmapplication.Main;
import com.fmapplication.entity.User;
import com.fmapplication.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class LoginService {

    /**
     * Open Hibernate session and transaction.
     * Make a DB query to return all users with given login and password.
     * Save result in list of Users.
     * If list is empty, then return false.
     * Else create User object user and assign fields from first result in the list.
     * Set static loggedInUser as user.
     * Return true.
     * @param login
     * @param password
     * @return
     */
    public boolean login(String login, String password) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("FROM User WHERE login=:login and password=:password");
        query.setString("login", login);
        query.setString("password", password);

        List<User> list = query.list();
        trx.commit();
        session.close();

        if (list.isEmpty()) {
            return false;
        }
        User user = list.get(0);
        Main.setLoggedInUser(user);
        System.out.println("Login success: " + user.getName());
        return true;
    }
}
