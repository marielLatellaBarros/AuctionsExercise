package be.pxl.auctions.dao.impl;

import be.pxl.auctions.dao.impl.util.DaoTest;
import be.pxl.auctions.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoImplTest extends DaoTest {

	private UserDaoImpl userDao = new UserDaoImpl(em);

	@Test
	public void userCanBeSavedAndRetrievedById() {
		User user = new User();
		user.setFirstName("Mark");
		user.setLastName("Zuckerberg");
		user.setDateOfBirth(LocalDate.of(1989, 5, 3));
		user.setEmail("mark@facebook.com");
		long newUserId = userDao.saveUser(user).getId();
		clear();

		User retrievedUser = userDao.findUserById(newUserId);
		assertNotNull(retrievedUser);
		assertEquals(user.getFirstName(), retrievedUser.getFirstName());
		assertEquals(user.getLastName(), retrievedUser.getLastName());
		assertEquals(user.getEmail(), retrievedUser.getEmail());
		assertEquals(user.getDateOfBirth(), retrievedUser.getDateOfBirth());
	}

	@Test
	public void userCanBeSavedAndRetrievedByEmail() {
		// TODO implement this test
	}

	@Test
	public void returnsNullWhenNoUserFoundWithGivenEmail() {
		// TODO implement this test
	}

	@Test
	public void allUsersCanBeRetrieved() {
		// TODO implement this test
		// create and save one user

		// retrieve all users
		// make sure there is at least 1 user in the list
		// make sure the newly created user is in the list (e.g. test if a user with this email address is in the list)
	}


}
