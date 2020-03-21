package com.fmapplication.dao;

import com.fmapplication.entity.User;
import com.fmapplication.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SigninDao {

    public int save (User user) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    public boolean isExistingLogin(String login) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("FROM User WHERE login=:login");
        query.setString("login", login);

        List<User> list = query.list();
        trx.commit();
        session.close();

        if (list.isEmpty()) {
            return false;
        }
        User user = list.get(0);
        return true;
    }
}
