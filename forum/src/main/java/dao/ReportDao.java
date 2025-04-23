package dao;

public interface ReportDao {
    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception;

    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception;
}
