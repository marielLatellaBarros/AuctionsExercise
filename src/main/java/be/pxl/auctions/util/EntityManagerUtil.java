package be.pxl.auctions.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class EntityManagerUtil implements ServletContextListener {

	private static final Logger LOGGER = LogManager.getLogger(EntityManagerUtil.class);

	private static EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		emf = Persistence.createEntityManagerFactory("auctions-pu");
		LOGGER.debug("*** Persistence started at " + LocalDateTime.now());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (emf != null) {
			emf.close();
			LOGGER.error("*** Persistence finished at " + LocalDateTime.now());
		}
	}

	public static EntityManager createEntityManager() {
		if (emf != null) {
			return emf.createEntityManager();
		}
		return null;
	}

}
