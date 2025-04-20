package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;

import pojo.History;
import pojo.Post;
import pojo.ResponseResult;
import service.PostService;
import service.impl.PostServiceImpl;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/post/*")
public class PostController extends BaseServlet {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private PostService postService = new PostServiceImpl();


    /*------------------------------------------    记录历史浏览帖子    ----------------------------------------*/
    public void recordHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        Integer userId = ControllerToolMethod.getUserId(request);

        System.out.println("PostController.recordHistory" +
                "，记录访问历史，帖子id：" + postId + ",用户id：" + userId);

        if (postService.recordPost(postId, userId)) {
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("记录帖子浏览记录成功！")
                    )
            );
            System.out.println("-->记录用户id：" + userId + "帖子id：" + postId + "浏览记录成功！");
        } else {
            response.getWriter().write(JSON.toJSONString(
                    ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "帖子不存在！")
            ));
        }

    }


    /*------------------------------------------    获取历史浏览帖子    ----------------------------------------*/
    public void getHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);

        System.out.println("PostController.getHistory，获取访问历史,用户id：" + userId);

        List<History> postHistory = postService.getPostHistory(userId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(postHistory)
                ));

        System.out.println("-->用户id：" + userId + "获取历史记录成功！");
        postHistory.forEach(System.out::println);

    }


    /*----------------------------    获取该版块的所有帖子，优先推送他关注的用户发的帖子   ---------------------------*/
    public void getAllPostInThisBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = ControllerToolMethod.getUserId(request);
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        System.out.println("PostController，获取版块id：" + boardId + "  下的全部帖子，用户id" + userId);


        List<Post> allPostInThisBoard = postService.getAllPostInThisBoardPrioritizeUserLike(userId, boardId);

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(allPostInThisBoard)
                )
        );

        System.out.println("-获取该版块下的全部帖子成功！");
    }


    /*--------------------------------------    获取某条具体的帖子内容   ----------------------------------------*/
    public void getThisPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer postId = Integer.parseInt(request.getParameter("postId"));
        System.out.println("PostController，获取该帖子的所有内容，帖子id" + postId);

        Post post = postService.getThisPostById(postId);

        System.out.println("-帖子内容：\n" + post.getContent());

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(post)
                )
        );

        System.out.println("=获取该帖子成功！");
    }

}
