package aditya.hotelManageent.service;


import aditya.hotelManageent.model.Booking;
import aditya.hotelManageent.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings(){
        return (List<Booking>) bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id){
        return bookingRepository.findById(id);
    }

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }

    public void deleteById(Long id){
        bookingRepository.deleteById(id);
    }
}
