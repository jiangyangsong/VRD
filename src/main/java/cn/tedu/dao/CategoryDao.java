package cn.tedu.dao;

import cn.tedu.entity.Category;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName cn.tedu.dao
 * @FileName CategoryDao
 * @Date 2020/8/17 15:22
 */
public class CategoryDao {

    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from category");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                list.add(new Category(id,name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
