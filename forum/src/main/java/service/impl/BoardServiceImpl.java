package service.impl;

import dao.*;
import dao.impl.*;
import pojo.*;
import service.BoardService;
import dao.SubscriptionDao;
import utils.Constants;

import java.sql.SQLException;
import java.util.*;

public class BoardServiceImpl implements BoardService {

    /*------------------------------    私有变量    -------------------------------------------------*/
    private BoardDao boardDao = new BoardDaoImpl();
    private BoardApplyDao boardApplyDao = new BoardApplyDaoImpl();
    private BoardBanDao boardBanDao = new BoardBanDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private NoticeDao noticeDao = new NoticeDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();
    private LogDao logDao = new LogDaoImpl();


    /*------------------------------    获取该版块下的历史公告    ---------------------------------------*/
    @Override
    public List<Notice> getAllNoticeInThisBoard(Integer boardId) throws SQLException {
        return noticeDao.getAllNoticeInThisBoard(boardId);
    }


    /*------------------------------    删除所有已处理的版块申请    -------------------------------------*/
    @Override
    public void deleteAllDealApply() throws Exception {
        // 进行数据库中的相关删除操作
        boardApplyDao.deleteAllDealApply();
        // 记录到日志中
        logDao.recordThisActionInLog(Constants.ADMIN_ID, Constants.ADMIN_NAME
                , Constants.ACTION_DELETE_ALL_DEAL_BOARD_APPLY);
    }


    /*------------------------------    查看是否有新的版块申请    ---------------------------------------*/
    @Override
    public boolean checkIfNewBoardApply() throws Exception {
        // 获取版块申请信息的List集合
        List<BoardApply> applyList = boardApplyDao.getAllApplyNewBoard();

        // 查看是否有未处理的申请
        for (BoardApply apply : applyList) {
            if (!apply.getIfDeal()) {
                // 如果有没处理的，直接返回true
                return true;
            }
        }

        return false;
    }


    /*------------------------------    拒绝创建版块的申请    ------------------------------------------*/
    @Override
    public void refuseApplyNewBoard(Integer applyId) throws Exception {
        // 把这个申请标记为已读
        boardApplyDao.hadReadThisApply(applyId);
        // 获得相关申请信息
        BoardApply apply = boardApplyDao.getBoardApplyById(applyId);
        // 通知用户
        messageDao.creatMessage("您申请创建版块：" + apply.getTitle() + " 已被管理员拒绝！"
                , apply.getHostId(), null, "版块申请创建结果");
        // 记录到日志中
        logDao.recordThisActionInLog(Constants.ADMIN_ID, Constants.ADMIN_NAME
                , String.format(Constants.ACTION_REFUSE_BOARD_APPLY, apply.getTitle()));
    }


    /*------------------------------    同意创建版块的申请    ------------------------------------------*/
    @Override
    public void agreeApplyNewBoard(Integer applyId) throws Exception {
        // 把这个申请标记为已读
        boardApplyDao.hadReadThisApply(applyId);
        BoardApply apply = boardApplyDao.getBoardApplyById(applyId);

        // 填充用户信息
        Map<Integer, User> userMap = userDao.getUserMap();
        User user = userMap.get(apply.getHostId());
        apply.setGrade(user.getGrade());
        apply.setHostAvatar(user.getAvatar());
        apply.setHostName(user.getName());

        // 创建版块
        boardDao.creatNewBoard(apply);
        // 通知用户
        messageDao.creatMessage("您申请创建版块：" + apply.getTitle() + " 成功！快去查看属于你的版块吧"
                , apply.getHostId(), null, "版块申请创建结果");
        // 记录到日志中
        logDao.recordThisActionInLog(Constants.ADMIN_ID, Constants.ADMIN_NAME
                , String.format(Constants.ACTION_AGREE_BOARD_APPLY, apply.getTitle()));
    }


    /*------------------------------    获取创建版块的申请    ------------------------------------------*/
    @Override
    public List<BoardApply> getAllApplyNewBoard() throws Exception {
        List<BoardApply> applyList = boardApplyDao.getAllApplyNewBoard();
        Map<Integer, User> userMap = userDao.getUserMap();
        for (BoardApply apply : applyList) {
            User user = userMap.get(apply.getHostId());
            apply.setGrade(user.getGrade());
            apply.setHostAvatar(user.getAvatar());
            apply.setHostName(user.getName());
        }
        return applyList;
    }


    /*------------------------------    获得所有版块，通过id排序    ------------------------------------*/
    @Override
    public List<Board> getAllBoardOrderById() throws Exception {
        return boardDao.getAllBoardOrderById();
    }


    /*------------------------------    取消关注版块    ----------------------------------------------*/
    @Override
    public void cancelSubscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        // 执行数据库相关操作
        subscriptionDao.cancelSubscribeThisBoard(boardId, userId);

        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_CANCEL_SUBSCRIBE_BOARD
                        , boardDao.getBoardById(boardId).getTitle()));
    }


    /*------------------------------    判断用户是否已经关注版块    -------------------------------------*/
    @Override
    public boolean checkIfSubcribe(Integer boardId, Integer userId) throws Exception {
        // 获取关注列表
        List<Subscription> subscriptionList = subscriptionDao.getOneSubscriptionList(userId);
        for (Subscription subscription : subscriptionList) {
            if (Objects.equals(subscription.getSubscribeToBoardId(), boardId)) {
                return true;
            }
        }
        return false;
    }


    /*------------------------------    订阅版块    --------------------------------------------------*/
    @Override
    public void subscribeThisBoard(Integer boardId, Integer userId) throws Exception {
        subscriptionDao.subscribeThisBoard(boardId, userId);
        User user = userDao.getUserById(userId);
        Board board = boardDao.getBoardById(boardId);
        messageDao.creatMessage("用户：" + user.getName() + " 刚刚关注了您的版块~"
                , board.getHostId(), null, "版块关注通知");
    }


    /*------------------------------    获取我的版块    ----------------------------------------------*/
    @Override
    public List<Board> getMyBoard(Integer userId) throws SQLException {
        return boardDao.getOneBoardList(userId);
    }


    /*------------------------------    获取热门版块    ----------------------------------------------*/
    @Override
    public List<Board> getHotBoard() throws Exception {
        return boardDao.getAllBoardPrioritizeBy(Constants.HOT, Constants.DESCENDING_ORDER);
    }


    /*------------------------------    获取最新版块    -----------------------------------------------*/
    @Override
    public List<Board> getNewBoard() throws Exception {
        return boardDao.getAllBoardPrioritizeBy(Constants.NEW, Constants.DESCENDING_ORDER);
    }


    /*------------------------------    获取推荐版块（默认）    -----------------------------------------*/
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


    /*------------------------------    申请新的版块    -----------------------------------------------*/
    @Override
    public void applyNewBoard(Integer userId, String title, String type, String notice) throws Exception {
        // 执行数据库相关操作
        boardApplyDao.applyNewBoard(userId, title, type, notice);
        // 记录到日志
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_APPLY_NEW_BOARD, title));

    }


    /*------------------------------    版块封禁用户发帖    --------------------------------------------*/
    @Override
    public boolean banUserInThisBoard(Integer boardId, String userName, String reason) throws Exception {
        // 过滤，找到该用户名是否存在
        Optional<User> matchedUser = userDao.getUserSet().stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst();

        // 如果该用户存在
        if (matchedUser.isPresent()) {
            User user = matchedUser.get();

            // 执行相关数据库操作
            boardBanDao.banUserInThisBoard(boardId, user.getId(), reason);
            Board board = boardDao.getBoardById(boardId);

            // 给该用户发送信息通知
            messageDao.creatMessage("您已被禁止在版块《" + board.getTitle()
                    + "》发布帖子/评论，原因为：\n" + reason, user.getId(), board.getHostId(), "违规封禁");
            // 记录到日志中
            logDao.recordThisActionInLog(board.getHostId(), board.getHostName()
                    , String.format(Constants.ACTION_BAN_USER_IN_BOARD
                            , board.getTitle(), user.getId(), userName));
            return true;
        }
        return false;
    }


    /*------------------------------    发布新的版块公告    --------------------------------------------*/
    @Override
    public void publishNewNotice(Integer boardId, String content) throws Exception {
        // 执行相关数据库操作
        noticeDao.publishNewNotice(boardId, content);
        Board board = boardDao.getBoardById(boardId);
        // 记录到日志中
        logDao.recordThisActionInLog(board.getHostId(), board.getHostName()
                , String.format(Constants.ACTION_PUBLISH_NOTICE, board.getTitle(), content));
    }


}
