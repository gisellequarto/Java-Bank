package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class SpringController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Map the URL/Verb to the method
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String sayHello(Model model) {

        List<Customer> list = customerService.listCustomers();


        // Add an attribute to the request
        model.addAttribute("customers", list);

        // Return the view name
        return "list";
    }


}