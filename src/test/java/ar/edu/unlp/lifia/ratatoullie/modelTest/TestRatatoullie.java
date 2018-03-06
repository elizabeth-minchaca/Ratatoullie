package ar.edu.unlp.lifia.ratatoullie.modelTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.unlp.lifia.ratatoullie.model.Commensal;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;
import ar.edu.unlp.lifia.ratatoullie.model.Gourmet;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Ranking;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;
import ar.edu.unlp.lifia.ratatoullie.model.Visitor;
import ar.edu.unlp.lifia.ratatoullie.model.Vote;

public class TestRatatoullie {
	private static Ratatoullie ratatoullie= Ratatoullie.getInstance();
	public static Ratatoullie getRatatoullie() {
		return ratatoullie;
	}
	public static void setRatatoullie(Ratatoullie ratatoullie) {
		TestRatatoullie.ratatoullie = ratatoullie;
	}
	
	@BeforeClass
	public static void beforeClass(){
		getRatatoullie().addUser("User1", "apellido", "User1@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User2","apellido", "User2@mail.com", "pass", new Location(2, 3));
		getRatatoullie().addUser("User3", "apellido", "User3@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User4", "apellido", "User4@mail.com", "pass",new Location(2, 3));		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("resUser", "apellido", "resUser@mail.com", "pass",new Location(2, 3));
		userResponsible.addRestaurant("FastFood", new Date().getTime(), new Location(2, 3), null);
		userResponsible.addRestaurant("FastFood2", new Date().getTime(), new Location(2, 3.4), null);		
	}
	
	
	/**
	 * Number of {@link User} per {@link Ranking}
	 */
	@Test
	public void quantityUsersRank(){
		Map<Ranking, Integer> ranks = getRatatoullie().getQuantityUsersRank();
		Assert.assertTrue(ranks.get(new Visitor())==5);
		Assert.assertTrue(ranks.get(new Commensal())==0);
		Assert.assertTrue(ranks.get(new Gourmet())==0);		
	}	
	@Test
	public void getRestaurantOK(){
		Restaurant restaurant = getRatatoullie().getRestaurant("FastFood");
		Assert.assertTrue(restaurant.getName().equals("FastFood"));
		Assert.assertTrue(restaurant.getOwner().getMail().equals("resUser@mail.com"));
	}
	@Test
	public void getRestaurantFailed(){
		Restaurant restaurant = getRatatoullie().getRestaurant("Rest");
		Assert.assertNull(restaurant);
	}
	@Test	
	public void addUserOK(){
		User userNUll= getRatatoullie().getUser("UserOk@mail.com");
		User user=getRatatoullie().addUser("UserOk", "apellido", "UserOk@mail.com", "pass",new Location(2, 2));
		User user2= getRatatoullie().getUser("UserOk@mail.com");
		Assert.assertEquals(user,user2);
		Assert.assertNull(userNUll);
	}
	@Test	
	public void addUserFailed(){
		User user=getRatatoullie().addUser("User1", "apellido", "User1@mail.com", "pass",new Location(2, 2));
		Assert.assertNull(user);
	}
	@Test
	public void addUserResponsible(){
		UserResponsible userNUll= (UserResponsible)getRatatoullie().getUser("UserResponsible@mail.com");
		UserResponsible user=getRatatoullie().addUserResponsible("UserResponsible", "apellido", "UserResponsible@mail.com", "pass",new Location(2, 2));
		UserResponsible user2=(UserResponsible) getRatatoullie().getUser("UserResponsible@mail.com");
		Assert.assertEquals(user,user2);
		Assert.assertNull(userNUll);
	}
	@Test
	public void addUserResponsibleFailed(){
		UserResponsible user=getRatatoullie().addUserResponsible("resUser", "apellido", "resUser@mail.com", "pass",new Location(2, 2));
		Assert.assertNull(user);
	}
	@Test
	public void getUserOK(){
		User user= getRatatoullie().getUser("User4@mail.com");
		Assert.assertEquals(user.getMail(), "User4@mail.com");
	}
	@Test
	public void getUserFailed(){
		User user= getRatatoullie().getUser("Juan");
		Assert.assertNull(user);
	}
	/**
	 * Busqueda de {@link Restaurant} a una distancia en Kilometros desde una {@link Location}
	 */
	@Test
	public void searchRestaurantLocationDistance() {
		UserResponsible userResponsible=(UserResponsible) getRatatoullie().getUser("resUser@mail.com");
		Restaurant restaurantFastFood= getRatatoullie().getRestaurant("FastFood");
		Restaurant restaurant=userResponsible.addRestaurant("RestaurantLocation", new Date().getTime(), new Location(2, 2.9), null);
		Restaurant restaurant2=userResponsible.addRestaurant("RestaurantLocation2", new Date().getTime(), new Location(2, 3.01), null);
		Restaurant restaurant3=userResponsible.addRestaurant("RestaurantLocation3", new Date().getTime(), new Location(2.3, 3.26), null);
		List<Restaurant> restaurants =getRatatoullie().search(new Location(2.0023 , 3),1000);
		List<Restaurant> restaurants2 =getRatatoullie().search(new Location(2.0023 , 3),10000);
		List<Restaurant> restaurants3 =getRatatoullie().search(new Location(2.0023 , 3),100000);
		Assert.assertTrue(restaurants.contains(restaurantFastFood));
		Assert.assertTrue(restaurants.size()==1);
		Assert.assertTrue(restaurants2.contains(restaurantFastFood));
		Assert.assertTrue(restaurants2.contains(restaurant2));
		Assert.assertTrue(restaurants2.size()==2);
		Assert.assertTrue(restaurants3.contains(restaurant3));
		Assert.assertTrue(restaurants3.contains(restaurant));
		Assert.assertTrue(restaurants3.size()==5);		
	}
	/**
	 * Lists the {@link Restaurant} with a specific number of {@link Vote}
	 */
	@Test
	public void searchRestaurantVote(){
		List<Restaurant> restaurantsNegative = getRatatoullie().search(Vote.NEGATIVE, 0);
		List<Restaurant> restaurantsPositive = getRatatoullie().search(Vote.POSITIVE, 0);
		List<Restaurant> restaurantsNeutral = getRatatoullie().search(Vote.NEUTRAL, 0);
		Assert.assertTrue(restaurantsNegative.size()>=2);
		Assert.assertTrue(restaurantsPositive.size()>=2);
		Assert.assertTrue(restaurantsNeutral.size()>=2);
		User user = getRatatoullie().getUser("User3@mail.com");
		Restaurant restaurant=getRatatoullie().getRestaurant("FastFood");
		Restaurant restaurant2=getRatatoullie().getRestaurant("RestaurantLocation");		
		user.comment("ComemntPositive", new Date().getTime(), restaurant, Vote.POSITIVE);
		user.comment("ComemntPositive2", new Date().getTime(), restaurant2, Vote.POSITIVE);
		user.comment("ComemntNegative", new Date().getTime(), restaurant, Vote.NEGATIVE);
		user.comment("ComemntNeutral", new Date().getTime(), restaurant, Vote.NEUTRAL);
		restaurantsNegative = getRatatoullie().search(Vote.NEGATIVE, 1);
		restaurantsPositive = getRatatoullie().search(Vote.POSITIVE, 1);
		restaurantsNeutral = getRatatoullie().search(Vote.NEUTRAL, 1);
		Assert.assertTrue(restaurantsNegative.size()>=1);
		Assert.assertTrue(restaurantsPositive.size()>=2);
		Assert.assertTrue(restaurantsNeutral.size()>=1);		
	}
	/**
	 * Lists {@link User} with the most {@link Comment}, limiting the number of results.
	 */
	@Test
	public void topUsersComments(){		
		User user = getRatatoullie().getUser("User4@mail.com");
		Restaurant restaurant=getRatatoullie().getRestaurant("FastFood");		
		user.comment("ComemntPositive", new Date().getTime(), restaurant, Vote.POSITIVE);
		user.comment("ComemntPositive2", new Date().getTime(), restaurant, Vote.POSITIVE);
		user.comment("ComemntNegative", new Date().getTime(), restaurant, Vote.NEGATIVE);
		user.comment("ComemntNeutral", new Date().getTime(), restaurant, Vote.NEUTRAL);
		List<User> users = getRatatoullie().getTopUsersComments(4);
		Assert.assertTrue(users.size()<=4);
		Assert.assertEquals(users.get(0), user);
		Assert.assertEquals(users.get(1).getMail(), "User1@mail.com");
		Assert.assertEquals(users.get(2).getMail(), "User2@mail.com");
		Assert.assertEquals(users.get(3).getMail(), "User3@mail.com");		
		User user2 = getRatatoullie().getUser("User2@mail.com");		
		user2.comment("ComemntPositive", new Date().getTime(), restaurant, Vote.POSITIVE);
		user2.comment("ComemntPositive2", new Date().getTime(), restaurant, Vote.POSITIVE);
		user2.comment("ComemntPositive3", new Date().getTime(), restaurant, Vote.POSITIVE);
		user2.comment("ComemntPositive4", new Date().getTime(), restaurant, Vote.POSITIVE);
		user2.comment("ComemntNegative", new Date().getTime(), restaurant, Vote.NEGATIVE);
		user2.comment("ComemntNeutral", new Date().getTime(), restaurant, Vote.NEUTRAL);
		users = getRatatoullie().getTopUsersComments(3);
		Assert.assertTrue(users.size()<=3);
		Assert.assertEquals(users.get(0), user2);
		Assert.assertEquals(users.get(1), user);
		Assert.assertEquals(users.get(2).getMail(), "User1@mail.com");
	}
	/**
	 * {@link Restaurant} with more {@link Comment} within a date range, and limiting the number of results.
	 */
	@Test
	public void topCommentsRestaurant(){
		Restaurant restaurant=getRatatoullie().getRestaurant("FastFood");
		Restaurant restaurant2=getRatatoullie().getRestaurant("RestaurantLocation2");
		Restaurant restaurant3=getRatatoullie().getRestaurant("RestaurantLocation3");
		User user= getRatatoullie().getUser("User1@mail.com");
		long initialDate= new Date().getTime();
		for (int i = 0; i < 30; i++) {
			user.comment("Text"+i, new Date().getTime(), restaurant2, Vote.POSITIVE);
			user.comment("Text"+i, new Date().getTime(), restaurant3, Vote.NEGATIVE);
		}
		for (int i = 0; i < 30; i++) {
			user.comment("Text"+i, new Date().getTime(), restaurant3, Vote.POSITIVE);
		}
		long finalDate= new Date().getTime();
		
		for (int i = 0; i < 40; i++) {
			user.comment("Text"+i, finalDate +1, restaurant, Vote.POSITIVE);
		}
		List<Restaurant> restaurants= getRatatoullie().getRestaurantsTopComments(initialDate, finalDate, 2);
		Assert.assertTrue(restaurants.size()<=2);
		Assert.assertEquals(restaurants.get(0), restaurant2);
		Assert.assertEquals(restaurants.get(1), restaurant3);
		restaurants= getRatatoullie().getRestaurantsTopComments(initialDate, finalDate, 3);
		Assert.assertTrue(restaurants.size()<=2);
		Assert.assertEquals(restaurants.get(0), restaurant2);
		Assert.assertEquals(restaurants.get(1), restaurant3);
				
	}	
	/**
	 * Lists the {@link Restaurant} at a minimum distance from a set of {@link Location}
	 */
	@Test
	public void searchRestaurantLocations() {
		ArrayList<Location> locations= new ArrayList<Location>();
		locations.add(new Location(2.01,3.4));
		Set<Restaurant> restaurants = getRatatoullie().search(locations,30000);
		Assert.assertTrue(restaurants.size()==1);
		locations.add(new Location(2.11,3.1));
		restaurants = getRatatoullie().search(locations,30000);		
		Assert.assertTrue(restaurants.size()==5);
	}
}
