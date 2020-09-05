package cn.tedu.dao;

import cn.tedu.entity.Product;
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
 * @FileName ProductDao
 * @Date 2020/8/18 10:46
 */
public class ProductDao {
    public void insert(Product product) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("insert into product values(null,?,?,?,?,?,?,0,0)");
            ps.setString(1,product.getTitle());
            ps.setString(2,product.getAuthor());
            ps.setString(3,product.getIntro());
            ps.setString(4,product.getImgPath());
            ps.setLong(5,product.getCreated());
            ps.setInt(6,product.getCategoryId());
            ps.executeUpdate();
            System.out.println("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from product order by created desc limit 0,5");
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findViewList() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from product order by viewCount desc limit 0,4");
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findLikeList() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from product order by likeCount desc limit 0,4");
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByCid(String cid) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select * from product where categoryId=? order by created desc");
            ps.setInt(1,Integer.parseInt(cid));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByKeyword(String keyword) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select * from product where title like ? order by created desc");
            ps.setString(1,"%"+keyword+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(int pid) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select * from product where id=?");
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                return new Product(id,title,author,intro,imgPath,created,categoryId,viewCount,likeCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(int id) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("delete from product where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("删除完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likeById(int id) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("update product set likeCount=likeCount+1 where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("修改完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewById(int id) {
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("update product set viewCount=viewCount+1 where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("浏览量增加!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadmore(int count) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn();){
            PreparedStatement ps = conn.prepareStatement("select * from product order by created desc limit ?,5");
            ps.setInt(1,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,
                        intro,imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
