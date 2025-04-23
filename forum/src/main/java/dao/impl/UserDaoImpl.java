package dao.impl;

import dao.UserDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.User;

import java.sql.SQLException;
import java.util.Set;

public class UserDaoImpl implements UserDao {


    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    @Override
    public void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `board_id`, `post_id`,`judge`, `reason`)" +
                " VALUES (?, ?, ?, ?)", userId, boardId, postId, "host", reason);
    }


    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    @Override
    public void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `reported_id`, `judge`, `reason`)" +
                " VALUES (?, ?, ?, ?)", userId, reportedThisUserId, "admin", reason);
    }


    @Override
    public User getUserById(Integer userId) throws SQLException {
        return MySearch.searchToOne(
                "SELECT id, email, phone, password" +
                        ", is_admin AS isAdmin" +
                        ", name, grade, avatar" +
                        ", if_receive_like AS ifReceiveLike" +
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


    @Override
    public void updatePhoneInUser(String newPhone, String email) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `phoneNumber` = ? " +
                "WHERE (`email` = ?);", newPhone, email);
    }

    // 获取新的sql.User信息，用于操作
    @Override
    public Set<User> getUserSet() throws SQLException {
        return MySearch.searchToSet(
                "SELECT id, email, phone, password" +
                        ", is_admin AS isAdmin" +
                        ", name, grade, avatar" +
                        ", if_receive_like AS ifReceiveLike" +
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

    @Override
    public void insertUser(String phone, String email, String password) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`User` (`phone`, `password`," +
                " `is_admin`, `email`, `name`) VALUES (?, ?, 0, ?, ?);", phone, password, email, "用户" + phone);
    }

    @Override
    public void updatePassword(String password, Integer id) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `password` = ? WHERE (`id` = ?);"
                , password, id);
    }


    @Override
    public void updateAvatar(Integer userId, String fileName) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `avatar` = ? WHERE (`id` = ?);"
                , "/images/avatar/" + fileName, userId);
    }

    @Override
    public void updateName(Integer userId, String newName) throws Exception {
        MyUpdate.update("UPDATE `forum`.`User` SET `name` = ? WHERE (`id` = ?);"
                , newName, userId);
    }
}


    