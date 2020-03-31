package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.dao.impl.UserDaoImpl;
import be.pxl.auctions.model.User;
import be.pxl.auctions.util.EmailValidator;
import be.pxl.auctions.util.EntityManagerUtil;
import be.pxl.auctions.util.exception.DuplicateEmailException;
import be.pxl.auctions.util.exception.InvalidDateException;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.RequiredFieldException;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class UserService {
	private UserDao userDao;

	public UserService() {
		EntityManager entityManager = EntityManagerUtil.createEntityManager();
		userDao = new UserDaoImpl(entityManager);
	}

	public List<User> getAllUsers() {
		return userDao.findAllUsers();
	}

	public User getUserById(long userId) {
		return userDao.findUserById(userId);
	}

	public User createUser(User user) throws RequiredFieldException, InvalidEmailException, DuplicateEmailException, InvalidDateException {
		if (StringUtils.isBlank(user.getFirstName())) {
			throw new RequiredFieldException("FirstName");
		}
		if (StringUtils.isBlank(user.getLastName())) {
			throw new RequiredFieldException("LastName");
		}
		if (StringUtils.isBlank(user.getEmail())) {
			throw new RequiredFieldException("Email");
		}
		if (!EmailValidator.isValid(user.getEmail())) {
			throw new InvalidEmailException(user.getEmail());
		}
		if (user.getDateOfBirth() == null) {
			throw new RequiredFieldException("DateOfBirth");
		}
		if (user.getDateOfBirth().isAfter(LocalDate.now())) {
			throw new InvalidDateException("DateOfBirth cannot be in the future.");
		}
		User existingUser = userDao.findUserByEmail(user.getEmail());
		if (existingUser != null) {
			throw new DuplicateEmailException(user.getEmail());
		}
		return userDao.saveUser(user);
	}

}
