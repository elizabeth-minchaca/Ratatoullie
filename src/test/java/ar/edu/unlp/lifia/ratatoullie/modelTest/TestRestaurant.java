package ar.edu.unlp.lifia.ratatoullie.modelTest;

import java.util.Date;

import org.junit.Test;

import ar.edu.unlp.lifia.ratatoullie.model.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestRestaurant {

	static Ratatoullie system= Ratatoullie.getInstance();
	User user1;
	User user2;
	User user3;
	User user4;
	
	
	public static Ratatoullie getRatatoullie() {
		return system;
	}

	public void setRatatoullie(Ratatoullie ratatoullie) {
		system = ratatoullie;
	}
	
	@BeforeClass
	public static void beforeClass(){
		getRatatoullie().addUser("User1","apellido", "User1@mail.com", "pass", new Location(2, 3));
		getRatatoullie().addUser("User2","apellido", "User2@mail.com", "pass", new Location(2, 3));
		getRatatoullie().addUser("User3","apellido", "User3@mail.com", "pass", new Location(2, 3));
		getRatatoullie().addUser("User4","apellido", "User4@mail.com", "pass", new Location(2, 3));
		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("resUser","apellido", "resUser@mail.com", "pass", new Location(2, 3));
		userResponsible.addRestaurant("FastFood", new Date().getTime(), new Location(2, 3), null);
		
	}
	@Before
	public void before(){
		user1= getRatatoullie().getUser("User1@mail.com");
		user2= getRatatoullie().getUser("User2@mail.com");
		user3= getRatatoullie().getUser("User3@mail.com");
		user4= getRatatoullie().getUser("User4@mail.com");	
	}

	@Test
	public void getCurrentMenu(){
		Restaurant resto= Ratatoullie.getInstance().getRestaurant("FastFood");		
		resto.addMenu("Locro", "zapallo, poroto, papas.", new Date().getTime(), null);
		resto.addMenu("Asado", "Falda, chorizo, ensalada, etc...", new Date().getTime(), null);
		resto.addMenu("Ñoquis", "Queso, papa, etc...", new Date().getTime(), null);
		Assert.assertTrue(resto.getCourentMenu().getText().equals("Ñoquis"));
	}

}
