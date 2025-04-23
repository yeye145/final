package service.impl;

import dao.impl.PostDaoImpl;
import dao.impl.ReportDaoImpl;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import pojo.Post;
import pojo.Subscription;
import pojo.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();
    private SubscriptionDaoImpl subscriptionDao = new SubscriptionDaoImpl();
    private PostDaoImpl postDao = new PostDaoImpl();
    private ReportDaoImpl reportDao = new ReportDaoImpl();


    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    @Override
    public boolean reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception {

        User user = userDao.getUserById(reportedThisUserId);

        // 若找不到该用户
        if (user == null) {
            return false;
        }

        reportDao.reportUser(reportedThisUserId, userId, reason);

        return true;
    }


    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    @Override
    public boolean reportPost(Integer postId, Integer userId, String reason) throws Exception {

        Post thePostWhichBeReported = postDao.getThisPostById(postId);

        // 若找不到这条帖子，返回举报失败
        if (thePostWhichBeReported == null) {
            return false;
        }

        reportDao.reportPost(postId, thePostWhichBeReported.getBoardId(), userId, reason);
        return true;
    }


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
