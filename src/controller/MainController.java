/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beanspkg.Order;
import beanspkg.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import servicespkg.OrderService;
import servicespkg.UserService;
import view.DeliveryOrder;
import view.DeliveryProfile;
import view.Home;
import view.Login;
import view.ProductDetails;
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

    public MainController() {
        Utility.themeLookAndFeel();
        currentForm = new Home(this);
        currentForm.setVisible(true);
        Utility.centerFrame(currentForm);

    }

    //Home buttons
    public void browseProducts() {
        currentForm.setVisible(false);
        currentForm = new ViewProducts();
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

    public ResultSet showRequiredOrders() {
        OrderService orderService = new OrderService();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = null;
        try {
            rs = orderService.selectDataAsRS();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void login(String name, String password) {
        try {
            dto.Login logindata = new dto.Login(name, password); //DTO "Data Transfer Object"
            UserService userService = new UserService();
            userExist = userService.login(logindata);
            if (userExist.getUserId() > 0) {
                currentForm.setVisible(false); //Login hide
                currentForm = checkUserType(userExist.getUserType()); // instantiate object of "Edit the comment to describe"
                currentForm.setVisible(true); // Show Welcome form
            } else {
                ((Login) currentForm).showErrorDialog("This is not a valid cresentials .. !");
            }
        } catch (SQLException ex) {
            ((Login) currentForm).showErrorDialog("Can not connect to DB right now, please call 8008280 for help.. !");
        }
    }

    public JFrame checkUserType(int type) {
        switch (type) {
            case 1:
                //currentForm = new AdminProfile(this);
                break;
            case 2:
               // currentForm = new SupplierProfile(this);
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
        String[] data= new String[3];
        data[0] = userExist.getFullName();
        data[1] = userExist.getEmail();
        data[2] = userExist.getPhone();
        return data;
    }

    //Update data from form to DB

    public void updateData(String t1, String t2, String t3, String t4) {
        UserService uService = new UserService();
        System.out.println("UserExist" + userExist.getUserId());
        userExist.setFullName(t1);
        userExist.setEmail(t2);
        userExist.setPhone(t3);
        userExist.setPassword(t4);
        try {
            int worAffected = uService.update(userExist);
            if (worAffected > 0) {
                ((DeliveryProfile) currentForm).showSuccess("Data updated successfully");
            } else {
                ((DeliveryProfile) currentForm).showSuccess("Error updating data");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /*
    //Load Data to table
    public ResultSet loadOrdersToTable(){
        OrderService o= new OrderService();
        // DefaultTableModel model = (DefaultTableModel) jTable.getModel();
         ResultSet rs = null;
        try {
            rs = o.selectDataAsRS();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
         //jTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
    }
*/
    /*
     public void buttonClicked(String className) {
     try {
     Class <?> type = Class.forName(className);
     currentForm.setVisible(false);
     currentForm = new type(this);
     currentForm.setVisible(true);
     } catch (ClassNotFoundException ex) {
     Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     }
     */
}
/*  public void ViewProducts1(JTable table) {
 table=new JTable();
 ProductService productService = new ProductService();
 try {
 beanspkg.Product[] productsExist = productService.selectAll();
 DefaultTableModel model = (DefaultTableModel) table.getModel();

 for (Product product : productsExist) {
 prodid.add(product.getProdId());
 prodName.add(product.getProdName());

 }

 Object[] arr = prodid.toArray();
 Object[] names = prodName.toArray();

 model.addColumn("id", arr);
 //model.addRow(arr);
 model.addColumn("Name", names);
 } catch (SQLException ex) {
 Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
 }
        
 }
 */
    // add more methods .. 

