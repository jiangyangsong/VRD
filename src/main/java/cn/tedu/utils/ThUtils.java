package cn.tedu.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author IANJIANG
 * @ProjectName myservlet_3_1
 * @PackageName cn.tedu.utils
 * @FileName ThUtils
 * @Date 2020/8/14 14:41
 */
public class ThUtils {
    private static TemplateEngine te;
    static {
        //创建模板引擎
        te = new TemplateEngine();
        //创建解析器,此解析器会自动查找resources目录下的html文件
        ClassLoaderTemplateResolver cr = new ClassLoaderTemplateResolver();
        //设置字符集
        cr.setCharacterEncoding("UTF-8");
        //将解析器和模板引擎对象关联
        te.setTemplateResolver(cr);

    }

    public static void print(String fileName, Context context, HttpServletResponse response) throws IOException {
        //让html模板页面和Context里面的数据整合到一起得到一个新的html
        String html = te.process(fileName,context);
        //把得到的html返回给客户端浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter(); //异常抛出
        pw.print(html);
        pw.close();
    }
}
