package controller.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 无论post和get请求都被这个方法处理

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String uri = request.getRequestURI();

        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);

        // 获得当前类 / 继承当前类的子类
        Class<? extends BaseServlet> clazz = this.getClass();


        try {
            // 获取方法对象，方法名methodName，形参是俩个字节码文件
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 执行方法，this指方法的调用者，即子类实例
            method.invoke(this, request, response);
        } catch (Exception e) {
            ControllerToolMethod.fetchException(request, response, e, "BaseServlet出现异常");
        }


    }
}
