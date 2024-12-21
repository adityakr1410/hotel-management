package aditya.hotelManageent.service;

import java.util.List;

import aditya.hotelManageent.model.*;;
public interface UserService {
	public boolean registerUser(User user);
	public User loginUser(String email,String password);
	public List<User> getAllUser();
}
