package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class UserServiceGetUserByIdTest {
	private static final long USER_ID = 5l;

	@Mock
	private UserDao userDao;
	@InjectMocks
	private UserService userService;
	private User user;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setFirstName("Mark");
		user.setLastName("Zuckerberg");
		user.setDateOfBirth(LocalDate.of(1989, 5, 3));
		user.setEmail("mark@facebook.com");
	}

	@Test
	public void returnsNullWhenNoUserWithGivenIdFound() {
		when(userDao.findUserById(USER_ID)).thenReturn(null);
		assertNull(userService.getUserById(USER_ID));
	}

	@Test
	public void returnsUserWhenUserFoundWithGivenId() {
		when(userDao.findUserById(USER_ID)).thenReturn(user);
		assertEquals(user, userService.getUserById(USER_ID));
	}
}
