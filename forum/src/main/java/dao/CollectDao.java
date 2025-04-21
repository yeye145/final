package dao;

import pojo.Collect;

import java.util.List;

public interface CollectDao {
    List<Collect> getCollectWithNullPostInformation(Integer userId) throws Exception;

    void collectThisPost(Integer postId, Integer userId, String remark) throws Exception;
}
