package service.impl;

import dao.MessageDao;
import dao.UserDao;
import dao.impl.MessageDaoImpl;
import dao.impl.UserDaoImpl;
import pojo.Message;
import pojo.User;
import service.MessageService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MessageServiceImpl implements MessageService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private MessageDao messageDao = new MessageDaoImpl();
    private UserDao userDao = new UserDaoImpl();


    /*--------------------------------------    获取我的通知信息    --------------------------------------------*/
    @Override
    public List<Message> getOneMessageList(Integer userId) throws SQLException {
        List<Message> messageList = messageDao.getOneMessageList(userId);

        Map<Integer, User> userMap = userDao.getUserMap();

        // 获取头像和昵称信息，填充
        for (Message message : messageList) {
            Integer userIdSend = message.getUserIdSend();
            if (userIdSend != null) {
                message.setUserAvatarSend(userMap.get(userIdSend).getAvatar());
                message.setUserNameSend(userMap.get(userIdSend).getName());
            } else {
                message.setUserNameSend("系统");
                message.setUserAvatarSend("/images/avatar/systemAvatar.jpg");
            }
        }

        return messageList;
    }


    /*--------------------------------------    查看是否有新的信息    -------------------------------------------*/
    @Override
    public boolean checkIfNewMessage(Integer userId) throws SQLException {
        List<Message> messageList = messageDao.getOneMessageList(userId);

        // 查看是否有观看次数为0的消息
        for (Message message : messageList) {
            if (message.getViewCount() == 0) {
                return true;
            }
        }

        return false;
    }


    /*--------------------------------------    确认查收这条信息    ---------------------------------------------*/
    @Override
    public void receiveThisMessage(Integer messageId) throws Exception {
        messageDao.receiveThisMessage(messageId);
    }


    /*--------------------------------------    清空所有已读信息    ---------------------------------------------*/
    @Override
    public void deleteAllReceiveMessage(Integer userId) throws Exception {
        messageDao.deleteAllReceiveMessage(userId);
    }



}
