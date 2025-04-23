package service.impl;

import dao.*;
import dao.impl.*;
import pojo.BoardBan;
import pojo.Comment;

import pojo.Post;
import pojo.User;
import service.CommentService;

import java.sql.SQLException;
import java.util.*;

public class CommentServiceImpl implements CommentService {

    /*--------------------------------------------    私有变量     ------------------------------------------*/
    private CommentDao commentDao = new CommentDaoImpl();
    private BoardBanDao boardBanDao = new BoardBanDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private MessageDao messageDao = new MessageDaoImpl();
    private PostDao postDao = new PostDaoImpl();


    /*-----------------------------------------    在该评论下发表评论    --------------------------------------*/
    @Override
    public boolean creatCommentOnComment(Integer postId, Integer boardId, Integer parentId, Integer userId, String content) throws Exception {

        System.out.println("CommentServiceImpl.creatCommentOnComment，发布评论的跟评，父评论id：" + parentId);

        Set<BoardBan> allBanUserSet = boardBanDao.getAllBanUserSet();

        for (BoardBan boardBan : allBanUserSet) {
            if (boardBan.getBanId() == userId && boardBan.getBoardId().equals(boardId)) {
                System.out.println("--X>发布失败，用户已在该版块被禁止发言");
                return false;
            }
        }
        // 获得评论者user信息
        User user = userDao.getUserById(userId);
        // 获得父评论信息
        Comment comment = commentDao.getCommentById(parentId);
        commentDao.creatCommentOnComment(postId, parentId, user, content);
        messageDao.creatMessage("用户：" + user.getName()
                        + " 给您的评论“" + comment.getContent() + "”进行了跟评，内容如下：\n" + content
                , comment.getUserId(), null, "评论回复");
        return true;
    }


    /*-----------------------------------------    在该帖子下发表评论    --------------------------------------*/
    @Override
    public boolean creatCommentOnPost(Integer postId, Integer boardId, Integer userId, String content) throws Exception {

        System.out.println("CommentServiceImpl.creatCommentOnPost，发布帖子评论，帖子id：" + postId);

        Set<BoardBan> allBanUserSet = boardBanDao.getAllBanUserSet();

        for (BoardBan boardBan : allBanUserSet) {
            if (boardBan.getBanId() == userId && boardBan.getBoardId().equals(boardId)) {
                System.out.println("--X>发布失败，用户已在该版块被禁止发言");
                return false;
            }
        }
        commentDao.creatCommentOnPost(postId, userDao.getUserById(userId), content);
        Post post = postDao.getThisPostById(postId);
        messageDao.creatMessage("用户：" + userDao.getUserById(userId).getName()
                        + " 给您的帖子“" + post.getTitle() + "”发布了一条评论，内容如下：\n" + content
                , post.getAuthorId(), null, "帖子回复");
        return true;
    }


    /*-----------------------------------------    获取帖子下所有评论    --------------------------------------*/
    @Override
    public List<Comment> getAllCommentInThisPost(Integer postId) throws SQLException {
        List<Comment> allCommentInThisPost = commentDao.getAllCommentInThisPost(postId);

        System.out.println("CommentServiceImpl.getAllCommentInThisPost，帖子id：" + postId);
        allCommentInThisPost.forEach(System.out::println);

        Map<Integer, Comment> commentMap = new HashMap<>();
        // 根评论集合
        List<Comment> rootComment = new ArrayList<>();

        for (Comment comment : allCommentInThisPost) {
            // 把评论都添加到map集合中
            commentMap.put(comment.getId(), comment);

            // 如果没有父评论，添加到根评论集合
            if (comment.getParentId() == null) {
                rootComment.add(comment);
            } else {
                // 如果有父评论，获得父评论的Comment，并在父评论的子评论List集合中添加当前正在遍历循环的元素
                Comment parentComment = commentMap.get(comment.getParentId());
                if (parentComment != null) {
                    parentComment.addChildComment(comment);
                }
            }
        }
        return rootComment;
    }


    /*-----------------------------------------    为评论点赞    ---------------------------------------------*/
    @Override
    public boolean likeThisComment(Integer commentId, Integer userId) throws Exception {
        if (userId == null) return false;
        commentDao.plusOneLikeCount(commentId);
        // 获得评论者user信息
        User user = userDao.getUserById(userId);
        // 获得评论信息
        Comment comment = commentDao.getCommentById(commentId);
        messageDao.creatMessage("用户：" + user.getName()
                        + " 给您的评论“" + comment.getContent() + "”点了1个赞"
                , comment.getUserId(), null, "评论回复");
        return true;
    }


}
