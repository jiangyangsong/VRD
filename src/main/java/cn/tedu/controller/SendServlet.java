package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/18 9:27
 */
@MultipartConfig //实现文件上传注释
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取参数
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        System.out.println(title);
        System.out.println(author);
        System.out.println(intro);
        System.out.println(categoryId);
        //处理文件
        Part part = request.getPart("file");
        //处理文件名
        //得到上传文件的描述信息
        String info = part.getHeader("content-disposition");
        System.out.println(info);
        //得到文件的后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //得到唯一的文件名
        String fileName = UUID.randomUUID()+suffix;
        System.out.println(fileName);
        //得到日期相关的文件路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //创建当前日期对象
        Date date = new Date();
        String subPath = "images/"+format.format(date);
        System.out.println(subPath);
        //得到完整的路径
        String path = request.getServletContext().getRealPath(subPath);
        System.out.println(path);
        //创建文件夹
        new File(path).mkdirs();
        //把文件存入文件夹
        part.write(path+"/"+fileName);
        System.out.println("文件保存完成!");
        ProductDao dao = new ProductDao();
        dao.insert(new Product(0,title,author,intro,subPath+"/"+fileName,System.currentTimeMillis(),categoryId));
        //重定向
        response.sendRedirect("/home");
    }
}
