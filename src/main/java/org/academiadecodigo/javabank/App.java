package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.persistence.JpaBootstrap;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

     //   JpaBootstrap jpa = new JpaBootstrap();
       // EntityManagerFactory emf = jpa.start();

        App app = new App();
        app.bootStrap();

//        jpa.stop();

    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new JpaAccountService());
        bootstrap.setCustomerService(new JpaCustomerService());

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
