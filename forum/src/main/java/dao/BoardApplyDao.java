package dao;

public interface BoardApplyDao {
    void applyNewBoard(Integer hostId, String title, String type, String notice) throws Exception;
}
