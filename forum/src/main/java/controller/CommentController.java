package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import pojo.Comment;

import pojo.ResponseResult;
import service.CommentService;
import service.impl.CommentServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


@WebServlet("/comment/*")
public class CommentController extends BaseServlet {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private CommentService commentService = new CommentServiceImpl();


    /*----------------------------------------   获取该帖子的所有评论   ----------------------------------------*/
    public void getAllCommentInThisPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取前端传过来的帖子id
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        System.out.println("CommentController，获取该帖子的所有评论，帖子id：" + postId);

        // 获得结果List集合
        List<Comment> resultCommentList = commentService.getAllCommentInThisPost(postId);

        // 返回结果List集合给前端
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(resultCommentList)
                )
        );

        System.out.println("=获取该帖子下的全部评论成功！\n---------------------------------------------------------");
        resultCommentList.forEach(System.out::println);
    }

    /*------------------------------------------    为这条帖子点个赞    ----------------------------------------*/
    public void likeThisComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer commentId = Integer.parseInt(request.getParameter("commentId"));

        System.out.println("CommentController.likeThisComment，为帖子点赞，评论id：" + commentId);

        commentService.likeThisComment(commentId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("点赞评论成功！")
                )
        );

    }



}
