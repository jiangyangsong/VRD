package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/17 10:20
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //是否记住用户名和密码
        String rem = request.getParameter("rem");

        System.out.println(username+","+password+","+rem);
        //创建Dao并调用登录方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if (user != null) {
            //记住登录状态
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //判断是否需要记住用户名和密码
            if (rem != null) { //此时rem的值为on 表示需要记住用户名和密码
                //创建两个Cookie把用户名和密码装进去
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                /*
                 * 设置Cookie数据保存时间
                 * 如果不设置事件数据保存在内存中,浏览器关闭后数据删除
                 * 设置事件后数据保存在磁盘中只有时间到之后才会被删除
                 */
                c1.setMaxAge(30*24*60*60); //秒为单位.时间为一个月
                //将Cookie下发到客户端浏览器中
                response.addCookie(c1);
                response.addCookie(c2);
                System.out.println("cookie添加完成!");
            }
            //登录成功则重定向到首页
            response.sendRedirect("/home");
        } else {
            //登录失败则重定向到登录页面
            response.sendRedirect("/login");
        }
    }
}
