/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beanspkg.Order;
import beanspkg.User;
import beanspkg.UserRateProduct;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servicespkg.CategoryService;
import servicespkg.DbService;
import servicespkg.OrderService;
import servicespkg.ProductService;
import servicespkg.UserRateProductService;
import servicespkg.UserService;
import view.AddDeliveryBoy;
import view.AddProducts;
import view.AddSupplier;
import view.AdminOrders;
import view.AdminProductDetails;
import view.AdminProfile;
import view.AdminViewProducts;
import view.Category;
import view.DeliveryOrder;
import view.DeliveryProfile;
import view.Home;
import view.Login;
import view.ProductDetails;
import view.SignUp;
import view.SupplierProfile;
import view.SupplierViewProducts;
import view.UserOrder;
import view.UserProfile;
import view.Utility;
import view.ViewProducts;
import view.VisitorProductDetails;
import view.VisitorViewProducts;

/**
 *
 * @author Muhammad Lupate
 */
public class MainController {

    JFrame currentForm;
    User userExist = new User();
    Order order = new Order();
    ProductService productService = new ProductService();
    UserRateProductService userRateProductService = new UserRateProductService();
    UserRateProduct userRateProduct = new UserRateProduct();
    ProductDetails productDetails = new ProductDetails();
    OrderService orderService = new OrderService();
//    UserRateProduct userRateProduct = new UserRateProduct();
    
    Long prod_id;
    Long order_id;

    public Long getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
    
    public void setProd_id(Long prod_id) {
        this.prod_id = prod_id;
    }
    public Long getProd_id() {
        return prod_id;
    }
    ResultSet rs = null;

    public MainController() {
        Utility.themeLookAndFeel();
        currentForm = new Home(this);
        currentForm.setVisible(true);
        Utility.centerFrame(currentForm);

    }

    //Home buttons
    public void browseProducts() {
        currentForm.setVisible(false);
        currentForm = new ViewProducts(this);
        currentForm.setVisible(true);
    }

    public void backToHome() {
        currentForm.setVisible(false);
        currentForm = new Home(this);
        currentForm.setVisible(true);
    }

    public void signIn() {
        currentForm.setVisible(false);
        currentForm = new Login(this);
        currentForm.setVisible(true);
    }

    //Delivery profile buttons
    public void delOrder() {
        currentForm.setVisible(false);
        currentForm = new DeliveryOrder(this);
        currentForm.setVisible(true);
    }

    //Delivery Order buttons
    public void showDelProfile() {
        currentForm.setVisible(false);
        currentForm = new DeliveryProfile(this);
        currentForm.setVisible(true);
    }

    //Customer buttons
    public void showCProfile() {
        currentForm.setVisible(false);
        currentForm = new UserProfile(this);
        currentForm.setVisible(true);
    }

    public void showProdDetails() {
        currentForm.setVisible(false);
        currentForm = new ProductDetails(this);
        currentForm.setVisible(true);
    }

    //Show Customer Orders
    public void showCustomerOrder() {
        currentForm.setVisible(false);
        currentForm = new UserOrder(this);
        currentForm.setVisible(true);
    }

    public void login(String name, String password) {
        try {
            dto.Login logindata = new dto.Login(name, password); //DTO "Data Transfer Object"
            UserService userService = new UserService();
            userExist = userService.login(logindata);
            if (userExist.getUserId() > 0) {
                currentForm.setVisible(false); //Login hide
                currentForm = checkUserType(userExist.getUserType()); // instantiate object of "Edit the comment to describe"
                currentForm.setVisible(true);
            } else {
                ((Login) currentForm).showErrorDialog("This is not a valid credentials .. !");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(currentForm, "This is a not a valid cresential ...");
        }
    }

    public JFrame checkUserType(int type) {
        switch (type) {
            case 1:
                currentForm = new AdminViewProducts(this);
                break;
            case 2:
                 currentForm = new SupplierViewProducts(this);
                break;
            case 3:
                currentForm = new UserProfile(this);
                break;
            case 4:
                currentForm = new DeliveryProfile(this);
                break;
            default:
                currentForm = new Home(this);
        }
        return currentForm;
    }

    //Load Profile data
    public ArrayList loadDataToForm() {
        ArrayList list = new ArrayList();
        list.add(userExist.getFullName());
        list.add(userExist.getEmail());
        list.add(String.valueOf(userExist.getPhone()));
        return list;
    }

    //Update data from delivery form to DB
    public void updateData(String t1, String t2, long t3, String t4) {
        UserService uService = new UserService();
        userExist.setFullName(t1);
        userExist.setEmail(t2);
        userExist.setPhone(t3);
        userExist.setPassword(t4);
        try {
            int worAffected = uService.update(userExist);
            if (worAffected > 0) {
                JOptionPane.showMessageDialog(currentForm, "Data updated successfully");

            } else {
                JOptionPane.showMessageDialog(currentForm, "Error updating data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Deleviry scenario methods for loading data to table 
    public ResultSet showRequiredOrders() {
        try {
            rs = orderService.selectDataAsRS();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet showPendingOrders() {

        try {
            rs = orderService.selectDataWithConditionAsRS();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Update Delivery Orders
    public void updateSelectedOrderStatus(){
        OrderService o = new OrderService();
        try {
           order = o.selectOne(order_id);
           order.setUserId((int) userExist.getUserId());
           order.setStatus(1);
           java.util.Date date= new java.util.Date();
           order.setDeliveryDate(new Timestamp(date.getTime()));
           o.update(order);
           JOptionPane.showMessageDialog(currentForm, "Order Delivered");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Customer scenario buttons
    //View Products Form Methods
    //load all products to table
    public ResultSet loadProducts() {
        try {
            rs = productService.selectAllProduts();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load all Categories to combobox
    public ResultSet loadCategories() {
        CategoryService categoryService = new CategoryService();
        try {
            rs = categoryService.selectAllCategories();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load all suppliers
    public ResultSet loadSuppliers() {
        UserService userService = new UserService();
        try {
            rs = userService.selectAllSuppliers();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load all Product names
    public ResultSet loadProductNames() {
        try {
            rs = productService.selectAllProdutNames();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load Products which were searched by category
    public ResultSet loadCategoryProducts(String str) {
        try {
            rs = productService.selectProdutsSearchedByCategory(str);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load Products which were searched by Suppliers
    public ResultSet loadSupplierProducts(String str) {
        try {
            rs = productService.selectProdutsSearchedBySupplier(str);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Load Products which were searched by names
    public ResultSet loadNameProducts(String str) {
        try {
            rs = productService.selectProdutsSearchedByName(str);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Get the selected row from products table
    public ResultSet getOneProduct(long prod_id) {
        ProductService productservice = new ProductService();
        try {
            rs = productservice.selectOneProduct(prod_id);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getOrderedProduct(long prod_id) {
        ProductService productservice = new ProductService();
        try {
            rs = productservice.selectOneProductWithCondition(prod_id);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Product details form methods
    //Raiting methods
    //Set product Rate data
    public void setProductRate() {
        UserRateProduct data = new UserRateProduct();
        data.setLikeCount(String.valueOf(Utility.rate));
        data.setUserId(userExist.getUserId());
        data.setProdId(prod_id);
        try {
            userRateProductService.updateData(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(currentForm, "Can't rate again");
        }
    }

    //Get product rate for product
    public int getProductRate(Long productID, int userID) {
        int productRate = 0;
        try {
            userRateProduct = userRateProductService.selectOne(productID, userID);
            productRate = Integer.parseInt(userRateProduct.getLikeCount());       
        } catch (Exception ex) {
                ArrayList l = new ArrayList();
                l.add(productID);
                l.add(userID);
                l.add(0);
            try {
                userRateProductService.insertData(l);
            } catch (SQLException ex1) {
            JOptionPane.showMessageDialog(currentForm, "Error in DB server (INSERTING RATE)");
            }
        }
        return productRate;
    }

    // Get current user id
    public long getUserID() {
        return userExist.getUserId();
    }

    //Get the orderd product
    public ResultSet getOneProductOrder() {
        ProductService productservice = new ProductService();
        try {
            rs = productservice.selectOneProduct(prod_id);
        } catch (SQLException ex) {
            System.out.println("error in controller");
        }
        return rs;
    }
    //Get the Cart content
    ProductService productservice = new ProductService();
    int quant;
    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
    public ArrayList getOneProductCart(Long id) {
        ArrayList prodInCart = new ArrayList();
        try {

            rs = productservice.selectOneProduct(id);
            while (rs.next()) {
                prodInCart.add(rs.getString("prod_name"));
                prodInCart.add(rs.getInt("price"));
                prodInCart.add(rs.getInt("off_sale"));
            }
            return prodInCart;
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
        
    }

    //ArrayList of product order id's
    private ArrayList list = new ArrayList();
    public void setList(ArrayList l) {
        list = l;
    }

    public ArrayList getList() {
        return list;
    }

    //ArrayList of quant values
    private ArrayList quantValues = new ArrayList();

    public ArrayList getQuantValues() {
        return quantValues;
    }

    public void setQuantValues(ArrayList quantValues) {
        this.quantValues = quantValues;
    }
    //Save order into DB
    public void saveOrder(){
        order.setUserId((int) userExist.getUserId());
    }

    
    ///////////////////mohammed///////////
    
     //centerlize method
    public void centerFrame(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        // Move the window
        frame.setLocation(x, y);
    }
    /////end of method
     //Home buttons
    public void adminBrowseProducts() {
        currentForm.setVisible(false);
        currentForm = new AdminViewProducts(this);
        currentForm.setVisible(true);
    }
    
    public void showProdDetailsAdmin() {

        currentForm.setVisible(false);
        currentForm = new AdminProductDetails(this);
        currentForm.setVisible(true);

    }
    
    ////////////////////////////////////////// Admin Methods///////////////////////////
    //1-first method -->browseProducts()(alredy exsit) to go to viewproduct form from control->product menu at admin profile
    //2-second method to view users orders form from control->orders menu at admin profile
    public void viewUserOrders() {
        currentForm.setVisible(false);
        currentForm = new AdminOrders(this);
        currentForm.setVisible(true);
    }

    //3-third method to add supplier form from control->suppliers menu at admin profile
    public void addSupplier() {
        currentForm.setVisible(false);
        currentForm = new AddSupplier(this);
        currentForm.setVisible(true);
    }

    //4-add delivery boy form from control->delivery boy menu at admin profile
    public void addDeliveryBoy() {
        currentForm.setVisible(false);
        currentForm = new AddDeliveryBoy(this);
        currentForm.setVisible(true);
    }
    //admin profile form
    public void showAdminProfile() {
        currentForm.setVisible(false);
        currentForm = new AdminProfile(this);
        currentForm.setVisible(true);
    }

    //method which retrive admin profile data
    public ArrayList getAdminProData() {
        ArrayList dataList = new ArrayList();
        dataList.add(userExist.getFullName());
        dataList.add(userExist.getAddress());
        dataList.add(userExist.getCity());
        dataList.add(userExist.getEmail());
        dataList.add(userExist.getPassword());
        dataList.add(userExist.getPhone());
        dataList.add(userExist.getPhoto2());
        dataList.add(userExist.getCompany());
        dataList.add(userExist.getUserId());

        return dataList;
    }

    //method which update admin profile data 
    public void updateaAdminData(String name, String email, String password, String phone, byte[] personimage, long id) throws SQLException {

        userExist.setUserId(id);
        userExist.setFullName(name);
        userExist.setAddress(userExist.getAddress());
        userExist.setCity(userExist.getCity());
        userExist.setUserType(userExist.getUserType());
        userExist.setEmail(email);
        userExist.setPassword(password);
        userExist.setPhone(Long.parseLong(phone));
        userExist.setPhoto2(personimage);
        int userUpdated = 0;
        try {
            UserService userService = new UserService();
            userUpdated = userService.update2(userExist);
            if (userUpdated > 0) {
                JOptionPane.showMessageDialog(null, " your data updated successfully!");

            } else {
                JOptionPane.showMessageDialog(null, " Invalid data !!");

            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, " Invalid data !!");

        }
    }

    //method to add supplier
    public void addSupplier_admin(String name, String address, int city, String email, String password, long phone, byte[] personimage, String company, int type) throws SQLException {
        User user = new User();
        user.setFullName(name);
        user.setAddress(address);
        user.setCity(city);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setPhoto2(personimage);
        user.setCompany(company);
        user.setUserType(type);
        UserService uService = new UserService();
        if (user != null) {
            uService.insert2(user);
            JOptionPane.showMessageDialog(null, "Supplier added successfully! ");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid data entered !");
        }

    }

    //method to add delivery boy
    public void addDeleviry_admin(String name, String address, int city, String email, String password, long phone, byte[] personimage, String company, int type) throws SQLException {
        User user = new User();
        user.setFullName(name);
        user.setAddress(address);
        user.setCity(city);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setPhoto2(personimage);
        user.setCompany(company);
        user.setUserType(type);
        UserService uService = new UserService();
        if (user != null) {
            uService.insert2(user);
            JOptionPane.showMessageDialog(null, "Deliveryboy added successfully! ");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid data entered !");
        }

    }

    //method to display orders for admin
    public ResultSet showRequiredOrdersAdmin() {
        OrderService orderService = new OrderService();
        ResultSet rs = null;
        try {
            rs = orderService.selectDataAsRSAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    //

    //method to display products for admin
    public ResultSet showProductsAdmin() {
        OrderService orderService = new OrderService();
        ResultSet rs = null;
        try {
            rs = orderService.selectProductsAsRSAdmin();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(currentForm,"failed to loade products ");
        }
        return rs;
    }
   
    //Hagar Code
    // Displaying Forms Methods

    public void signUp() {
        currentForm.setVisible(true);
        currentForm = new SignUp(this);
        currentForm.setVisible(true);
    }

    public void signOut() {
        System.exit(0);
    }

    public void viewSupplierProfile() {

        currentForm = new SupplierProfile(this);
        currentForm.setVisible(true);
    }
  public void VisitorviewProducts(){
        currentForm = new VisitorViewProducts(this);
        currentForm.setVisible(true);
 }
  
   public void VisitorProductsDetails(){
         currentForm.setVisible(true);
        currentForm = new VisitorProductDetails(this);
        currentForm.setVisible(true);
 }
    public void addProduct() {

        currentForm = new AddProducts(this);
        currentForm.setVisible(true);
        currentForm.setAlwaysOnTop(true);

    }

    public void addCategory() {

        currentForm = new Category(this);
        currentForm.setVisible(true);
        currentForm.setAlwaysOnTop(true);
    }

    //Getting user ID
    public int checkUserID() {

        int id = (int) userExist.getUserId();
        return id;
    }
    //Getting user Type 

  
    // Displaying data in the Supplier profile

    public ArrayList retrieveData() {

        ArrayList data = new ArrayList();
        data.add(userExist.getFullName());
        data.add(userExist.getCompany());
        data.add(userExist.getEmail());
        data.add(userExist.getPhone());
        data.add(userExist.getPassword());
        return data;

    }
//Updating data in the Supplier profile

    public void updateSupplierData(String sName, String sCompany, String sEmail, int sPhone, String sPassword) {

        UserService uService = new UserService();
        System.out.println("UserExist" + userExist.getUserId());
        userExist.setFullName(sName);
        userExist.setCompany(sCompany);
        userExist.setEmail(sEmail);
        userExist.setPhone(sPhone);
        userExist.setPassword(sPassword);

        try {
            int worAffected = uService.update(userExist);
            if (worAffected > 0) {
                ((SupplierProfile) currentForm).showSuccess(worAffected);
            } else {
                ((SupplierProfile) currentForm).showSuccess(worAffected);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Sign Up method

    public void signUPData(String fullName, int city, String email, String address, String password, String company, long phone, long userType) throws SQLException {

        UserService uService = new UserService();
        beanspkg.User item = new beanspkg.User(fullName, email, city, address, password, company, phone, userType);
        System.out.println(userType);
        System.out.println(item.getUserType());
        uService.insert(item);
        JOptionPane.showMessageDialog(null, "Your registration done successfully! ");

    }
    // Inserting new Category to database

    public void addCategory(String description) throws SQLException {

        CategoryService catService = new CategoryService();
        beanspkg.CategoryBean item = new beanspkg.CategoryBean(description);
        catService.insert(item);

    }
    //Deleting category from database

    public void deleteCategory(int catID) throws SQLException {

        CategoryService catService = new CategoryService();
        beanspkg.CategoryBean item = new beanspkg.CategoryBean(catID);
        catService.delete(catID);
    }

    //Retrieving categories from database
    public ResultSet viewCategory() throws SQLException {

        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT cat_id as ID, cat_desc as Description FROM `category`");
        return rs;
    }
    //Retrieving products from database

    public ResultSet viewProTable() throws SQLException {

        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_id as ID, prod_size as Size, prod_condition as ProCondition,  in_stock as Quantity, photo as Image,  price as Price,  off_sale as Sale, cat_id as Category,  prod_name as Name,  prod_color as Color  FROM `scart`.`product`");
        return rs;
    }
    //Updating categories

    public void updateCategory(int catID, String desc) throws SQLException {

        CategoryService catService = new CategoryService();
        beanspkg.CategoryBean item = new beanspkg.CategoryBean(catID, desc);
        catService.update(item);
    }

    //Inserting new product
    public void addProductData(int size, int prodCondition, int quantity, int price, int sale, int id, long category, String name, String color) throws SQLException {

        id = (int) userExist.getUserId();
        ProductService prodService = new ProductService();
        beanspkg.Product item = new beanspkg.Product(size, prodCondition, quantity, price, sale, id, category, name, color);
        prodService.insert(item);

    }

    //Deleting product
    public void DeleteProductData(int proID) throws SQLException {

        ProductService prodService = new ProductService();
        beanspkg.Product item = new beanspkg.Product(proID);
        prodService.delete(proID);

    }
    //Updating product

    public void updateProdTable(int proID, int size, int prodCondition, int quantity, Blob image, int price, int sale, int id, long category, String name, String color) throws SQLException {

        ProductService prodService = new ProductService();
        beanspkg.Product item = new beanspkg.Product(proID, size, prodCondition, quantity, image, price, sale, id, category, name, color);
        prodService.update(item);
    }
 

    //Getting category ComboBox items IDs
    public long getCategory(String catName) throws SQLException {
        CategoryService categoryService = new CategoryService();
        beanspkg.CategoryBean item = new beanspkg.CategoryBean();
        item = categoryService.selectCat(catName);

        return item.getCatId();
    }
    //Getting category ComboBox items Names

    public String getCategoryName(int catID) throws SQLException {
        CategoryService categoryService = new CategoryService();
        beanspkg.Category item = new beanspkg.Category();
        item = categoryService.selectOne(catID);

        return item.getCatDesc();
    }
}