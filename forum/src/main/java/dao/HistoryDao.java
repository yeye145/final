package dao;

import pojo.History;

import java.util.List;

public interface HistoryDao {

    void recordHistory(Integer postId, Integer userId) throws Exception;

    List<History> getHistoryWithNullPostInformation(Integer userId) throws Exception;
}
