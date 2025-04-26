package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface UserDao {

    /*-----------------------------    管理员封禁某个用户（通过用户ID）    -----------------------------------*/
    void banUser(Integer userId) throws Exception;

    /*-----------------------------    用户收到一个点赞（通过用户ID）    --------------------------------------*/
    void receiveOneLike(Integer userId) throws Exception;

    /*-----------------------------    用户失去一个粉丝（通过用户ID）    --------------------------------------*/
    void lossOneSubscription(Integer userId) throws Exception;

    /*-----------------------------    获取所有用户的Map集合（键为用户ID）    --------------------------------*/
    Map<Integer, User> getUserMap() throws SQLException;

    /*-----------------------------    通过用户ID查询单个用户    --------------------------------------------*/
    User getUserById(Integer userId) throws SQLException;

    /*-----------------------------    获取所有用户的Set集合    --------------------------------------------*/
    Set<User> getUserSet() throws SQLException;

    /*-----------------------------    创建新用户（通过手机号、邮箱和密码）    --------------------------------*/
    void insertUser(String phone, String email, String password) throws Exception;

    /*-----------------------------    修改用户密码（通过用户ID和新密码）    --------------------------------*/
    void updatePassword(String password, Integer id) throws Exception;

    /*-----------------------------    更新用户头像（通过用户ID和文件名）    --------------------------------*/
    void updateAvatar(Integer userId, String fileName) throws Exception;

    /*-----------------------------    修改用户昵称（通过用户ID和新名称）    --------------------------------*/
    void updateName(Integer userId, String newName) throws Exception;
}