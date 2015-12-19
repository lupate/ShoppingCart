/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Login;
import services.LoginService;

/**
 *
 * @author Muhammad Lupate
 */
public class UserController {

    public UserController() {
    }

    public int login(String name, String password) {
        Login logindata = new Login(name, password);
        LoginService loginService = new LoginService();
        return loginService.checkUserExistence(logindata);
    }
}