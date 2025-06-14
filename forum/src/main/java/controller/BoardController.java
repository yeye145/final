package controller;

import com.alibaba.fastjson.JSON;
import controller.utils.BaseServlet;
import controller.utils.ControllerToolMethod;
import pojo.Board;
import pojo.Notice;
import pojo.ResponseResult;
import service.BoardService;
import service.impl.BoardServiceImpl;
import utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/board/*")
public class BoardController extends BaseServlet {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private BoardService boardService = new BoardServiceImpl();


    /*--------------------------------------    删除所有已处理的版块申请    -------------------------------------*/
    public void deleteAllDealApply(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("MessageController,deleteAllDealApply，删除所有已处理的版块申请");

            boardService.deleteAllDealApply();

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("已删除所有已处理的版块申请")
                    )
            );

            System.out.println("---->删除所有已处理的版块申请！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "删除所有已处理的版块申请时出错");
        }

    }


    /*----------------------------------------    是否有新的版块申请    ----------------------------------------*/
    public void checkIfNewBoardApply(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("MessageController,checkIfNewBoardApply，查看是否有新的版块申请");

            if (boardService.checkIfNewBoardApply()) {
                // 有新的通知
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("yes")
                        )
                );

                System.out.println("---->有新的申请！");
            } else {
                // 没有新的通知
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("no")
                        )
                );

                System.out.println("---->《没》有新的申请");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "查看是否有新的版块申请时出错");
        }

    }


    /*---------------------------------------    拒绝创建版块的申请   ------------------------------------------*/
    public void refuseThisApply(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer applyId = Integer.parseInt(request.getParameter("applyId"));
            System.out.println("BoardController.agreeThisApply，拒绝创建版块的申请,apply申请id：" + applyId);

            boardService.refuseApplyNewBoard(applyId);
            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("拒绝创建申请")
                    )
            );

            System.out.println("-->拒绝创建版块的申请！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "拒绝创建版块的申请时出错");
        }
    }


    /*---------------------------------------    同意创建版块的申请   ------------------------------------------*/
    public void agreeThisApply(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer applyId = Integer.parseInt(request.getParameter("applyId"));
            System.out.println("BoardController.agreeThisApply，同意创建版块的申请,apply申请id：" + applyId);

            boardService.agreeApplyNewBoard(applyId);
            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("同意创建申请")
                    )
            );

            System.out.println("-->同意创建版块的申请！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "同意创建版块的申请时出错");
        }
    }


    /*---------------------------------------    获取创建版块的申请   ------------------------------------------*/
    public void getAllApplyNewBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("BoardController.getAllApplyNewBoard，获取创建版块的申请");

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(boardService.getAllApplyNewBoard())
                    )
            );

            System.out.println("-->获取创建版块的申请成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取创建版块的申请时出错");
        }
    }


    /*---------------------------------------    获取按id排序的版块   ------------------------------------------*/
    public void getAllBoardOrderById(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("BoardController，获取按id排序的版块");

            List<Board> resultBoard = boardService.getAllBoardOrderById();

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(resultBoard)
                    )
            );

            System.out.println("-获取按id排序的版块成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取按id排序的版块时出错");
        }
    }


    /*---------------------------------    判断某个版块，用户是否已经关注   ---------------------------------------*/
    public void checkIfHadSubscribeThisBoard(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("BoardController.checkIfHadSubscribeThisBoard" +
                    "，判断版块是否收藏，版块id" + boardId + "，用户id：" + userId);

            if (boardService.checkIfSubcribe(boardId, userId)) {
                // 返回已经收藏响应
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("yes")
                        )
                );
                System.out.println("-->已经关注该版块");
            } else {
                // 返回已经收藏响应
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("no")
                        )
                );
                System.out.println("-->还没有关注该版块");
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "判断某个版块用户是否已经关注时出错");
        }


    }


    /*-----------------------------------------    取消关注这个版块    -----------------------------------------*/
    public void cancelSubscribeThisBoard(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            Integer userId = ControllerToolMethod.getUserId(request);

            System.out.println("BoardController，cancelSubscribeThisBoard，取消关注版块id:" + boardId + ",用户id:" + userId);
            boardService.cancelSubscribeThisBoard(boardId, userId);


            // 返回成功响应
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("取消关注版块成功！")
                    )
            );

            System.out.println("-->取消关注版块成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "取消关注版块时出错");
        }

    }


    /*-------------------------------------------    关注这个版块    ------------------------------------------*/
    public void subscribeThisBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("BoardController，关注版块，用户id" + userId + "关注的版块id：" + boardId);

            boardService.subscribeThisBoard(boardId, userId);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("关注成功！")
                    )
            );

            System.out.println("-关注版块成功！--");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "关注这个版块时出错");
        }
    }


    /*-------------------------------------------    获取我的版块    ------------------------------------------*/
    public void getMyBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("BoardController，获取我的版块，用户id" + userId);

            List<Board> targetBoard = boardService.getMyBoard(userId);

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(targetBoard)
                    )
            );

            System.out.println("-获取版块成功！--" + targetBoard);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取我的版块时出错");
        }
    }


    /*------------------------------------    获取全部版块，优先渲染他喜欢的   -----------------------------------*/
    public void getAllBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("BoardController，获取全部版块，用户id" + userId);

            List<Board> allBoard = boardService.getAllBoardPrioritizeUserLike(userId);

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(allBoard)
                    )
            );

            System.out.println("-获取全部版块成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取全部版块时出错");
        }
    }


    /*-------------------------------------------    获取热门版块   ------------------------------------------*/
    public void getHotBoard(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("BoardController，获取热门版块");

            List<Board> hotBoard = boardService.getHotBoard();

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(hotBoard)
                    )
            );

            System.out.println("-获取热门版块成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取热门版块时出错");
        }
    }


    /*-------------------------------------------    获取最新版块   ------------------------------------------*/
    public void getNewBoard(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("BoardController，获取最新版块");

            List<Board> newBoard = boardService.getNewBoard();

            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(newBoard)
                    )
            );

            System.out.println("-获取最新版块成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取最新版块时出错");
        }
    }


    /*-------------------------------------------    获取全部公告    ------------------------------------------*/
    public void getNotice(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("BoardController，获取全部公告版块，版块id" + boardId);

            List<Notice> allNoticeInThisBoard = boardService.getAllNoticeInThisBoard(boardId);
            // 获取版块信息
            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success(allNoticeInThisBoard)
                    )
            );

            System.out.println("-获取全部公告成功！");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "获取全部公告时出错");
        }
    }


    /*-------------------------------------------    申请新的版块    ------------------------------------------*/
    public void applyNewBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = ControllerToolMethod.getUserId(request);
            System.out.println("BoardController，申请版块，用户id" + userId);
            String title = request.getParameter("title");
            String type = request.getParameter("type");
            String notice = request.getParameter("notice");
            System.out.println("-版块标题：" + title + "，版块类型：" + type + "\n-版块公告：" + notice);

            boardService.applyNewBoard(userId, title, type, notice);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("申请成功！请等待管理员审核")
                    )
            );

            System.out.println("-->申请成功！等待审核");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "申请新的版块时出错");
        }

    }


    /*-------------------------------------------     版块封禁用户    -----------------------------------------*/
    public void banUserInThisBoard(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("BoardController，版块封禁用户，版主id：" + ControllerToolMethod.getUserId(request));
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            String reason = request.getParameter("reason");
            String userName = request.getParameter("userName");
            System.out.println("-->封禁用户：" + userName + ",版块id：" + boardId + ",封禁原因：\n" + reason);

            if (boardService.banUserInThisBoard(boardId, userName, reason)) {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.success("封禁用户发帖成功")
                        )
                );
            } else {
                response.getWriter().write(
                        JSON.toJSONString(
                                ResponseResult.error(Constants.RESPONSE_CODE_NOT_FOUND, "用户名不存在")
                        )
                );
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "版块封禁用户时出错");
        }
    }


    /*-----------------------------------------    发布新的版块公告    ------------------------------------------*/
    public void publishNewNotice(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer boardId = Integer.parseInt(request.getParameter("boardId"));
            String content = request.getParameter("content");
            System.out.println("BoardController，发布版块公告，版块id" + boardId);

            boardService.publishNewNotice(boardId, content);

            response.getWriter().write(
                    JSON.toJSONString(
                            ResponseResult.success("发布成功")
                    )
            );

            System.out.println("-->公告发布成功！内容：\n" + content
                    + "\n--------------------------------------------------------------------------------");
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "发布新的版块公告时出错");
        }

    }

}
