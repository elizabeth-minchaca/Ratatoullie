package ar.edu.unlp.lifia.ratatoullie.modelTest;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.unlp.lifia.ratatoullie.model.*;

public class TestGeneral {

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

	
	@After
	public void after() {
		
	}
	
	@Test
	public void userFollow(){		
		user1.follow(user2);
		Assert.assertTrue(user1.getFollowings().contains(user2));
		Assert.assertTrue(user2.getFollowers().contains(user1));
	}
	
	@Test
	public void stopFollowing(){
		//added
		user1.follow(user2);
		user1.stopFollowing(user2);
		Assert.assertFalse(user1.getFollowings().contains(user2));
		Assert.assertFalse(user2.getFollowers().contains(user1));
	}
	
	@Test
	public void getFollowings(){
		user1.follow(user2);
		user1.follow(user3);
		user1.follow(user4);
		Assert.assertTrue(user1.getFollowings().contains(user2));
		Assert.assertTrue(user1.getFollowings().contains(user3));
		Assert.assertTrue(user1.getFollowings().contains(user4));
		
	}
	
	@Test
	public void recommend(){
		
		Restaurant restaurant= Ratatoullie.getInstance().getRestaurant("FastFood");
				Menu menu =restaurant.addMenu("Locro", "zapallo, poroto, papas.", new Date().getTime(), null);
		user1.recommend(menu, user2, "Bueno", new Date().getTime());
		Assert.assertFalse(user2.getRecommendations().isEmpty());
	}
	
	@Test
	public void recommendFollowers(){
		
		Restaurant restaurant= Ratatoullie.getInstance().getRestaurant("FastFood");
		Menu menu =restaurant.addMenu("Locro", "zapallo, poroto, papas.", new Date().getTime(), null);
		user2.follow(user1);
		user3.follow(user1);
		user4.follow(user1);
		user1.recommendFollowers(menu, "Bueno", new Date().getTime());
		Assert.assertFalse(user2.getRecommendations().isEmpty());
		Assert.assertFalse(user3.getRecommendations().isEmpty());
		Assert.assertFalse(user4.getRecommendations().isEmpty());
	}
	
	@Test
	public void userComment(){
		Vote v = Vote.POSITIVE;
		Restaurant restaurant= Ratatoullie.getInstance().getRestaurant("FastFood");
		Menu menu =restaurant.addMenu("Locro", "zapallo, poroto, papas.", new Date().getTime(), null);		
		Comment c = user1.comment("Malo", new Date().getTime(), menu, v);
		Assert.assertTrue(menu.getComments().contains(c));
		Assert.assertTrue(restaurant.getOwner().getCommentsRestaurant().contains(c));
	}
}