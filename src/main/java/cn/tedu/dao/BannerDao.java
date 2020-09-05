package cn.tedu.dao;

import cn.tedu.entity.Banner;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName cn.tedu.dao
 * @FileName BannerDao
 * @Date 2020/8/17 16:58
 */
public class BannerDao {
    public List<Banner> findAll() {
        List<Banner> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from banner");
            while (rs.next()) {
                int id = rs.getInt(1);
                String imgPath = rs.getString(2);
                list.add(new Banner(id,imgPath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteById(int id) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("delete from banner where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("删除轮播图完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findPathById(int id) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select imgPath from banner where id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Banner banner) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("insert into banner values(null,?)");
            ps.setString(1,banner.getImgPath());
            ps.executeUpdate();
            System.out.println("添加轮播图完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
