package service.impl;


import dao.UserDao;

import dao.impl.UserDaoImpl;
import pojo.User;
import service.RegisterService;

import java.util.Set;

public class RegisterServiceImpl implements RegisterService {

    private UserDao userDao = new UserDaoImpl();


    @Override
    public String register(String phone, String email, String password) throws Exception {

        Set<User> user = userDao.getUserSet();

        for (User userSet : user) {
            if (userSet.getPhone().equals(phone) || userSet.getEmail().equals(email)) {
                return "{\"error\":\"用户已存在\"}";
            }
        }

        userDao.insertUser(phone, email, password);

        return "{\"success\":true, \"message\":\"注册成功\"}";

    }

}
