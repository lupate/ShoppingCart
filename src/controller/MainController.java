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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servicespkg.CategoryService;
import servicespkg.OrderService;
import servicespkg.ProductService;
import servicespkg.UserRateProductService;
import servicespkg.UserService;
import view.AddDeliveryBoy;
import view.AddSupplier;
import view.AdminOrders;
import view.AdminProductDetails;
import view.AdminProfile;
import view.AdminViewProducts;
import view.DeliveryOrder;
import view.DeliveryProfile;
import view.Home;
import view.Login;
import view.ProductDetails;
import view.UserOrder;
import view.UserProfile;
import view.Utility;
import view.ViewProducts;

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
                ((Login) currentForm).showErrorDialog("This is not a valid cresentials .. !");
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
                 //currentForm = new SupplierProfile(this);
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

    //Load Profile data  WRONG !!   "Edited"
    public String[] loadDataToForm() {
        String[] data = new String[3];
        data[0] = userExist.getFullName();
        data[1] = userExist.getEmail();
        //data[2] = userExist.getPhone();
        return data;
    }

    //Update data from delivery form to DB
    public void updateData(String t1, String t2, String t3, String t4) {
        UserService uService = new UserService();
        userExist.setFullName(t1);
        userExist.setEmail(t2);
        //userExist.setPhone(t3);
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
           //order.setOrderId(order_id);
           //order.setUserId(userExist.getUserId());
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
//    public int getUserID() {
//        return userExist.getUserId();
//    }

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
        //order.setUserId(userExist.getUserId());
        order.setAddress(userExist.getAddress());
        order.setStatus(0);
        try {
            orderService.insert(order);
        } catch (SQLException ex) {
            System.out.println("Check your DB server");
        }
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
    //

//    //select one product for admin
//    long prod_id;
//    //getter
//    public long getProd_id() {
//        return prod_id;
//    }
//    //setter
//    public void setProd_id(long prod_id) {
//        this.prod_id = prod_id;
//
//    }
    //get one product data
//    public ResultSet getOneProduct(long prod_id) {
//        ProductService productservice = new ProductService();
//        ResultSet rs = null;
//        try {
//            rs = productservice.selectOneProduct(prod_id);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(currentForm,"failed to get product data");
//        }
//        return rs;
//    }
    ////////

   // beanspkg.User userExist = new User();
    // log in method
//    public void login(String name, String password) {
//        try {
//            dto.Login logindata = new dto.Login(name, password); //DTO "Data Transfer Object"
//            UserService userService = new UserService();
//            //beanspkg.User userExist = userService.login(logindata);
//            userExist = userService.login(logindata);//get all user data if userExist
//            if (userExist.getUserId() > 0) {
//                currentForm.setVisible(false); //Login hide
//                currentForm = checkUserType(userExist.getUserType()); // main form for each user
//                currentForm.setVisible(true); // display it
//            } else {
//                ((Login) currentForm).showErrorDialog("This is not a valid cresentials .. !");
//
//            }
//        } catch (Exception ex) {
//            ((Login) currentForm).showErrorDialog("Can not connect to DB right now, please call 8008280 for help.. !");
//
//        }
//    }
    
//    public JFrame checkUserType(int type) {
//        switch (type) {
//            case 1: {
//                currentForm = new AdminViewProducts(this);
//                break;
//            }
//            case 2:
//                //currentForm = new SupplierProfile(this);
//                break;
//            case 3:
//                //currentForm = new UserProfile(this);
//                break;
//            case 4:
//                //currentForm = new DeliveryProfile(this);
//                break;
//            default:
//                currentForm = new Home(this);
//        }
//        return currentForm;
//    }
// end of maincontroller 
}
