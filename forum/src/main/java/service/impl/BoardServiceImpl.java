package service.impl;

import dao.*;
import dao.impl.*;
import pojo.Board;
import pojo.Subscription;
import pojo.User;
import service.BoardService;
import dao.SubscriptionDao;
import utils.Constants;

import java.sql.SQLException;
import java.util.*;

public class BoardServiceImpl implements BoardService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private BoardDao boardDao = new BoardDaoImpl();
    private BoardApplyDao boardApplyDao = new BoardApplyDaoImpl();
    private BoardBanDao boardBanDao = new BoardBanDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private NoticeDao noticeDao = new NoticeDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();


    /*-----------------------------------------    取消关注版块    --------------------------------------------*/
    @Override
    public void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        subscriptionDao.cancelSubscribeThisBoard(boardId, userId);
    }


    /*--------------------------------    判断用户是否已经关注版块    --------------------------------------------*/
    @Override
    public boolean checkIfSubcribe(Integer boardId, Integer userId) throws Exception {
        // 获取关注列表
        List<Subscription> subscriptionList = subscriptionDao.getOneSubscriptionList(userId);
        for (Subscription subscription : subscriptionList) {
            if (subscription.getSubscribeToBoardId() == (boardId)) {
                return true;
            }
        }
        return false;
    }


    /*-------------------------------------------    订阅版块    ----------------------------------------------*/
    @Override
    public void subscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        subscriptionDao.subscribeThisBoard(boardId, userId);
        User user = userDao.getUserById(userId);
        Board board = boardDao.getBoardById(boardId);
        messageDao.creatMessage("用户：" + user.getName() + " 刚刚关注了您的版块~"
                , board.getHostId(), null, "版块关注通知");
    }


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


    /*-------------------------------------------    新增版块    ----------------------------------------------*/
    @Override
    public void applyNewBoard(Integer userId, String title, String type, String notice) throws Exception {
        boardApplyDao.applyNewBoard(userId, title, type, notice);
    }

    /*--------------------------------------    版块封禁用户发帖    ----------------------------------------------*/
    @Override
    public boolean banUserInThisBoard(Integer boardId, String userName, String reason) throws SQLException {
        // 过滤，找到该用户名是否存在
        Optional<User> matchedUser = userDao.getUserSet().stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst();

        if (matchedUser.isPresent()) {
            User user = matchedUser.get();
            try {
                boardBanDao.banUserInThisBoard(boardId, user.getId(), reason);
                Board board = boardDao.getBoardById(boardId);
                messageDao.creatMessage("您已被禁止在版块《" + board.getTitle()
                        + "》发布帖子/评论，原因为：\n" + reason, user.getId(), board.getHostId(), "违规封禁");
                return true;
            } catch (Exception e) {
                // 处理特定异常，例如数据库操作异常
                System.err.println("封禁用户时出现错误: " + e.getMessage());
                return false;
            }
        }
        return false;
    }


    @Override
    public void publishNewNotice(Integer boardId, String content) throws Exception {
        noticeDao.publishNewNotice(boardId, content);
    }


}
