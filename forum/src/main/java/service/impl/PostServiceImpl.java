package service.impl;

import dao.BoardDao;
import dao.CollectDao;
import dao.HistoryDao;
import dao.impl.CollectDaoImpl;
import dao.impl.HistoryDaoImpl;
import dao.PostDao;
import dao.impl.BoardDaoImpl;
import dao.impl.PostDaoImpl;

import pojo.Collect;
import pojo.History;
import pojo.Post;
import pojo.Subscription;
import service.PostService;
import service.SubscriptionDao;

import java.util.*;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private PostDao postDao = new PostDaoImpl();
    private BoardDao boardDao = new BoardDaoImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
    private HistoryDao historyDao = new HistoryDaoImpl();
    private CollectDao collectDao = new CollectDaoImpl();


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


    /*-----------------------------------------    获得历史记录    --------------------------------------------*/
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


    /*-----------------------------------------    新增历史记录    --------------------------------------------*/
    @Override
    public boolean collectThisPost(Integer postId, Integer userId, String remark) throws Exception {
        collectDao.collectThisPost(postId, userId, remark);
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
            } catch (Exception e) {
                System.err.println("删除帖子时出现异常: " + e.getMessage());
            }
        });
    }


    /*-----------------------------------------    为帖子点赞    ---------------------------------------------*/
    @Override
    public void likeThisPost(Integer postId) throws Exception {
        postDao.plusOneLikeCount(postId);
    }


}
