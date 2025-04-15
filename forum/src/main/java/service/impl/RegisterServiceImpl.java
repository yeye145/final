package service.impl;


import dao.UserDao;

import dao.impl.UserDaoImpl;
import pojo.User;
import service.RegisterService;

import java.util.Set;

public class RegisterServiceImpl implements RegisterService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public Boolean register(String phone, String email, String password) throws Exception {

        Set<User> userSet = userDao.getUserSet();

        for (User user : userSet) {
            if (user.getPhone().equals(phone) || user.getEmail().equals(email)) {
                return false;
            }
        }
        userDao.insertUser(phone, email, password);

        return true;

    }

}
