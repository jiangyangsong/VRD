package cn.tedu.controller;

import cn.tedu.dao.BannerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/19 16:52
 */
@WebServlet(name = "DeleteBannerServlet",urlPatterns = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        int id = Integer.parseInt(request.getParameter("id"));
        BannerDao dao = new BannerDao();
        //通过ID查询轮播图路径
        String imgPath = dao.findPathById(id);
        String filePath = request.getServletContext().getRealPath(imgPath);
        new File(filePath).delete();
        dao.deleteById(id);
        //重定向到修改轮播图页面
        response.sendRedirect("/showbanners");

    }
}
