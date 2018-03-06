package ar.edu.unlp.lifia.ratatoullie.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="ratatoullie")
public class Ratatoullie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private static Ratatoullie ratatoullie;
	@OneToMany(mappedBy="ratatoullie")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<User> users;
	@OneToMany(mappedBy="ratatoullie")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Restaurant> restaurants;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	private Ratatoullie() {
		setRestaurants(new ArrayList<Restaurant>());
		setUsers(new ArrayList<User>());
	}	
	public static void setRatatoullie(Ratatoullie ratatoullie) {
		Ratatoullie.ratatoullie = ratatoullie;
	}
	private static Ratatoullie getRatatoullie() {
		return ratatoullie;
	}
	public static Ratatoullie getInstance(){		
		if(getRatatoullie()==null){
			setRatatoullie(new Ratatoullie());
		}
		return getRatatoullie();
	}
	/**
	 * Returns a list of {@link Restaurant} within a certain distance of a {@link Location} 
	 * @param location
	 * @return
	 */
	public List<Restaurant> search(Location location, double distance) {
		ArrayList<Restaurant> restaurants= new ArrayList<Restaurant>();
		for (Restaurant restaurant : getRestaurants()) {
			if(location.getDistance(restaurant.getLocation())<=distance){
				restaurants.add(restaurant);
			}
		}
		return order(restaurants);
	}
	/**
	 * Sorts a list of {@link Restaurant} according to the {@link Benefits} TOP
	 * @param restaurants
	 * @return
	 */
	public List<Restaurant> order(List<Restaurant> restaurants) {
		Comparator<Restaurant> comparator = new Comparator<Restaurant>(){
		     @Override
		     public int compare(Restaurant restaurant, Restaurant restaurant2)
		     {
		    	 boolean rest1= restaurant.getBenefices().contains(Benefits.TOP);
			 		boolean rest2= restaurant2.getBenefices().contains(Benefits.TOP);
			         if (rest1 & rest2) {
			             return 0;
			         }
			         if (rest1 ) {
			             return -1;
			         }
			         if (rest2 ) {
			             return 1;
			         }
			         return 0;
		     }             
		};
		restaurants.sort(comparator);
		return restaurants;
	}
	/**
	 * Returns a list of {@link Restaurant} that have a minimun quantity of one type of {@link Vote}
	 * @param vote
	 * @param quantity
	 * @return
	 */
	public List<Restaurant> search(Vote vote, int quantity) {
		ArrayList<Restaurant> restaurantsResult = new ArrayList<Restaurant>();
		for (Restaurant restaurant : getRestaurants()) {
			int comments=0;
			for (Comment comment : restaurant.getAllComments()) {
				if(comment.getVote().equals(vote)){
					comments++;
				}
			}
			if(comments>=quantity){
				restaurantsResult.add(restaurant);
			}
		}	
		return order(restaurantsResult);
	}
	/**
	 * ?
	 * @param recommendation
	 * @return
	 */
	public List<Restaurant> search(Recommendation recommendation) {
		return null;
	}
	/**
	 * Returns a list of {@link Restaurant} that had more comments on a given range of dates.
	 * @param initialDate
	 * @param finalDate
	 * @param quantity limits the number of results.
	 * @return
	 */
	public List<Restaurant> getRestaurantsTopComments(long initialDate,long finalDate, int quantity){
		ArrayList<Restaurant> restaurantsResult = new ArrayList<Restaurant>();
		restaurantsResult.addAll(getRestaurants());
		Comparator<Restaurant> comparator = new Comparator<Restaurant>(){
		     @Override
		     public int compare(Restaurant restaurant, Restaurant restaurant2)
		     {
		    	 int myCommentsSize= restaurant.getAllComments(initialDate, finalDate).size();
			 		int otherCommentsSize= restaurant2.getAllComments(initialDate, finalDate).size();
			         if (myCommentsSize < otherCommentsSize) {
			             return 1;
			         }
			         if (myCommentsSize > otherCommentsSize) {
			             return -1;
			         }
			         return 0;
		     }             
		};
		restaurantsResult.sort(comparator);
		while (restaurantsResult.size()>quantity) {
			restaurantsResult.remove(restaurantsResult.size()-1);			
		}
		ArrayList<Restaurant> restaurantsRemmove = new ArrayList<Restaurant>();
		for (Restaurant restaurant : restaurantsResult) {
			if(restaurant.getAllComments(initialDate, finalDate).size()==0){
				restaurantsRemmove.add(restaurant);
			}
		}		
		restaurantsResult.removeAll(restaurantsRemmove);
		return order(restaurantsResult);
	}
	/**
	 * Returns a list of {@link User} who have more {@link Comment}
	 * @param quantity limits the number of results.
	 * @return
	 */
	public List<User> getTopUsersComments(int quantity){
		ArrayList<User> users = new ArrayList<User>();
		users.addAll(getUsers());
		Comparator<User> comparator = new Comparator<User>(){
		     @Override
		     public int compare(User user, User user2)
		     {
		    	 int myCommentsSize= user.getMyComments().size();
			 		int otherCommentsSize= user2.getMyComments().size();
			         if (myCommentsSize < otherCommentsSize) {
			             return 1;
			         }
			         if (myCommentsSize > otherCommentsSize) {
			             return -1;
			         }
			         return 0;
		     }             
		};		
		users.sort(comparator);		
		while (users.size()>quantity) {
			users.remove(users.size()-1);
		}	
		return users;
	}
	/**
	 * Number of {@link User} per {@link Ranking}
	 * @return {@link Map} <{@link Ranking}, number of {@link User}>
	 */
	public Map<Ranking, Integer> getQuantityUsersRank(){
		HashMap<Ranking, Integer> result = new HashMap<Ranking,Integer>();
		int visitor=0,commensal=0,gourmet=0;
		for (User user : getUsers()) {
			switch (user.getRanking().getName()) {
				case Visitor.name:visitor++;				
				break;
				case Commensal.name:commensal++;
				break;
				case Gourmet.name:gourmet++;
				break;
				default:
				break;
			}
		}
		result.put(new Visitor(), visitor);
		result.put(new Commensal(), commensal);
		result.put(new Gourmet(), gourmet);		
		return result;
	}
	/**
	 * Adds a {@link User} to the instance.
	 * @param user {@link User} with the same name can't be added.
	 * @return returns NULL if the {@link User} already exists.
	 */ 
	private User addUser(User user){		
		if(!getUsers().contains(user)){
			getUsers().add(user);
			return user;
		}
		return null;
	}
	/**
	 * Creates and adds a {@link User} to the system.
	 * @param name
	 * @param location
	 * @return
	 */
	public User addUser(String name, String lastName,String mail,String password, Location location){		
		User user= new User(name, lastName, mail, password, location);
		return addUser(user);
	}
	/**
	 * Creates and adds a {@link UserResponsible} to the system.
	 * @param name
	 * @param location
	 * @return
	 */
	public UserResponsible addUserResponsible(String name, String lastName,String mail,String password, Location location){
		UserResponsible userResponsible= new UserResponsible(name, lastName, mail, password, location);
		userResponsible=(UserResponsible)addUser(userResponsible);
		return userResponsible;
	}
	
	/**
	 * Returns a {@link User} by its mail.
	 * @param mail
	 * @return returns NULL if the {@link User} doesn't exist.
	 */
	public User getUser(String mail){
		for (User user : getUsers()) {
			if(user.getMail().compareTo(mail)==0){
				return user;
			}
		}
		return null;
	}
	/**
	 * Returns a {@link Restaurant} by its name.
	 * @param name
	 * @return returns NULL if the {@link Restaurant} doesn't exist.
	 */
	public Restaurant getRestaurant(String name){
		for (Restaurant restaurant : getRestaurants()) {
			if(restaurant.getName().compareTo(name)==0){
				return restaurant;
			}
		}
		return null;
	}
	/**
	 * Returns a {@link Restaurant} by its id.
	 * @param name
	 * @return returns NULL if the {@link Restaurant} doesn't exist.
	 */
	public Restaurant getRestaurant(long id){
		for (Restaurant restaurant : getRestaurants()) {
			if(restaurant.getId() == id){
				return restaurant;
			}
		}
		return null;
	}

	/**
	 * Used by a {@link UserResponsible} for adding a {@link Restaurant} to the system.
	 * @param restaurant {@link Restaurant} with the same name and {@link Location} can't be added.
	 * @return returns NULL if the {@link Restaurant} already exists.
	 */
	protected Restaurant addRestaurant(Restaurant restaurant) {
		if (!getRestaurants().contains(restaurant) && !(getRestaurant(restaurant.getName(), restaurant.getLocation()) != null)){
			getRestaurants().add(restaurant);
			return restaurant;
		}
		return null;
	}
	
	/**
	 * Returns a {@link Restaurant} by its {@link Location}.
	 * @param location
	 * @return
	 */
	public Restaurant searchRestaurantByLocation(Location location) {
		for (Restaurant restaurant : getRestaurants()) {
			if(restaurant.getLocation().equals(location)){
				return restaurant;
			}
		}
		return null;
	}	
	
	/**
	 * Returns a list of {@link Restaurant} within a certain distance of a list of {@link Location} 
	 * @param locations
	 * @param distance
	 * @return
	 */
	public Set<Restaurant> search(ArrayList<Location> locations, double distance) {
		HashSet<Restaurant> restaurants = new HashSet<Restaurant>();
		for (Location location : locations) {
			restaurants.addAll(search(location, distance));
		}
		return restaurants;		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Returns a {@link Restaurant} by its name and {@link Location}.
	 * @param name
	 * @param location
	 * @return returns NULL if the {@link Restaurant} doesn't exist.
	 */
	public Restaurant getRestaurant(String name, Location location) {
		for (Restaurant restaurant : getRestaurants()) {
			if(restaurant.getName().compareTo(name)==0 && restaurant.getLocation().equals(location)){
				return restaurant;
			}
		}
		return null;
	}	
}