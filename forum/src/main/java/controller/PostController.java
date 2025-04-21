package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

        // 返回成功响应
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

        // 返回成功响应
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(post)
                )
        );

        System.out.println("=获取该帖子成功！");
    }


    /*-----------------------------------------    批量删除帖子   ---------------------------------------------*/
    public void deleteThesePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("PostController，deleteThesePost，删除帖子");
        // 获得html传递过来的数组
        String json = request.getReader().readLine();

        // 解析JSON数据
        JSONObject jsonData = JSON.parseObject(json);

        // 获取解析后的集合
        List<Integer> postIdAboutToDelete = jsonData.getJSONArray("postId").toJavaList(Integer.class);

        postIdAboutToDelete.forEach(System.out::println);

        // 传递参数，执行删除服务
        postService.deleteThesePost(postIdAboutToDelete);

        // 返回成功响应
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("删除成功！")
                )
        );

        System.out.println("-->删除这些帖子成功！");

    }


    /*------------------------------------------    为这条帖子点个赞    ----------------------------------------*/
    public void likeThisPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer postId = Integer.parseInt(request.getParameter("postId"));

        System.out.println("PostController.likeThisPost，为帖子点赞，帖子id：" + postId);

        postService.likeThisPost(postId);

        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success("记录帖子浏览记录成功！")
                )
        );

    }



}
