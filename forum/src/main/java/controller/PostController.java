package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.GetUserId;

import pojo.Post;
import pojo.ResponseResult;
import service.PostService;
import service.impl.PostServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/post/*")
public class PostController extends BaseServlet {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private PostService postService = new PostServiceImpl();


    /*----------------------------    获取该版块的所有帖子，优先推送他关注的用户发的帖子   ---------------------------*/
    public void getAllPostInThisBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = GetUserId.getUserId(request);
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        System.out.println("PostController，获取版块id：" + boardId + "  下的全部帖子，用户id" + userId);


        List<Post> allPostInThisBoard = postService.getAllPostInThisBoardPrioritizeUserLike(userId, boardId);

        // 获取版块信息
        response.getWriter().write(
                JSON.toJSONString(
                        ResponseResult.success(allPostInThisBoard)
                )
        );

        System.out.println("-获取全部版块成功！");
    }

}
