package dao;

import pojo.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {

    /*-----------------------------    获取某个用户的关注List集合    -----------------------------------------*/
    List<Subscription> getOneSubscriptionList(Integer userId) throws SQLException;

    /*-----------------------------    用户关注这个版块    --------------------------------------------------*/
    void subscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*-----------------------------    用户关注这个作者    --------------------------------------------------*/
    void subscribeThisAuthor(Integer authorId, Integer userId) throws Exception;

    /*-----------------------------    用户取消关注这个版块    -----------------------------------------------*/
    void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception;

    /*-----------------------------    用户取消关注这个作者    -----------------------------------------------*/
    void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception;
}
