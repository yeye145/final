package dao;

public interface HistoryDao {
    void recordHistory(Integer postId, Integer userId) throws Exception;
}
