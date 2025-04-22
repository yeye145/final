package dao;

import pojo.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
    List<Subscription> getOneSubscriptionList(Integer userId) throws SQLException;

    void subscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    void subscribeThisAuthor(Integer authorId, Integer userId) throws Exception;

    void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception;
}
