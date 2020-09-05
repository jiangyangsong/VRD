package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

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
 * @Date 2020/8/19 14:09
 */
@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao dao = new ProductDao();
        //获取作品的完整信息
        Product p = dao.findById(id);
        //得到作品图片的完整路径
        String filePath = request.getServletContext().getRealPath(p.getImgPath());
        System.out.println(filePath);
        //根据图片完整路径创建文件对象并删除文件
        File file = new File(filePath);
        file.delete();
        dao.deleteById(id);
        //重定向到首页
        response.sendRedirect("/home");
    }
}
