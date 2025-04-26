package dao;

import pojo.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {


    /*--------------------------------------    确认查收这条信息    ---------------------------------------------*/
    void receiveThisMessage(Integer messageId) throws Exception;

    /*--------------------------------------    创建一条新的信息    ---------------------------------------------*/
    void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception;

    /*--------------------------------------    获取一个人的所有消息    ------------------------------------------*/
    List<Message> getOneMessageList(Integer userId) throws SQLException;

    /*--------------------------------------    清空所有已读信息    ---------------------------------------------*/
    void deleteAllReceiveMessage(Integer userId) throws Exception;
}
