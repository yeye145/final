package service.impl;

import dao.utils.MySearch;
import pojo.Subscription;
import service.SubscriptionDao;

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
}
