package dao.impl;

import dao.MessageDao;
import dao.utils.MySearch;
import dao.utils.MyUpdate;
import pojo.Board;
import pojo.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageDaoImpl implements MessageDao {


    @Override
    public void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`message`" +
                " (`content`, `user_id_receive`, `user_id_send`, `type`)" +
                " VALUES (?, ?, ?, ?)", content, userIdReceive, userIdSend, type);
    }

    @Override
    public List<Message> getOneMessageList(Integer userId) throws SQLException {
        return MySearch.searchToList("SELECT id, content, type, time" +
                ", user_id_receive AS userIdReceive" +
                ", user_id_send AS userIdSend" +
                ", view_count AS viewCount " +
                "FROM `forum`.`message` " +
                "WHERE user_id_receive = ?", Message.class, userId);

    }


}
