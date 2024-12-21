package aditya.hotelManageent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aditya.hotelManageent.model.*;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}