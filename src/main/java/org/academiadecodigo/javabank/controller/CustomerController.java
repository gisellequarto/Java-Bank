package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.dto.ConverterDto;
import org.academiadecodigo.javabank.dto.CustomerDto;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private ConverterDto converterDto;
    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setConverterDto(ConverterDto converterDto) {
        this.converterDto = converterDto;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {

        List<CustomerDto> customerDtoList = customerService.list().stream()
                .map(c -> converterDto.converterCustomer(c)).collect(Collectors.toList());

        model.addAttribute("customers", customerDtoList);
        return "customer/list";
    }

    /**
     * Renders a view with customer details
     *
     * @param id    the customer id
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {

        Customer customer = customerService.get(id);
        CustomerDto customerDto = converterDto.converterCustomer(customer);

        model.addAttribute("customer", customerDto);
        model.addAttribute("accounts" , customer.getAccounts());
        model.addAttribute("recipients", customerService.listRecipients(id));
        return "customer/show";
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Customer #id" + id + " deleted!" );

        return "redirect:/customer/list";
    }

    /**
     * Deletes a recipient from a customer
     *
     * @param cid the customer id
     * @param rid the recipient id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipient/{rid}/delete/")
    public String deleteRecipient(@PathVariable Integer cid, @PathVariable Integer rid) {
        customerService.removeRecipient(cid, rid);
        return "redirect:/customer/" + cid;
    }

    @RequestMapping(method = RequestMethod.GET, path = "form/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", converterDto.converterCustomer(customerService.get(id)));
        return "customer/form";
    }


    @RequestMapping(method = RequestMethod.GET, path = "form/")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "customer/form";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customer")
    public String saveOrUpdateCustomer(@ModelAttribute CustomerDto customerDto, RedirectAttributes redirectAttributes) {
        /*
        if (customerDto.getId() == null) {
            redirectAttributes.addFlashAttribute("lastAction", "Added customer successfully!");
        } else {
            redirectAttributes.addFlashAttribute("lastAction", "Customer successfully edited");
        }*/

        Customer customer = converterDto.converterCustomerDto(customerDto);
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("lastAction", "Operation Successfully!");

        return "redirect:/customer/list";
    }


}
