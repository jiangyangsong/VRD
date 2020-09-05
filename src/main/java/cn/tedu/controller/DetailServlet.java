package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName ${PACKAGE_NAME}
 * @FileName ${NAME}
 * @Date 2020/8/19 10:31
 */
@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ID
        int id = Integer.parseInt(request.getParameter("id"));
        //创建Dao并调用findById方法
        ProductDao pDao = new ProductDao();
        //增加浏览量
        pDao.viewById(id);

        Product product = pDao.findById(id);
        Context context = new Context();
        context.setVariable("product",product);

        //查询出所有作品信息并装进容器
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findAll();
        context.setVariable("list",list);

        //得到当前登录的用户对象装进context容器中
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        context.setVariable("user",user);

        //查询浏览最多的作品信息
        List<Product> viewList = pDao.findViewList();
        context.setVariable("viewList",viewList);

        //查询最受欢迎的作品信息
        List<Product> likeList = pDao.findLikeList();
        context.setVariable("likeList",likeList);

        ThUtils.print("detail.html",context,response);
    }
}
