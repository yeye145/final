package dao;

public interface BoardBanDao {
    void banUserInThisBoard(Integer boardId, Integer userId, String reason) throws Exception;
}
