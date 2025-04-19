package service.impl;

import dao.BoardDao;
import dao.impl.BoardDaoImpl;
import pojo.Board;
import pojo.Subscription;
import service.BoardService;
import service.SubscriptionDao;
import utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoardServiceImpl implements BoardService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private BoardDao boardDao = new BoardDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();


    /*-------------------------------------------    获取我的版块    ------------------------------------------*/
    @Override
    public List<Board> getMyBoard(Integer userId) throws SQLException {
        return boardDao.getOneBoardList(userId);
    }


    /*--------------------------------------------    获取热门版块    ------------------------------------------*/
    @Override
    public List<Board> getHotBoard() throws Exception {
        return boardDao.getAllBoardPrioritizeBy(Constants.HOT, Constants.DESCENDING_ORDER);
    }


    /*--------------------------------------------    获取最新版块    ------------------------------------------*/
    @Override
    public List<Board> getNewBoard() throws Exception {
        return boardDao.getAllBoardPrioritizeBy(Constants.NEW, Constants.DESCENDING_ORDER);
    }


    /*----------------------------------------    获取推荐版块（默认）    ---------------------------------------*/
    @Override
    public List<Board> getAllBoardPrioritizeUserLike(Integer userId) throws SQLException {

        // 获取所有版块的Set集合，因为推送是随机的，用Set来模拟随机
        Set<Board> originBoardSet = boardDao.getAllBoardSet();
        // 复制一份数据，用于移除重复元素
        Set<Board> originBoardSetForCopy = originBoardSet;

        // 获取所有用户喜欢的版块List集合
        List<Subscription> userSubscriptionList = subscriptionDao.getOneSubscriptionList(userId);

        // 定义本方法的返回结果集合
        List<Board> resultBoardList = new ArrayList<Board>();

        for (Subscription userLike : userSubscriptionList) {

            for (Board board : originBoardSet) {

                // 如果与用户关注的版块id相同
                if (Objects.equals(board.getId(), userLike.getSubscribeToBoardId())) {
                    resultBoardList.add(board);                // 增加到结果集合
                    originBoardSetForCopy.remove(board);       // 移除相同元素
                    break;
                }
            }
        }

        // 把剩余数据拷贝
        resultBoardList.addAll(originBoardSetForCopy);

        return resultBoardList;
    }
}
