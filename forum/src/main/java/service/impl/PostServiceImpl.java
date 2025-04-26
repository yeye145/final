package service.impl;

import dao.*;
import dao.impl.*;

import pojo.*;
import service.PostService;
import utils.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private PostDao postDao = new PostDaoImpl();
    private BoardDao boardDao = new BoardDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
    private HistoryDao historyDao = new HistoryDaoImpl();
    private CollectDao collectDao = new CollectDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BoardBanDao boardBanDao = new BoardBanDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();
    private LogDao logDao = new LogDaoImpl();


    /*------------------------------    获取该版块的所有帖子，优先显示晚新发布的------------------------------------*/
    @Override
    public List<Post> getAllPostInThisBoardOrderByTimeDesc(Integer boardId) throws Exception {
        return postDao.getAllPostInThisBoardList(boardId);
    }


    /*----------------------------------------    获取我的帖子    --------------------------------------------*/
    @Override
    public List<Post> getMyPost(Integer userId) throws Exception {
        return postDao.getMyPost(userId);
    }


    /*--------------------------------------------    发布帖子    --------------------------------------------*/
    @Override
    public boolean creatPost(Integer boardId, String title, String content, Integer userId) throws Exception {

        Set<BoardBan> allBanUserSet = boardBanDao.getAllBanUserSet();

        // 查看用户是否被禁止发帖
        for (BoardBan boardBan : allBanUserSet) {
            if (Objects.equals(boardBan.getBanId(), userId) && boardBan.getBoardId().equals(boardId)) {
                return false;
            }
        }

        User user = userDao.getUserById(userId);
        // 执行数据库相关操作
        postDao.creatPost(boardId, title, content, user);

        // 记录到日志中
        logDao.recordThisActionInLog(userId, user.getName()
                , String.format(Constants.ACTION_PUBLISH_POST_TITLE, title));
        return true;
    }


    /*-----------------------------------------    取消收藏帖子    --------------------------------------------*/
    @Override
    public void cancelCollectThisPost(Integer postId, Integer userId) throws Exception {
        collectDao.cancelCollectThisPost(postId, userId);
        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_CANCEL_COLLECT, postId));
    }


    /*-----------------------------------------    检查是否已收藏    ------------------------------------------*/
    @Override
    public boolean checkIfCollect(Integer postId, Integer userId) throws Exception {
        // 获取收藏记录列表
        List<Collect> collectList = collectDao.getCollectWithNullPostInformation(userId);
        for (Collect collect : collectList) {
            if (collect.getPostId() == (postId)) {
                return true;
            }
        }
        return false;
    }


    /*-----------------------------------------    获得收藏记录    --------------------------------------------*/
    @Override
    public List<Collect> getPostCollect(Integer userId) throws Exception {

        // 获取收藏记录列表
        List<Collect> collectList = collectDao.getCollectWithNullPostInformation(userId);

        // 提取所有postId用于查询Post对象
        List<Integer> postId = collectList.stream()
                .map(Collect::getPostId)
                .collect(Collectors.toList());

        if (postId.isEmpty()) {
            return collectList; // 若无数据直接返回
        }

        // 获取Post对象的Map（键为postId）
        String inClause = postId.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "(", ")"));

        Map<Integer, Post> postMap = postDao.getPostMapIn(inClause);

        // 填充collect中的Post对象
        for (Collect collect : collectList) {
            Post post = postMap.get(collect.getPostId());
            collect.setPost(post);
        }

        return collectList;
    }


    /*-----------------------------------------    收藏这条帖子    --------------------------------------------*/
    @Override
    public boolean collectThisPost(Integer postId, Integer userId, String remark) throws Exception {
        collectDao.collectThisPost(postId, userId, remark);
        // 帖子被收藏，发出通知
        Post post = postDao.getThisPostById(postId);
        // 向帖子作者发送一条消息
        messageDao.creatMessage("您的帖子《" + post.getTitle() +
                        "》被用户“" + userDao.getUserById(userId).getName() + "”收藏了！", post.getAuthorId()
                , null, "帖子被收藏");

        // 记录到日志中
        logDao.recordThisActionInLog(userId, userDao.getUserById(userId).getName()
                , String.format(Constants.ACTION_COLLECT, postId));
        return true;
    }


    /*-----------------------------------------    新增历史记录    --------------------------------------------*/
    @Override
    public boolean recordPost(Integer postId, Integer userId) throws Exception {
        historyDao.recordHistory(postId, userId);
        return true;
    }


    /*-----------------------------------------    获得历史记录    --------------------------------------------*/
    @Override
    public List<History> getPostHistory(Integer userId) throws Exception {

        // 获取历史记录列表
        List<History> historyList = historyDao.getHistoryWithNullPostInformation(userId);

        // 提取所有postId用于查询Post对象
        List<Integer> postId = historyList.stream()
                .map(History::getPostId)
                .collect(Collectors.toList());

        if (postId.isEmpty()) {
            return historyList; // 若无数据直接返回
        }

        // 获取Post对象的Map（键为postId）
        String inClause = postId.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "(", ")"));

        Map<Integer, Post> postMap = postDao.getPostMapIn(inClause);

        // 填充History中的Post对象
        for (History history : historyList) {
            Post post = postMap.get(history.getPostId());
            history.setPost(post);
        }

        return historyList;
    }


    /*----------------------------------    获取帖子，优先考虑用户关注的    ---------------------------------------*/
    @Override
    public List<Post> getAllPostInThisBoardPrioritizeUserLike(Integer userId, Integer boardId) throws Exception {
        // 获取版块下所有帖子，按时间倒序排列
        List<Post> allPost = postDao.getAllPostInThisBoardList(boardId);

        // 获取用户订阅信息
        List<Subscription> userSubscriptionList = subscriptionDao.getOneSubscriptionList(userId);

        // 分离订阅的用户，获取订阅的用户的id的Set集合
        Set<Integer> subscribedUserIds = userSubscriptionList.stream()
                .map(Subscription::getSubscribeToUserId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 分离订阅的版块，获取订阅的版块的id的Set集合
        Set<Integer> subscribedBoardIds = userSubscriptionList.stream()
                .map(Subscription::getSubscribeToBoardId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 分类处理帖子
        List<Post> priorityUserPost = new ArrayList<>();
        List<Post> priorityBoardPost = new ArrayList<>();
        List<Post> leftPost = new ArrayList<>();

        for (Post post : allPost) {
            if (subscribedUserIds.contains(post.getAuthorId())) {
                priorityUserPost.add(post);
            } else if (subscribedBoardIds.contains(post.getBoardId())) {
                priorityBoardPost.add(post);
            } else {
                leftPost.add(post);
            }
        }

        // 打乱非优先帖子的顺序
        Collections.shuffle(priorityBoardPost);
        Collections.shuffle(leftPost);

        // 合并结果
        List<Post> resultPostList = new ArrayList<>();
        resultPostList.addAll(priorityUserPost);  // 按时间排序
        resultPostList.addAll(priorityBoardPost); // 随机排序
        resultPostList.addAll(leftPost);        // 随机排序

        // 增加一个浏览数
        boardDao.plusOneViewCount(boardId);

        System.out.println("-获取版块id：" + boardId + " 下的所有帖子成功！");

        return resultPostList;


    }


    /*-----------------------------------    获取特定帖子，通过id    --------------------------------------------*/
    @Override
    public Post getThisPostById(Integer postId) throws Exception {
        // 增加一个浏览数，并获取该帖子内容
        postDao.plusOneViewCount(postId);
        return postDao.getThisPostById(postId);
    }


    /*-----------------------------------    删除特定帖子，通过id    --------------------------------------------*/
    @Override
    public void deleteThesePost(List<Integer> postIdAboutToDelete) {
        postIdAboutToDelete.forEach(postId -> {
            try {
                postDao.deleteThisPost(postId);
                // 帖子被删除，发布通知
                Post post = postDao.getThisPostById(postId);
                messageDao.creatMessage("您的帖子《" + post.getTitle() +
                                "》已被删除，若非本人操作，则是因内容违规而被版主或管理员删除", post.getAuthorId()
                        , null, "帖子被删除");
            } catch (Exception e) {
                System.err.println("删除帖子时出现异常: " + e.getMessage());
            }
        });
    }


    /*-----------------------------------------    为帖子点赞    ---------------------------------------------*/
    @Override
    public boolean likeThisPost(Integer postId, Integer userId) throws Exception {
        if (userId == null) return false;
        // 执行点赞功能
        postDao.plusOneLikeCount(postId);
        // 获得评论者user信息
        User user = userDao.getUserById(userId);
        // 获得评论信息
        Post post = postDao.getThisPostById(postId);
        messageDao.creatMessage("用户：" + user.getName()
                        + " 给您的帖子《" + post.getTitle() + "》点了1个赞"
                , post.getAuthorId(), null, "帖子被点赞");
        // 作者点赞数加一
        userDao.receiveOneLike(post.getAuthorId());

        // 记录到日志中
        logDao.recordThisActionInLog(userId, user.getName()
                , String.format(Constants.ACTION_LIKE_POST, postId));
        return true;
    }


}
