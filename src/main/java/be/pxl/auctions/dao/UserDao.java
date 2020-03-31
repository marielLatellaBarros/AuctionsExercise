package be.pxl.auctions.dao;

import be.pxl.auctions.model.User;

import java.util.List;

public interface UserDao {
	User saveUser(User user);
	User findUserByEmail(String email);
	User findUserById(long userId);
	List<User> findAllUsers();
}
