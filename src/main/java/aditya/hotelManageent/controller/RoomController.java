package aditya.hotelManageent.controller;


import aditya.hotelManageent.model.Customer;
import aditya.hotelManageent.model.Room;
import aditya.hotelManageent.service.CustomerService;
import aditya.hotelManageent.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final CustomerService customerService;

    public RoomController(RoomService roomService, CustomerService customerService) {
        this.roomService = roomService;
        this.customerService = customerService;
    }

    @GetMapping("/allRooms")
    public String room(Model model){
        List<Room> roomList = roomService.getAllRooms();
        model.addAttribute("roomList",roomList);

        return "allRooms";
    }

    @GetMapping("/addNewRoom")
    public String addNewRoom(Model model){
        Room room = new Room();
        model.addAttribute("room",room);

        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList",customerList);
        return "addNewRoom";
    }

    @PostMapping("/saveRoom")
    public String save(@ModelAttribute("room") Room room,
                       Model model,
                       RedirectAttributes redirectAttributes){
        roomService.save(room);
        redirectAttributes.addAttribute(
                "message",
                "Room created successfully"
        );
        return "redirect:/allRooms";
    }

    @GetMapping("/room/{id}/edit")
    public String editCustomer(@PathVariable long id, Model model){

        Room room = roomService.findById(id).orElse(null);
        model.addAttribute("room",room);
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList",customerList);
        return "addNewRoom";
    }




}
