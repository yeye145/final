package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface UserDao {


    void banUser(Integer userId) throws Exception;

    void receiveOneLike(Integer useId) throws Exception;

    void lossOneSubscription(Integer useId) throws Exception;

    Map<Integer, User> getUserMap() throws SQLException;

    User getUserById(Integer userId) throws SQLException;


    // 获取新的sql.users信息，用于操作
    Set<User> getUserSet() throws SQLException;

    void insertUser(String phone, String email, String password) throws Exception;

    void updatePassword(String password, Integer id) throws Exception;

    void updateAvatar(Integer userId, String fileName) throws Exception;

    void updateName(Integer userId, String newName) throws Exception;
}
