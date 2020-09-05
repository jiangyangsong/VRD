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
 * @Date 2020/8/17 10:41
 */
@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String keyword = request.getParameter("keyword");
        System.out.println("搜索内容:"+keyword);
        System.out.println("分类id:"+cid);
        Context context = new Context();
        //创建CategoryDao 并调用findAll方法
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findAll();
        context.setVariable("list",list);

        //得到当前登录的用户对象装进context容器中
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        context.setVariable("user",user);

        //查询出所有的轮播图并装进容器中
        BannerDao bannerDao = new BannerDao();
        List<Banner> blist = bannerDao.findAll();
        context.setVariable("blist",blist);

        //查询出所有作品信息并装进容器
        ProductDao pDao = new ProductDao();
        List<Product> plist = null;
        if (cid != null) { //分类
            plist = pDao.findByCid(cid);
        } else if (keyword != null) { //搜索
            plist = pDao.findByKeyword(keyword);
        } else {
            plist = pDao.findAll();
            System.out.println("全部作品已找到!");
        }
        context.setVariable("plist",plist);

        //查询浏览最多的作品信息
        List<Product> viewList = pDao.findViewList();
        context.setVariable("viewList",viewList);

        //查询最受欢迎的作品信息
        List<Product> likeList = pDao.findLikeList();
        context.setVariable("likeList",likeList);

        ThUtils.print("home.html",context,response);
    }
}
