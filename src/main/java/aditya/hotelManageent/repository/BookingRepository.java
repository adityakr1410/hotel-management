package aditya.hotelManageent.repository;


import aditya.hotelManageent.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
