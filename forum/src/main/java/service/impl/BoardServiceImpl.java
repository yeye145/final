package service.impl;

import dao.BoardDao;
import dao.impl.BoardDaoImpl;
import pojo.Board;
import pojo.Subscription;
import service.BoardService;
import service.SubscriptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoardServiceImpl implements BoardService {


    private BoardDao boardDao = new BoardDaoImpl();
    private SubscriptionDao subsriptionDao = new SubscriptionDaoImpl();

    @Override
    public List<Board> getMyBoard(Integer useId) throws SQLException {
        return boardDao.getOneBoardList(useId);
    }

    @Override
    public List<Board> getAllBoardPrioritizeUserLike(Integer useId) throws SQLException {

        // 获取所有版块的Set集合，因为推送是随机的，用Set来模拟随机
        Set<Board> originBoardSet = boardDao.getAllBoardSet();

        // 获取所有用户喜欢的版块List集合
        List<Subscription> userSubscriptionList = subsriptionDao.getOneSubscriptionList(useId);

        // 定义本方法的返回结果集合
        List<Board> resultBoardList = new ArrayList<Board>();

        for (Subscription userLike : userSubscriptionList) {

            for (Board board : originBoardSet) {

                // 如果与用户关注的版块id相同
                if (Objects.equals(board.getId(), userLike.getSubscribeToBoardId())){
                    resultBoardList.add(board);     // 增加到结果集合
                    break;
                }
            }
        }

        resultBoardList.addAll(originBoardSet);

        return resultBoardList;
    }
}
