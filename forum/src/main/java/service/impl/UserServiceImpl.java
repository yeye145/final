package service.impl;

import dao.*;
import dao.impl.*;

import pojo.Log;
import pojo.Subscription;
import pojo.User;
import service.UserService;
import utils.Constants;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserServiceImpl implements UserService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserDao userDao = new UserDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();
    private LogDao logDao = new LogDaoImpl();


    @Override
    public List<Log> adminGetAllLog() throws SQLException {
        return logDao.getAllLog();
    }


    /*--------------------------------------------    更新头像    --------------------------------------------*/
    @Override
    public Boolean uploadAvatar(Part filePart, String savePath, String fileName, Integer userId) throws Exception {

        // 确保目录存在
        java.nio.file.Path path = Paths.get(savePath).getParent();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 获取输入流in
        try (InputStream in = filePart.getInputStream();
             OutputStream out = Files.newOutputStream(Paths.get(savePath))) {

            // 缓冲区
            byte[] buffer = new byte[4096];
            int bytesRead;

            // 当输入流没有结束
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);    //将读取到的数据写入到输出流中
            }
        }
        // 更新数据库
        userDao.updateAvatar(userId, fileName);

        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , Constants.ACTION_UPDATE_AVATAR);
        System.out.println("=头像保存成功，数据库更新完成");

        return true;
    }


    /*-------------------------------------------    更改昵称    ----------------------------------------------*/
    @Override
    public boolean updateName(Integer id, String newName) throws Exception {

        Set<User> userSet = userDao.getUserSet();
        // 判断用户名是否已经存在
        for (User user : userSet) {
            if (user.getName().equals(newName)) {
                System.out.println("--UpdateServiceImpl，昵称已存在");
                return false;
            }
        }
        // 执行数据库相关操作
        userDao.updateName(id, newName);

        // 记录到日志中
        logDao.recordThisActionInLog(id, userDao.getUserById(id).getName()
                , String.format(Constants.ACTION_UPDATE_NAME_TO, newName));
        return true;
    }


    /*-------------------------------------------    订阅作者    ----------------------------------------------*/
    @Override
    public void subscribeThisUser(Integer authorId, Integer userId) throws Exception {
        // 执行相关数据库操作
        subscriptionDao.subscribeThisAuthor(authorId, userId);
        // 获取作者信息
        User user = userDao.getUserById(userId);
        // 发送自己被订阅的信息给作者
        messageDao.creatMessage("用户“" + user.getName() + "”刚刚关注了您！"
                , authorId, null, "用户关注通知");
        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_SUBSCRIBE_AUTHOR, authorId));
    }


    /*-----------------------------------------    取消关注用户    --------------------------------------------*/
    @Override
    public void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception {
        // 执行相关数据库操作
        subscriptionDao.cancelSubscribeThisUser(authorId, userId);
        userDao.lossOneSubscription(authorId);

        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_CANCEL_SUBSCRIBE_AUTHOR, authorId));
    }


    /*-------------------------------    判断用户是否已经关注该作者    --------------------------------------------*/
    @Override
    public boolean checkIfSubcribe(Integer authorId, Integer userId) throws Exception {
        // 获取关注列表
        List<Subscription> subscriptionList = subscriptionDao.getOneSubscriptionList(userId);
        for (Subscription subscription : subscriptionList) {
            if (Objects.equals(subscription.getSubscribeToUserId(), authorId)) {
                return true;
            }
        }
        return false;
    }


    /*-------------------------------------------    获取头像    ----------------------------------------------*/
    @Override
    public String getAvatar(Integer userId) throws SQLException {

        // 用stream流优化代码？算吧
        User targetUser = userDao
                .getUserSet()
                .stream()
                .filter(user -> Objects.equals(user.getId(), userId))
                .findFirst()
                .get();

        System.out.println("--UserService，获取头像，用户id：" + targetUser.getId());
        return targetUser.getAvatar();
    }


    /*-------------------------------------------    获取个人信息    -------------------------------------------*/
    @Override
    public User getInformation(Integer userId) throws SQLException {
        return userDao
                .getUserSet()
                .stream()
                .filter(user -> Objects.equals(user.getId(), userId))
                .findFirst()
                .get();
    }


}
