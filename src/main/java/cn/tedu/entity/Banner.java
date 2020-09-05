package cn.tedu.entity;

/**
 * @Author IANJIANG
 * @ProjectName vrd_2
 * @PackageName cn.tedu.entity
 * @FileName Banner
 * @Date 2020/8/17 16:58
 */
public class Banner {
    private int id;
    private String imgPath;

    public Banner(int id, String imgPath) {
        this.id = id;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
