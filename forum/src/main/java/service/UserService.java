package service;

import pojo.User;

import java.sql.SQLException;

public interface UserService {
    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    boolean reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    boolean reportPost(Integer postId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    订阅作者    ----------------------------------------------*/
    void subscribeThisUser(Integer authorId, Integer userId) throws Exception;

    /*-----------------------------------------    取消关注用户    --------------------------------------------*/
    void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception;

    /*-------------------------------    判断用户是否已经关注该作者    --------------------------------------------*/
    boolean checkIfSubcribe(Integer authorId, Integer userId) throws Exception;

    String getAvatar(Integer userId) throws SQLException;

    User getInformation(Integer userId) throws SQLException;


}
