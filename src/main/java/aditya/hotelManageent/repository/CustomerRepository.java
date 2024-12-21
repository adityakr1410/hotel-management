package aditya.hotelManageent.repository;


import aditya.hotelManageent.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
