package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringConfig {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/META-INF/spring-init.xml");

        Controller controller = context.getBean("loginController", LoginController.class);

        controller.init();


    }

}
