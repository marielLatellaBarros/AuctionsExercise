package be.pxl.auctions.dao.impl;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	private EntityManager entityManager;

	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public User saveUser(User user) {
		LOGGER.info("Saving user [" + user.getEmail() + "]");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(user);
		transaction.commit();
		return user;
	}

	public User findUserByEmail(String email) {
		TypedQuery<User> userQuery = entityManager.createNamedQuery("findUserByEmail", User.class);
		userQuery.setParameter("email", email);
		try {
			return userQuery.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("No user found with email [" + email + "]");
			return null;
		}
	}

	public User findUserById(long userId) {
		return entityManager.find(User.class, userId);
	}

	public List<User> findAllUsers() {
		TypedQuery<User> findAllQuery = entityManager.createNamedQuery("findAllUsers", User.class);
		return findAllQuery.getResultList();
	}
}
