package ar.edu.unlp.lifia.ratatoullie.modelTest;


import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.unlp.lifia.ratatoullie.model.*;

/**
 * 
 * @author Elizabeth
 *
 */
public class TestUserResponsible {

	static Ratatoullie system= Ratatoullie.getInstance();

	public static Ratatoullie getRatatoullie() {
		return system;
	}

	public void setRatatoullie(Ratatoullie ratatoullie) {
		system = ratatoullie;
	}

	@BeforeClass
	public static void beforeClass(){
		getRatatoullie().addUser("User1", "apellido", "User1@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User2", "apellido", "User2@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User3", "apellido", "User3@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User4", "apellido", "User4@mail.com", "pass",new Location(2, 3));
		
		getRatatoullie().addUser("User5", "apellido", "User5@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User6", "apellido", "User6@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User7", "apellido", "User7@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User8", "apellido", "User8@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User9", "apellido", "User9@mail.com", "pass",new Location(2, 3));
		getRatatoullie().addUser("User10", "apellido", "User10@mail.com", "pass",new Location(2, 3));


		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("resUser", "apellido", "resUser1@mail.com", "pass",new Location(2, 3));
		userResponsible.addRestaurant("Restaurante El Cuartito", new Date().getTime(), new Location(-34.903875, -58.000947), null);
		
		UserResponsible userResponsible2 =getRatatoullie().addUserResponsible("resUser2","apellido", "resUser2@mail.com", "pass", new Location(2, 6));
		userResponsible2.addRestaurant("FastFood", new Date().getTime(), new Location(-34.903009, -57.999962), null);
		userResponsible2.addRestaurant("Restaurante Las Violetas", new Date().getTime(), new Location(88, 70), null);

		UserResponsible userResponsible3 =getRatatoullie().addUserResponsible("resUser3", "apellido", "resUser3@mail.com", "pass",new Location(5, 9));
		userResponsible3.addRestaurant("Restaurante Le Sus", new Date().getTime(), new Location(32, 28), null);
		userResponsible3.addRestaurant("Restaurante Oviedo", new Date().getTime(), new Location(54, 45), null);
		
		UserResponsible userResponsible4 =getRatatoullie().addUserResponsible("resUser4", "apellido", "resUser4@mail.com", "pass",new Location(3, 9));
		userResponsible4.addRestaurant("Restaurante Dambleé", new Date().getTime(), new Location(7, 2), null);
		userResponsible4.addRestaurant("Restaurante Kiria", new Date().getTime(), new Location(5, 8), null);


	}
	@Before
	public void before(){

	}
	
	@After
	public void after() {
		
	}

	/**
	 * Prove that a UserResponsible can delete a restaurant that does not exist.
	 */
	@Test	
	public void addRestaurantOK(){
		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("restaurantUser", "apellido", "mail@mail.com", "pass",new Location(2, 3));
		int numberOfRestaurants = getRatatoullie().getRestaurants().size();
		
		Restaurant restaurant= userResponsible.addRestaurant("RestaurantOK", new Date().getTime(), new Location(1, 1), null);
		Restaurant restaurant2= getRatatoullie().getRestaurant("RestaurantOK");
		
		Assert.assertEquals(restaurant,restaurant2);
		Assert.assertEquals(userResponsible.getRestaurants().size(), 1);
		Assert.assertEquals(getRatatoullie().getRestaurants().size(), numberOfRestaurants + 1);
	}

	/**
	 * Prove that a UserResponsible can not add a restaurant that already exists.
	 */
	@Test	
	public void addRestaurantFailed(){	
		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("restaurantUser1@mail.com", "apellido", "restaurantUser1@mail.com", "pass",new Location(2, 3));
		int cantidadRestaurantes = getRatatoullie().getRestaurants().size();
		
		Restaurant restaurant= userResponsible.addRestaurant("RestaurantOK", new Date().getTime(), new Location(1, 1), null);
		
		Assert.assertNull(restaurant);
		Assert.assertEquals(userResponsible.getRestaurants().size(), 0);
		Assert.assertEquals(getRatatoullie().getRestaurants().size(), cantidadRestaurantes);
	}

	/**
	 * Prove that a UserResponsible can delete a restaurant that exists.
	 * Not deleted from Ratatoullie.
	 */
	@Test	
	public void removeRestaurantOK(){	
		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("restaurantUser2", "apellido", "restaurantUser2@mail.com", "pass",new Location(2, 3));		
		Restaurant restaurant= userResponsible.addRestaurant("RestaurantOK1", new Date().getTime(), new Location(1, 1), null);
		
		userResponsible.removeRestaurant(restaurant);
		
		Assert.assertFalse(userResponsible.getRestaurants().contains(restaurant));
		Assert.assertEquals(userResponsible.getRestaurants().size(), 0);
	}
	
	/**
	 * Prove that a UserResponsible can not delete a restaurant that does not exist.
	 */
	@Test	
	public void removeRestaurantFailed(){	
		
		UserResponsible userResponsible =getRatatoullie().addUserResponsible("restaurantUser3", "apellido", "restaurantUser3@mail.com", "pass",new Location(2, 3));
		Restaurant restaurant= userResponsible.addRestaurant("RestaurantOK2", new Date().getTime(), new Location(1, 1), null);
		Restaurant restaurantNULL = getRatatoullie().getRestaurant("RestNULL");
		
		userResponsible.removeRestaurant(restaurantNULL);
		
		Assert.assertFalse(userResponsible.getRestaurants().contains(restaurantNULL));
		Assert.assertTrue(userResponsible.getRestaurants().contains(restaurant));
		Assert.assertEquals(userResponsible.getRestaurants().size(), 1);
	}

	/**
	 * Prove that a UserResponsible can not comment on nearby restaurants.
	 */
	@Test
	public void commentToNearbyRestaurant(){
		
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser1@mail.com");
		Restaurant nearbyRestaurant = (Restaurant) getRatatoullie().getRestaurant("FastFood");		
		
		int allComments=nearbyRestaurant.getAllComments().size();
		int myComments=userResponsible.getMyComments().size();
		int notifications=nearbyRestaurant.getOwner().getNotifications().size();
		
		Comment invalidComment =userResponsible.comment("Buen menu", new Date().getTime(), nearbyRestaurant, Vote.POSITIVE);
		
		
		Assert.assertNull(invalidComment);
		Assert.assertTrue(allComments==nearbyRestaurant.getAllComments().size());
		Assert.assertTrue(myComments==userResponsible.getMyComments().size());
		Assert.assertTrue(notifications==nearbyRestaurant.getOwner().getNotifications().size());
		

	}

	/**
	 * Prove that a UserResponsible can comment on Restaurant not nearby.
	 */
	@Test
	public void commentToRestaurantNotNearby(){
		
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser1@mail.com");
		Restaurant restaurantNotNearby = (Restaurant) getRatatoullie().getRestaurant("Restaurante Las Violetas");	
		
		
		Comment validComment =userResponsible.comment("Buen menu", new Date().getTime(), restaurantNotNearby, Vote.POSITIVE);

		Assert.assertTrue(restaurantNotNearby.getAllComments().contains(validComment));
		Assert.assertTrue(userResponsible.getMyComments().contains(validComment));	

	}

	/**
	 * Test that a UserResponsible can not comment on a menu in a nearby Restaurant.
	 */
	@Test
	public void commentMenuToRestaurantNearby(){
		
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser1@mail.com");
		Restaurant nearbyRestaurant = (Restaurant) getRatatoullie().getRestaurant("FastFood");
		nearbyRestaurant.addMenu("Menu Nuevo Comentario Invalido", "fritas, pollo, ensalada", new Long(4645634), null);
		Menu invalidMenu = nearbyRestaurant.getCourentMenu();	

		int allComments=invalidMenu.getComments().size();
		int myComments=userResponsible.getMyComments().size();
		int notifications=invalidMenu.getRestaurant().getOwner().getNotifications().size();
		
		Comment invalidComment =userResponsible.comment("Test Fallido", new Date().getTime(), invalidMenu, Vote.NEGATIVE);	
		
		Assert.assertNull(invalidComment);
		Assert.assertTrue(allComments==invalidMenu.getComments().size());
		Assert.assertTrue(myComments==userResponsible.getMyComments().size());
		Assert.assertTrue(notifications==invalidMenu.getRestaurant().getOwner().getNotifications().size());
	
		
	}
	
	/**
	 * Test that a UserResponsible can comment on a nearby Restaurant menu.
	 */
	@Test
	public void commentMenuToRestaurantNotNearby(){
		
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser1@mail.com");
		Restaurant restaurantNotNearby = (Restaurant) getRatatoullie().getRestaurant("Restaurante Las Violetas");	
		restaurantNotNearby.addMenu("Menu Nuevo Comentario Valido", "fritas, pollo, ensalada", new Long(56754), null);
		Menu validMenu = restaurantNotNearby.getCourentMenu();	
		
		
		Comment validComment = userResponsible.comment("Test Positivo", new Date().getTime(), validMenu, Vote.POSITIVE);
		
		Assert.assertTrue(validMenu.getComments().contains(validComment));
		Assert.assertTrue(userResponsible.getMyComments().contains(validComment));	

	}
	
	/**
	 * Test when a UserResponsible configures the notification block for User with Ranking Visitor,
	 * then you do not receive notifications from users with Visitor Ranking.
	 */
	@Test
	public void blockedNotificationToVisitor(){
		
		User userRankingVisitor =  getRatatoullie().getUser("User1@mail.com");
		UserResponsible userReponsible = (UserResponsible) getRatatoullie().getUser("resUser3@mail.com");
		userReponsible.addBlockedRanking(new Visitor());
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Oviedo");
		
		int notifications=userReponsible.getNotifications().size();
		
		Comment unnotifiedComment = userRankingVisitor.comment("Comentario Visitor", new Date().getTime(), validRestaurant, Vote.POSITIVE);
		
		
		Assert.assertTrue(userRankingVisitor.getMyComments().contains(unnotifiedComment));
		Assert.assertTrue(validRestaurant.getAllComments().contains(unnotifiedComment));
		Assert.assertTrue(notifications==userReponsible.getNotifications().size());

	}

	/**
	 * Test when a UserResponsible configures the notification block for User with Visitor ranking,
	 * and can only receive notifications from users with Commensal or Gourmet Ranking.
	 */
	@Test
	public void notificationSentFromNonVisitor(){
		
		User userRankingCommensal =  getRatatoullie().getUser("User2@mail.com");
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser3@mail.com");
		userResponsible.addBlockedRanking(new Visitor());
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Le Sus");
		for (int i=1; i<=19; i++) {
			userRankingCommensal.comment("Comentario "+i, new Date().getTime(), validRestaurant, Vote.POSITIVE);
		}
		Comment reportedCommentCommensal = userRankingCommensal.comment("Comentario Commensal 20", new Date().getTime(), validRestaurant, Vote.POSITIVE);
				
		Assert.assertTrue(userRankingCommensal.getMyComments().contains(reportedCommentCommensal));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentCommensal));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentCommensal));
		
		
		User userRankingGourmet = getRatatoullie().getUser("User5@mail.com");	
		for (int i=1; i<=40; i++) {
			userRankingGourmet.comment("Comentario "+i, new Date().getTime(), validRestaurant, Vote.POSITIVE);
		}
		Comment reportedCommentGourmet = userRankingGourmet.comment("Comentario Gourmet 41", new Date().getTime(), validRestaurant, Vote.POSITIVE);

		Assert.assertTrue(userRankingGourmet.getMyComments().contains(reportedCommentGourmet));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentGourmet));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentGourmet));

	}

	/**
	 * Test when a UserResponsible configures notification blocking for User with Ranking Commensal,
	 * then you do not receive notifications from users with Commensal Ranking.
	 */
	@Test
	public void blockedNotificationToCommensal(){
		
		User userRankingCommensal =  getRatatoullie().getUser("User6@mail.com");
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser4@mail.com");
		
		userResponsible.addBlockedRanking(new Commensal());
		
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Kiria");	
		
		for (int i=1; i<=19; i++) {
			userRankingCommensal.comment("Comentario "+i, new Date().getTime(), validRestaurant, Vote.POSITIVE);
		}
		int notifications=userResponsible.getNotifications().size();
		Comment unnotifiedComment = userRankingCommensal.comment("Comentario Commensal 20", new Date().getTime(), validRestaurant, Vote.POSITIVE);
				
		Assert.assertTrue(userRankingCommensal.getMyComments().contains(unnotifiedComment));
		Assert.assertTrue(validRestaurant.getAllComments().contains(unnotifiedComment));
		Assert.assertTrue(notifications==userResponsible.getNotifications().size());

	}

	/**
	 * Test when a UserResponsible configures the notification block for User with Commensal ranking,
	 * and can only receive notifications from users with Ranking Visitor or Gourmet.
	 */
	@Test
	public void notificationSentFromNonCommensal(){
		
		User userRankingVisitor =  getRatatoullie().getUser("User7@mail.com");
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser4@mail.com");
		
		userResponsible.addBlockedRanking(new Commensal());
		
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Dambleé");

	
		Comment reportedCommentVisitor = userRankingVisitor.comment("Comentario Visitor 1", new Date().getTime(), validRestaurant, Vote.POSITIVE);
				
		Assert.assertTrue(userRankingVisitor.getMyComments().contains(reportedCommentVisitor));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentVisitor));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentVisitor));
		
		
		User userRankingGourmet = getRatatoullie().getUser("User8@mail.com");	
		for (int i=1; i<=40; i++) {
			userRankingGourmet.comment("Comentario "+i, new Date().getTime(), validRestaurant, Vote.POSITIVE);
		}
		Comment reportedCommentGourmet = userRankingGourmet.comment("Comentario Gourmet 41", new Date().getTime(), validRestaurant, Vote.POSITIVE);
		
		Assert.assertTrue(userRankingGourmet.getMyComments().contains(reportedCommentGourmet));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentGourmet));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentGourmet));
	}

	/**
	 * Test when a UserResponsible configures the notification block for User with Gourmet Ranking,
	 * then you do not receive notifications from users with Gourmet Ranking.
	 */
	@Test
	public void blockedNotificationToGourmet(){
		
		User userRankingGourmet =  getRatatoullie().getUser("User9@mail.com");
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser4@mail.com");
		userResponsible.addBlockedRanking(new Gourmet());
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Kiria");	
		
		for (int i=1; i<=41; i++) {
			userRankingGourmet.comment("Comentario "+i, new Date().getTime(), validRestaurant, Vote.POSITIVE);
		}
		int notifications=userResponsible.getNotifications().size();
		Comment unnotifiedComment = userRankingGourmet.comment("Comentario Gourmet 42", new Date().getTime(), validRestaurant, Vote.POSITIVE);
				
		Assert.assertTrue(userRankingGourmet.getMyComments().contains(unnotifiedComment));
		Assert.assertTrue(validRestaurant.getAllComments().contains(unnotifiedComment));
		Assert.assertTrue(notifications==userResponsible.getNotifications().size());

	}

	/**
	 * Test when a UserResponsible configures the notification block for User with Gourmet ranking,
	 * and can only receive notifications from users with Ranking Visitor or Commensal. 
	 */
	@Test
	public void notificationSentFromNonGourmet(){
		
		User userRankingVisitor =  getRatatoullie().getUser("User7@mail.com");
		UserResponsible userResponsible = (UserResponsible) getRatatoullie().getUser("resUser4@mail.com");
		userResponsible.addBlockedRanking(new Gourmet());
		Restaurant validRestaurant = (Restaurant) getRatatoullie().getRestaurant("Restaurante Dambleé");

		
		Comment reportedCommentVisitor =  userRankingVisitor.comment( "Comentario Visitor 2", new Date().getTime(), validRestaurant, Vote.POSITIVE);
				
		Assert.assertTrue(userRankingVisitor.getMyComments().contains(reportedCommentVisitor));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentVisitor));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentVisitor));
		
		
		User userRankingCommensal = getRatatoullie().getUser("User6@mail.com");	

		
		Comment reportedCommentCommensal = userRankingCommensal.comment("Comentario Gourmet 22", new Date().getTime(), validRestaurant, Vote.POSITIVE);

		Assert.assertTrue(userRankingCommensal.getMyComments().contains(reportedCommentCommensal));
		Assert.assertTrue(validRestaurant.getAllComments().contains(reportedCommentCommensal));
		Assert.assertTrue(userResponsible.containsCommentInNotifications(reportedCommentCommensal));
	}

}