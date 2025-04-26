package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;

import pojo.Collect;
import pojo.History;
import pojo.Post;
import pojo.ResponseResult;
import service.PostService;
import service.impl.PostServiceImpl;
import utils.Constants;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/post/*")
public class PostController extends BaseServlet {


    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private PostService postService = new PostServiceImpl();


    /*---------------------------------    获取该版块的所有帖子，优先显示晚新发布的   -------------------------------*/
    public void getAllPostInThisBoardOrderByTimeDesc(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("PostController.getAllPostInThisBoardOrderByTimeDesc" +
                    "，获取版块id：" + boardId + "  下的全部帖子");

            List<Post> resultPost = postService.getAllPostInThisBoardOrderByTimeDesc(boardId);

            // 返回成功响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(resultPost)
                    )
            );

            System.out.println("-->获取该版块的所有帖子，优先显示晚新发布的，成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取按时间降序排列的版块帖子时出错");
        }
    }


    /*-----------------------------------------    获取我的帖子    --------------------------------------------*/
    public void getMyPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("PostController.getMyPost，用户id：" + userId);
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(postService.getMyPost(userId))
                    )
            );
            System.out.println("-->获取我的帖子成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取我的帖子时出错");
        }
    }


    /*--------------------------------------------    发布帖子    --------------------------------------------*/
    public void creatPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("版块id:" + boardId + "下，用户id:"
                    + userId + "创建帖子，标题为：" + title + "\n帖子内容：" + content);

            if (postService.creatPost(boardId, title, content, userId)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("发布帖子成功！")
                        )
                );
                System.out.println("-->创建帖子成功！");
            } else {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.error(Constants.RESPONSE_CODE_FORBIDDEN, "您被禁止在该版块发帖子！")
                        )
                );
                System.out.println("-->创建帖子失败！用户已被禁止在该版块发帖子");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "发布帖子时出错");
        }
    }


    /*--------------------------------------------    收藏这条帖子    -----------------------------------------*/
    public void collectThisPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);
            String remark = request.getParameter("remark");

            System.out.println("PostController.collectThisPost" +
                    "，收藏帖子id：" + postId + ",用户id：" + userId);

            if (postService.collectThisPost(postId, userId, remark)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("收藏帖子成功！")
                        )
                );
                System.out.println("-->记录用户id：" + userId + "帖子id：" + postId + "浏览记录成功！");
            } else {
                response.getWriter().write(JSON.toJSONString(
                        ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "帖子不存在！")
                ));
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "收藏帖子时出错");
        }

    }


    /*------------------------------------------    记录历史浏览帖子    ----------------------------------------*/
    public void recordHistory(HttpServletRequest request, HttpServletResponse response) {
        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "记录浏览历史时出错");
        }

    }


    /*------------------------------------------    获取历史浏览帖子    ----------------------------------------*/
    public void getHistory(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("PostController.getHistory，获取访问历史,用户id：" + userId);

            List<History> postHistory = postService.getPostHistory(userId);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(postHistory)
                    ));

            System.out.println("-->用户id：" + userId + "获取历史记录成功！");
            postHistory.forEach(System.out::println);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取浏览历史时出错");
        }

    }


    /*----------------------------    获取该版块的所有帖子，优先推送他关注的用户发的帖子   ---------------------------*/
    public void getAllPostInThisBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取版块帖子时出错");
        }
    }


    /*--------------------------------------    获取某条具体的帖子内容   ----------------------------------------*/
    public void getThisPost(HttpServletRequest request, HttpServletResponse response) {

        try {
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
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取帖子详情时出错");
        }
    }


    /*-----------------------------------------    批量删除帖子   ---------------------------------------------*/
    public void deleteThesePost(HttpServletRequest request, HttpServletResponse response) {

        try {
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
        } catch (IOException e) {
            ControllerToolMethod.fetchException(request, response, e, "批量删除帖子时出错");
        }

    }


    /*------------------------------------------    为这条帖子点个赞    ----------------------------------------*/
    public void likeThisPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("PostController.likeThisPost，为帖子点赞，帖子id：" + postId);

            if (postService.likeThisPost(postId, userId)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("点赞帖子成功！")
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
            ControllerToolMethod.fetchException(request, response, e, "点赞帖子时出错");
        }

    }


    /*------------------------------------------    获取我的收藏帖子    ----------------------------------------*/
    public void getCollect(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("PostController.getCollect，获取收藏记录,用户id：" + userId);

            List<Collect> postCollect = postService.getPostCollect(userId);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(postCollect)
                    ));

            System.out.println("-->用户id：" + userId + "获取历史记录成功！");
            postCollect.forEach(System.out::println);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取收藏记录时出错");
        }

    }


    /*---------------------------------    判断某条帖子，用户是否已经收藏   ---------------------------------------*/
    public void checkIfCollect(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("PostController.checkIfCollect" +
                    "，判断帖子是否收藏，帖子id" + postId + "，用户id：" + userId);

            if (postService.checkIfCollect(postId, userId)) {
                // 返回已经收藏响应
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("yes")
                        )
                );
                System.out.println("-->已经收藏");
            } else {
                // 返回已经收藏响应
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("no")
                        )
                );
                System.out.println("-->还没有收藏");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "检查收藏状态时出错");
        }


    }


    /*-----------------------------------------    删除收藏这条帖子   ------------------------------------------*/
    public void cancelCollectThisPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("PostController，cancelCollectThisPost，取消收藏帖子id:" + postId + ",用户id:" + userId);

            postService.cancelCollectThisPost(postId, userId);

            // 返回成功响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("取消收藏成功！")
                    )
            );

            System.out.println("-->取消收藏帖子成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "取消收藏帖子时出错");
        }

    }


}
