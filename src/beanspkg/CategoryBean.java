package beanspkg;


public class CategoryBean {

    private int catId;

    public CategoryBean() {
    }

    public CategoryBean(String description) {
     this.catDesc= description;
    }

    public CategoryBean(int catID, String desc) {
      this.catId= catID;
      this.catDesc= desc;
    }

    public CategoryBean(int catID) {
        this.catId= catID;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    private String catDesc;

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

}
