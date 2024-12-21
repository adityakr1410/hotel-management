package aditya.hotelManageent.controller;

import aditya.hotelManageent.model.Customer;
import aditya.hotelManageent.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    public String customer(Model model){
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList",customerList);


        return "allCustomers";
    }


    @GetMapping("/addNewCustomer")
    public String addNewCustomer(Model model)
    {

        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "addNewCustomer";
    }

    @PostMapping("/saveCustomer")
    public String save(@ModelAttribute("customer") Customer customer,
                       Model model,
                       RedirectAttributes redirectAttributes){
        customerService.save(customer);
        redirectAttributes.addAttribute(
                "message",
                "Customer created successfully"
        );
        return "redirect:/allCustomers";
    }

    @GetMapping("customer/{id}/edit")
    public String editCustomer(@PathVariable long id, Model model){

        Customer customer = customerService.findById(id).orElse(null);
        model.addAttribute("customer",customer);
        return "addNewCustomer";
    }

    @GetMapping("customer/{id}/delete")
    public String deleteCustomer(@PathVariable long id, Model model){
        customerService.deleteById(id);

        return "redirect:/allCustomers";
    }

    @GetMapping("customer/{id}")
    public String customerData(@PathVariable long id, Model model){
        customerService.findById(id)
                .ifPresent(customer -> model.addAttribute("customer",customer));

        return "customerDetails";
    }

}
