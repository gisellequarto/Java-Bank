package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.service.customer.CustomerService;

public class App {

    private Bank bank;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        CustomerService customerService = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(customerService);

        // start application
        loginController.init();
    }
}
