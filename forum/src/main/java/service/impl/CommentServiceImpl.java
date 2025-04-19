package service.impl;

import dao.CommentDao;
import dao.impl.CommentDaoImpl;
import pojo.Comment;

import service.CommentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {

    /*--------------------------------------------    私有变量     ------------------------------------------*/
    private CommentDao commentDao = new CommentDaoImpl();


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

}
