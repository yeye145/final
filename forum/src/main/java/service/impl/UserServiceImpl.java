package service.impl;

import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import pojo.Subscription;
import pojo.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();
    private SubscriptionDaoImpl subscriptionDao = new SubscriptionDaoImpl();

    /*-------------------------------------------    订阅作者    ----------------------------------------------*/
    @Override
    public void subscribeThisUser(Integer authorId, Integer userId) throws Exception {
        subscriptionDao.subscribeThisAuthor(authorId, userId);
    }


    /*-----------------------------------------    取消关注用户    --------------------------------------------*/
    @Override
    public void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception {
        subscriptionDao.cancelSubscribeThisUser(authorId, userId);
    }



    /*-------------------------------    判断用户是否已经关注该作者    --------------------------------------------*/
    @Override
    public boolean checkIfSubcribe(Integer authorId, Integer userId) throws Exception {
        // 获取关注列表
        List<Subscription> subscriptionList = subscriptionDao.getOneSubscriptionList(userId);
        for (Subscription subscription : subscriptionList) {
            if (subscription.getSubscribeToUserId() == (authorId)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String getAvatar(Integer userId) throws SQLException {

        // 用stream流优化代码？算吧
        User targetUser = userDao
                .getUserSet()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .get();

        System.out.println("--UserService，获取头像，用户id：" + targetUser.getId());
        return targetUser.getAvatar();
    }

    @Override
    public User getInformation(Integer userId) throws SQLException {
        return userDao
                .getUserSet()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .get();
    }


}
