package service;

import pojo.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {

    /*--------------------------------------    获取我的通知信息    --------------------------------------------*/
    List<Message> getOneMessageList(Integer userId) throws SQLException;

    /*--------------------------------------    查看是否有新的信息    -------------------------------------------*/
    boolean checkIfNewMessage(Integer userId) throws SQLException;

    /*--------------------------------------    确认查收这条信息    ---------------------------------------------*/
    void receiveThisMessage(Integer messageId) throws Exception;
}
