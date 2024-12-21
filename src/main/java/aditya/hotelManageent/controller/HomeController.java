package aditya.hotelManageent.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import aditya.hotelManageent.model.*;
import aditya.hotelManageent.service.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping("/")
    public String home(@RequestParam(value = "name", defaultValue = "") String name,
            Model model) {
        String sayHello = "Hello " + name;
        model.addAttribute("message", sayHello);
        return "home";
    }

    @GetMapping("/regPage")
    public String openRegPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/regForm")
    public String submitRegForm(@ModelAttribute("user") User user, Model model) {
        boolean status = userService.registerUser(user);
        if (status) {
            model.addAttribute("successMsg", "user registered successfully");
        } else {
            model.addAttribute("errorMsg", "user not registered due to some error");
        }
        return "login";
    }

    @GetMapping("/loginPage")
    public String openLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loginForm")
    public String submitLoginForm(@ModelAttribute("user") User user, HttpSession session, Model model) {
        User validUser = userService.loginUser(user.getEmail(), user.getPassword());
        // if (validUser != null) {
        // session.setAttribute("user", validUser); // Set user in session
        // // List<Booking> list = BookingService.getAllBookings();
        // // model.addAttribute("BookingList", list);
        // return "redirect:/allBookings ";
        // } else {
        // model.addAttribute("errorMsg", "Email id and password didn't match!!");
        // return "login_error";
        // }
        return "redirect:/allBookings";
    }

}
