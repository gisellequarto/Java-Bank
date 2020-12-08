package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.SpringCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SpringController {

    private SpringCustomerService springCustomerService;


    // Map the URL/Verb to the method
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String sayHello(Model model) {

        List<Customer> list = springCustomerService.getAllCustomers();


        // Add an attribute to the request
        model.addAttribute("customers", list);

        // Return the view name
        return "list";

    }

    @Autowired
    public void setSpringCustomerService(SpringCustomerService springCustomerService) {
        this.springCustomerService = springCustomerService;
    }

}