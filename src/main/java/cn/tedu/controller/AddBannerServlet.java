package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/19 18:20
 */
@MultipartConfig
@WebServlet(name = "AddBannerServlet",urlPatterns = "/addbanner")
public class AddBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取参数
        Part part = request.getPart("file");
        System.out.println(part);
        //获取文件描述
        String info = part.getHeader("content-disposition");
        System.out.println(info);
        //获取后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //获取路径
        String path = request.getServletContext().getRealPath("images/");
        //唯一文件名
        String fileName = UUID.randomUUID()+suffix;
        part.write(path+fileName);
        BannerDao dao = new BannerDao();
        dao.insert(new Banner(0,"images/"+fileName));
        //重定向到首页
        response.sendRedirect("/showbanners");
    }
}
