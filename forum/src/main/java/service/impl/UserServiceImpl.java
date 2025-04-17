package service.impl;

import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String getAvatar(Integer userId) throws SQLException {

        // 用stream流优化代码？算吧
        User targetUser = userDao
                .getUserSet()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .get();

        System.out.println("--UserService，获取头像，用户id：" + targetUser.getId());
        return "/images/avatar/" + targetUser.getAvatar();
    }

    @Override
    public User getInformation(Integer userId) throws SQLException {
        return userDao
                .getUserSet()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .get();
    }
}
