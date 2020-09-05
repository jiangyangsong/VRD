package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/21 11:22
 */
@WebServlet(name = "LoadMoreServlet",urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        int count = Integer.parseInt(request.getParameter("count"));
        System.out.println("页面已有:"+count);
        //创建Dao调用查询更多方法
        ProductDao dao = new ProductDao();
        List<Product> list = dao.loadmore(count);
        System.out.println(list);
        //将集合里面的数据转换成Json字符串
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(list);
        System.out.println(json);
        //把得到的json返回给客户端浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(json);
        pw.close();
    }
}
