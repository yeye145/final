package service.impl;

import dao.BoardDao;
import dao.PostDao;
import dao.impl.BoardDaoImpl;
import dao.impl.PostDaoImpl;

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


    /*--------------------------------------------    获取帖子    --------------------------------------------*/
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

}
