package aditya.hotelManageent.controller;


import aditya.hotelManageent.model.Booking;
import aditya.hotelManageent.model.Customer;
import aditya.hotelManageent.model.Room;
import aditya.hotelManageent.service.BookingService;
import aditya.hotelManageent.service.CustomerService;
import aditya.hotelManageent.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, CustomerService customerService, RoomService roomService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @GetMapping("/allBookings")
    public String booking(Model model){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Booking> bookingList = bookingService.getAllBookings();

        List<BookingDto> bookingDtoList = new ArrayList<>();
        for(Booking bk: bookingList){
            BookingDto dto = new BookingDto();
            dto.id = bk.getId();
            dto.customer = bk.getCustomer();
            dto.room = bk.getRoom();
            dto.customerName = bk.getCustomer().getFirstName() +" "+ bk.getCustomer().getLastName();
            dto.roomNo = bk.getRoom().getId();
            dto.checkIn = bk.getCheckIn().format(formatter);
            dto.checkOut = (bk.getCheckOut()!= null)? bk.getCheckOut().format(formatter) :"Ongoing";
            bookingDtoList.add(dto);
        }

        model.addAttribute("bookingList",bookingDtoList);

        return "allBookings";
    }

    @GetMapping("/assignRoom/{id}")
    public String showAssignRoomPage(@PathVariable("id") Long roomId, Model model) {
        Room room = roomService.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID: " + roomId));

        List<Customer> customerList = customerService.getAllCustomers();

        model.addAttribute("room", room);
        model.addAttribute("customerList", customerList);

        return "assignRoom";
    }

    @PostMapping("/assignRoom")
    public String assignRoom(@RequestParam("id") Long roomId, @RequestParam("customerId") Long customerId,
                             RedirectAttributes redirectAttributes) {

        Room room = roomService.findById(roomId).orElseThrow(()-> new IllegalArgumentException("Invalid room id"));
        Customer customer = customerService.findById(customerId).orElseThrow(()-> new IllegalArgumentException("Invalid customer id"));

        if (room.getAssignedCustomer() != null) {
            redirectAttributes.addFlashAttribute("error", "Room is already assigned to another customer.");
            return "redirect:/allRooms";
        }
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setCheckIn(LocalDateTime.now());

        bookingService.save(booking);

        room.setAssignedCustomer(customer);
        room.setBookingId(booking);
        roomService.save(room);


        return "redirect:/allRooms";
    }

    @GetMapping("deassign/{id}")
    public String deassign(Model model,@PathVariable("id") Long bookingId){

        System.out.println("Its working continue the work to deassign "+ bookingId);

        Booking booking = bookingService.findById(bookingId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid booking ID" + bookingId));

        booking.setCheckOut(LocalDateTime.now());
        bookingService.save(booking);

        Room room = roomService.findById((booking.getRoom()).getId())
                .orElseThrow(()-> new IllegalArgumentException("Invalid Room ID "));
        room.setAssignedCustomer(null);
        room.setBookingId(null);
        roomService.save(room);

        return "redirect:/allRooms";
    }


}

class BookingDto {
    public long id;
    public Customer customer;
    public Room room;
    public String customerName;
    public long roomNo;
    public String checkIn;
    public String checkOut;
}
