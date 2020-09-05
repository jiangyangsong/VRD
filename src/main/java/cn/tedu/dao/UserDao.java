package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName cn.tedu.dao
 * @FileName UserDao
 * @Date 2020/8/17 10:24
 */
public class UserDao {
    public User login(String username, String password) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select id from user where username=? and password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new User(id,username,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String check(String username) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select id from user where username=?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "用户名已存在!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "用户名可用!";
    }
}
