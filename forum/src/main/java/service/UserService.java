package service;

import pojo.User;

import java.sql.SQLException;

public interface UserService {
    String getAvatar(Integer userId) throws SQLException;

    User getInformation(Integer userId) throws SQLException;


}
