package ar.edu.unlp.lifia.ratatoullie.modelTest;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.unlp.lifia.ratatoullie.model.*;
public class TestCategory {

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
	public void updateNeutraltoPopular(){
		Vote v = Vote.POSITIVE;
		Restaurant rest= Ratatoullie.getInstance().getRestaurant("FastFood");
		Menu menu =rest.addMenu("Asado", "Falda, chorizo, ensalada, etc...", new Date().getTime(), null);
		Popular popular = new Popular();
		for (int i=1; i<=11; i++) {
			user2.comment("Bueno"+i, new Date().getTime(), menu, v);
		}
		Assert.assertTrue(rest.getCategory().getClass().equals(popular.getClass()));
	}
	
	@Test
	public void updatePopulartoNeutral(){
		//Set Restaurant as Popular
		Vote v = Vote.POSITIVE;
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("respU","apellido", "respU@mail.com", "pass", new Location(2, 3));
		userResponsible.addRestaurant("Joe's", new Date().getTime(), new Location(2, 3), null);
		Restaurant rest= Ratatoullie.getInstance().getRestaurant("FastFood");
		for (int i=1; i<=11; i++) {
			user2.comment("Bueno"+i, new Date().getTime(), rest, v);
		}
		
		//Check whether Restaurant is Neutral
		for (int i=1; i<=11; i++) {			
			user2.comment("Malo"+i, new Date().getTime(), rest, Vote.NEGATIVE);
		}
		Neutral neutral = new Neutral();
		Assert.assertTrue(rest.getCategory().getClass().equals(neutral.getClass()));
	}
	
	@Test
	public void updateNeutraltoNotPopular(){
		Vote v = Vote.NEGATIVE;
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("rUser", "apellido", "rUser@mail.com", "pass",new Location(2, 3));
		userResponsible.addRestaurant("Moe's", new Date().getTime(), new Location(2, 3), null);
		Restaurant rest= Ratatoullie.getInstance().getRestaurant("Moe's");
		NotPopular notPopular = new NotPopular();
		for (int i=1; i<=11; i++) {
			user2.comment("Malo"+i, new Date().getTime(), rest, v);
		}
		Assert.assertTrue(rest.getCategory().getClass().equals(notPopular.getClass()));
	}
	
	@Test
	public void updateNotPopulartoNeutral(){
		//Set Restaurant as NotPopular
		Vote v = Vote.NEGATIVE;
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("respUser","apellido", "mail@mail.com", "pass", new Location(2, 3));
		userResponsible.addRestaurant("Moe's", new Date().getTime(), new Location(2, 3), null);
		Restaurant rest= Ratatoullie.getInstance().getRestaurant("Moe's");
		for (int i=1; i<=11; i++) {
			user2.comment("Malo"+i, new Date().getTime(), rest, v);
		}
		//check whether Restaurant is Neutral
		for (int i=1; i<=11; i++) {
			user2.comment("Bueno"+i, new Date().getTime(), rest, Vote.POSITIVE);
		}
		Neutral neutral = new Neutral();
		Assert.assertTrue(rest.getCategory().getClass().equals(neutral.getClass()));
	}
}
