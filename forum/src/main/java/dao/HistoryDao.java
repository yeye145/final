package dao;

import pojo.History;

import java.util.List;

public interface HistoryDao {

    /*----------------------------    浏览帖子时记录到历史记录中  ---------------------------------------*/
    void recordHistory(Integer postId, Integer userId) throws Exception;

    /*----------------------------    获取我的历史记录（帖子内容未填充）  ---------------------------------*/
    List<History> getHistoryWithNullPostInformation(Integer userId) throws Exception;
}
