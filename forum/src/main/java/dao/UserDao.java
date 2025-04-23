package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.Set;

public interface UserDao {

    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception;

    User getUserById(Integer userId) throws SQLException;

    // 更新users表中的电话号码
    void updatePhoneInUser(String newPhone,String email) throws Exception;

    // 获取新的sql.users信息，用于操作
    Set<User> getUserSet() throws SQLException;

    void insertUser(String phone, String email, String password) throws Exception;

    void updatePassword(String password, Integer id) throws Exception;

    void updateAvatar(Integer userId, String fileName) throws Exception;

    void updateName(Integer userId, String newName) throws Exception;
}
