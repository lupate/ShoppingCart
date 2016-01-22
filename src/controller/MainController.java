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
=======
        //order.setUserId(userExist.getUserId());
