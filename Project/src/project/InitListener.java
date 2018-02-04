package project;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import project.dao.ConnectionManager;


public class InitListener implements ServletContextListener {
	 @Override
	    public void contextInitialized(ServletContextEvent servletContextEvent) {
	        System.out.println("inicijalizacija...");
	        ConnectionManager.open();
	        System.out.println("završeno!");
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	        ConnectionManager.close();
	    }	
}
