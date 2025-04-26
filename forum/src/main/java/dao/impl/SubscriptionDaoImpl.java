package dao.impl;

import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Subscription;
import dao.SubscriptionDao;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    /*-----------------------------    获取某个用户的关注List集合    -----------------------------------------*/
    @Override
    public List<Subscription> getOneSubscriptionList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id" +
                        ", subscribe_to_user_id AS subscribeToUserId" +
                        ", subscribe_to_board_id AS subscribeToBoardId" +
                        ", user_id AS userId FROM `forum`.`subscription` WHERE user_id = ?"
                , Subscription.class, userId);
    }


    /*-----------------------------    用户关注这个版块    --------------------------------------------------*/
    @Override
    public void subscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`subscription`" +
                " (`user_id`, `subscribe_to_board_id`) VALUES (?, ?)", userId, boardId);


    }


    /*-----------------------------    用户关注这个作者    --------------------------------------------------*/
    @Override
    public void subscribeThisAuthor(Integer authorId, Integer userId) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`subscription`" +
                " (`user_id`, `subscribe_to_user_id`) VALUES (?, ?)", userId, authorId);

    }


    /*-----------------------------    用户取消关注这个版块    -----------------------------------------------*/
    @Override
    public void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`subscription`" +
                " WHERE (`user_id` = ? AND `subscribe_to_board_id` = ?)", userId, boardId);
    }


    /*-----------------------------    用户取消关注这个作者    -----------------------------------------------*/
    @Override
    public void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`subscription`" +
                " WHERE (`user_id` = ? AND `subscribe_to_user_id` = ?)", userId, authorId);
    }

}