package dao;

import pojo.Collect;

import java.util.List;

public interface CollectDao {
    List<Collect> getCollectWithNullPostInformation(Integer userId) throws Exception;
}
