package dao.impl;

import dao.MessageDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageDaoImpl implements MessageDao {


    /*--------------------------------------    确认查收这条信息    ---------------------------------------------*/
    @Override
    public void receiveThisMessage(Integer messageId) throws Exception {
        MyUpdate.update("UPDATE `forum`.`message` SET view_count = 1 WHERE (`id` = ?)", messageId);
    }


    /*--------------------------------------    创建一条新的信息    ---------------------------------------------*/
    @Override
    public void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`message`" +
                " (`content`, `user_id_receive`, `user_id_send`, `type`)" +
                " VALUES (?, ?, ?, ?)", content, userIdReceive, userIdSend, type);
    }


    /*--------------------------------------    获取一个人的所有消息    ------------------------------------------*/
    @Override
    public List<Message> getOneMessageList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id, content, type, time" +
                ", user_id_receive AS userIdReceive" +
                ", user_id_send AS userIdSend" +
                ", view_count AS viewCount " +
                "FROM `forum`.`message` " +
                "WHERE user_id_receive = ? ORDER BY time DESC", Message.class, userId);

    }

    /*--------------------------------------    清空所有已读信息    ---------------------------------------------*/
    @Override
    public void deleteAllReceiveMessage(Integer userId) throws Exception {
        MyUpdate.update("DELETE FROM `forum`.`message`" +
                " WHERE user_id_receive = ? AND view_count = 1", userId);
    }


}
