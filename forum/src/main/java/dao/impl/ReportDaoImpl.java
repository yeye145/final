package dao.impl;

import dao.ReportDao;
import dao.utils.MyUpdate;

public class ReportDaoImpl implements ReportDao {

    /*-------------------------------------------    举报帖子    ----------------------------------------------*/
    @Override
    public void reportPost(Integer postId, Integer boardId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `board_id`, `post_id`,`judge`, `reason`)" +
                " VALUES (?, ?, ?, ?, ?)", userId, boardId, postId, "host", reason);
    }


    /*-------------------------------------------    举报作者    ----------------------------------------------*/
    @Override
    public void reportUser(Integer reportedThisUserId, Integer userId, String reason) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`report`" +
                " (`user_id`, `reported_this_user_id`, `judge`, `reason`)" +
                " VALUES (?, ?, ?, ?)", userId, reportedThisUserId, "admin", reason);
    }

}
