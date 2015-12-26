/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import javax.swing.JFrame;
import servicespkg.UserService;
import view.Login;
import view.Welcome;

/**
 *
 * @author Muhammad Lupate
 */
public class MainController {

    JFrame currentForm;

    public MainController() {
        currentForm = (Login) new Login(this);
        currentForm.setVisible(true);
    }

    public void login(String name, String password) {
        try {
            dto.Login logindata = new dto.Login(name, password); //DTO "Data Transfer Object"
            UserService userService = new UserService();
            beanspkg.User userExist = userService.login(logindata);
            if (userExist.getUserId() > 0) {
                currentForm.setVisible(false); //Login hide
                currentForm = new Welcome(userExist.getFullName()); // instantiate object of WELCOME
                currentForm.setVisible(true); // Show Welcome form
            } else {
                ((Login) currentForm).showErrorDialog("This is not a valid cresentials .. !");
            }
        } catch (SQLException ex) {
            ((Login) currentForm).showErrorDialog("Can not connect to DB right now, please call 8008280 for help.. !");
        }
    }
}
// add more methods .. 