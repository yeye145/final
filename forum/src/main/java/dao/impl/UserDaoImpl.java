package dao.impl;

import dao.UserDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    /*-----------------------------    管理员封禁某个用户（通过用户ID）    -----------------------------------*/
    @Override
    public void banUser(Integer userId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET if_ban_login = 1 " +
                "WHERE (`id` = ?);", userId);
    }


    /*-----------------------------    用户收到一个点赞（通过用户ID）    --------------------------------------*/
    @Override
    public void receiveOneLike(Integer userId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET receive_like_count = receive_like_count + 1 " +
                "WHERE (`id` = ?);", userId);
    }


    /*-----------------------------    用户失去一个粉丝（通过用户ID）    --------------------------------------*/
    @Override
    public void lossOneSubscription(Integer userId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET fans_count = fans_count - 1 " +
                "WHERE (`id` = ?);", userId);
    }


    /*-----------------------------    获取所有用户的Map集合（键为用户ID）    --------------------------------*/
    @Override
    public Map<Integer, User> getUserMap() throws SQLException {
        return MySearch.searchToMap("SELECT id, email, phone, password" +
                ", is_admin AS isAdmin" +
                ", name, grade, avatar" +
                ", if_ban_login AS ifBanLogin" +
                ", receive_like_count AS receiveLikeCount" +
                ", fans_count AS fansCount" +
                ", post_count AS postCount" +
                ", receive_read_count AS receiveReadCount" +
                ", my_subscribe_count AS mySubscribeCount" +
                ", my_collect_count AS myCollectCount" +
                ", my_board_count AS myBoardCount" +
                " FROM `forum`.`User`", User.class, "id");
    }


    /*-----------------------------    通过用户ID查询单个用户    --------------------------------------------*/
    @Override
    public User getUserById(Integer userId) throws SQLException {
        return MySearch.searchToOne(
                "SELECT id, email, phone, password" +
                        ", is_admin AS isAdmin" +
                        ", name, grade, avatar" +
                        ", if_ban_login AS ifBanLogin" +
                        ", receive_like_count AS receiveLikeCount" +
                        ", fans_count AS fansCount" +
                        ", post_count AS postCount" +
                        ", receive_read_count AS receiveReadCount" +
                        ", my_subscribe_count AS mySubscribeCount" +
                        ", my_collect_count AS myCollectCount" +
                        ", my_board_count AS myBoardCount" +
                        " FROM `forum`.`User` WHERE `id` = ?", User.class, userId
        );
    }


    /*-----------------------------    获取所有用户的Set集合    --------------------------------------------*/
    @Override
    public Set<User> getUserSet() throws SQLException {
        return MySearch.searchToSet(
                "SELECT id, email, phone, password" +
                        ", is_admin AS isAdmin" +
                        ", name, grade, avatar" +
                        ", if_ban_login AS ifBanLogin" +
                        ", receive_like_count AS receiveLikeCount" +
                        ", fans_count AS fansCount" +
                        ", post_count AS postCount" +
                        ", receive_read_count AS receiveReadCount" +
                        ", my_subscribe_count AS mySubscribeCount" +
                        ", my_collect_count AS myCollectCount" +
                        ", my_board_count AS myBoardCount" +
                        " FROM `forum`.`User`", User.class
        );
    }


    /*-----------------------------    创建新用户（通过手机号、邮箱和密码）    --------------------------------*/
    @Override
    public void insertUser(String phone, String email, String password) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`User` (`phone`, `password`," +
                " `is_admin`, `email`, `name`) VALUES (?, ?, 0, ?, ?);", phone, password, email, "用户" + phone);
    }


    /*-----------------------------    修改用户密码（通过用户ID和新密码）    --------------------------------*/
    @Override
    public void updatePassword(String password, Integer id) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `password` = ? WHERE (`id` = ?);", password, id);
    }


    /*-----------------------------    更新用户头像（通过用户ID和文件名）    --------------------------------*/
    @Override
    public void updateAvatar(Integer userId, String fileName) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `avatar` = ? WHERE (`id` = ?);",
                "/images/avatar/" + fileName, userId);
    }


    /*-----------------------------    修改用户昵称（通过用户ID和新名称）    --------------------------------*/
    @Override
    public void updateName(Integer userId, String newName) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `name` = ? WHERE (`id` = ?);", newName, userId);
    }
}