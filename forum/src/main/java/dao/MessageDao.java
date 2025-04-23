package dao;

public interface MessageDao {
    void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception;
}
