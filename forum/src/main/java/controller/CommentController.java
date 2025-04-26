package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.Comment;

import pojo.ResponseResult;
import service.CommentService;
import service.impl.CommentServiceImpl;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/comment/*")
public class CommentController extends BaseServlet {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private CommentService commentService = new CommentServiceImpl();


    /*----------------------------------------   获取该帖子的所有评论   ----------------------------------------*/
    public void getAllCommentInThisPost(HttpServletRequest request, HttpServletResponse response) {

        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取帖子的所有评论时出错");
        }
    }

    /*------------------------------------------    为这条评论点个赞    ----------------------------------------*/
    public void likeThisComment(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer commentId = Integer.parseInt(request.getParameter("commentId"));
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("CommentController.likeThisComment，为评论点赞，评论id：" + commentId);

            if (commentService.likeThisComment(commentId, userId)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("点赞评论成功！")
                        )
                );
            } else {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.error(Constants.RESPONSE_CODE_UNAUTHORIZED, "非法点赞，点赞无效")
                        )
                );
                System.out.println("--X>非法点赞，未登录");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "为这条评论点个赞时出错");
        }


    }


    /*------------------------------------------    对帖子进行评论    ------------------------------------------*/
    public void creatCommentOnPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            String content = request.getParameter("content");

            System.out.println("CommentController.creatCommentOnPost，为帖子评论，帖子id：" + postId);

            if (commentService.creatCommentOnPost(postId, boardId, userId, content)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("发表评论成功！")
                        )
                );
                System.out.println("-->发表评论成功！");
            } else {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "您已被禁止在该版块发布内容！")
                        )
                );
                System.out.println("--X>发表评论失败");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "对帖子进行评论时出错");
        }


    }


    /*------------------------------------------    对评论进行评论    ------------------------------------------*/
    public void creatCommentOnComment(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);
            Integer parentId = Integer.parseInt(request.getParameter("parentId"));
            String content = request.getParameter("content");


            System.out.println("CommentController.creatCommentOnComment，对评论进行评论，父评论id：" + parentId);

            if (commentService.creatCommentOnComment(postId, parentId, userId, content)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("发表评论成功！")
                        )
                );
                System.out.println("-->发表评论成功！");
            } else {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "您已被禁止在该版块发布内容！")
                        )
                );
                System.out.println("--X>发表评论失败");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "对评论进行评论时出错,");
        }


    }


}
