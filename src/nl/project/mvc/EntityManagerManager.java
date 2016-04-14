package nl.project.mvc;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerManager implements ServletContextListener, ServletContainerInitializer {

	private static EntityManagerFactory emf; 
	
	private static EntityManagerFactory getEMF(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("teamapp");
		}
		return emf;
	}
	
	public static EntityManager getEntityManager(){
		return getEMF().createEntityManager();
	}
	
	@Override
	public void onStartup(Set<Class<?>> arg0, ServletContext arg1) throws ServletException {}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(emf != null){
			System.out.println("Closing EntityManagerFactory");
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
