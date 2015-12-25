/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import services.LoginService;
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
        model.Login logindata = new model.Login(name, password);
        LoginService loginService = new LoginService();
//        return loginService.checkUserExistence(logindata);
        int userExist = loginService.checkUserExistence(logindata);
        if (userExist > 0) {
            currentForm.setVisible(false); //Login hide
            currentForm = new Welcome(userExist); // instantiate object of WELCOME 
            currentForm.setVisible(true); // Show Welcome form
        } else {
            ((Login) currentForm).showErrorDialog("This is not a valid cresentials .. !");
        }

    }

}
