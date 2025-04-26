package filter;

import controller.utils.ControllerToolMethod;
import utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {


    /*--------------------------------------------  非法请求拦截  --------------------------------------------*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        try {
            // 强转成Http
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            //判断访问资源路径是否和登录注册相关
            String[] urls = Constants.FREE_URLS;

            // 获取当前访问的资源路径
            String url = request.getRequestURL().toString();

            //循环判断
            for (String u : urls) {
                if (url.contains(u)) {
                    // 找到了就放行
                    filterChain.doFilter(request, servletResponse);
                    // 找不到打回去
                    return;
                }
            }

            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user != null) {
                filterChain.doFilter(request, servletResponse);
            } else {
                System.out.println("LoginFilter类,请先登录");
                request.setAttribute("login_msg", "请先登录！");
                request.getRequestDispatcher("/login.html").forward(request, servletResponse);
            }
        } catch (Exception e) {
            ControllerToolMethod.fetchException((HttpServletRequest) servletRequest
                    , (HttpServletResponse) servletResponse, e, "BaseServlet出现异常");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
