package dao.impl;

import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Subscription;
import dao.SubscriptionDao;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public List<Subscription> getOneSubscriptionList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id" +
                        ", subscribe_to_user_id AS subscribeToUserId" +
                        ", subscribe_to_board_id AS subscribeToBoardId" +
                        ", user_id AS userId FROM `forum`.`subscription` WHERE user_id = ?"
                , Subscription.class, userId);
    }

    @Override
    public void subscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`subscription`" +
                " (`user_id`, `subscribe_to_board_id`) VALUES (?, ?)", userId, boardId);


    }


    @Override
    public void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`subscription`" +
                " WHERE (`user_id` = ? AND `subscribe_to_board_id` = ?)", userId, boardId);
    }

}