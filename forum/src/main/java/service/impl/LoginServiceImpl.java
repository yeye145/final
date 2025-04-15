package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.LoginService;

import java.sql.SQLException;
import java.util.Set;

public class LoginServiceImpl implements LoginService {

    private UserDao userDao = new UserDaoImpl();


    @Override
    public User loginCheck(String account, String password) throws SQLException {

        Set<User> users = userDao.getUserSet();
        // 遍历集合，找到该用户
        for (User user : users) {
            if (user.getPhone().equals(account) || user.getEmail().equals(account)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }


}
