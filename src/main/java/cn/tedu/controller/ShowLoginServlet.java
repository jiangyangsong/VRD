package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/17 10:15
 */
@WebServlet(name = "ShowLoginServlet",urlPatterns = "/login")
public class ShowLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示登录页面
        Context context = new Context();
        //取出Cookie中的用户名和密码装进容器 最后显示到页面中
        Cookie[] cookies = request.getCookies();
        //由于有可能浏览器中没有任何cookie 从数组中取数据之前需要先添加非空判断
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if (name.equals("username")) { //判断是否为用户名
                    //把用户名存进容器中
                    context.setVariable("name",value);
                }
                if (name.equals("password")) { //判断是否为密码
                    //把密码存进容器中
                    context.setVariable("pwd",value);
                }
            }
        }
        ThUtils.print("login.html",context,response);
    }
}
