package be.pxl.auctions.dao.impl.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DaoTest {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;

	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("auctions-pu-test");
		em = emf.createEntityManager();
	}

	protected void clear() {
		em.clear();
	}

	@AfterAll
	public static void tearDown() {
		if (em != null) {
			em.clear();
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

}
