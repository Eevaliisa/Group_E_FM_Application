package com.fmapplication.service;

import com.fmapplication.dao.SigninDao;
import com.fmapplication.entity.User;

public class SigninService {

    /**
     * Open Hibernate session and transaction.
     * Save user to DB. Return generated id.
     *
     * @param user
     * @return
     */

    private SigninDao signinDao = new SigninDao();
    public int save (User user) {
        return signinDao.save(user);
    }

    /**
     * Open Hibernate session and transaction.
     * Query DB to see if login already exists.
     *
     */
    public boolean isExistingLogin(String login) {
        return signinDao.isExistingLogin(login);
    }
}
