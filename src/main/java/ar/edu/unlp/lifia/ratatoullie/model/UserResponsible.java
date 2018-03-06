package ar.edu.unlp.lifia.ratatoullie.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * User responsible for a {@link Restaurant}.
 * @author NATA
 *
 */

@Entity
@Table(name="user_responsible")
@PrimaryKeyJoinColumn(name="user_id")
public class UserResponsible extends User {
	
    @OneToMany(cascade=CascadeType.ALL, mappedBy="owner",fetch= FetchType.EAGER)  
	private List<Restaurant> restaurants;
	
    @ManyToMany(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	private Set<Ranking> blockedRanking;
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notification> notifications;
	
	protected UserResponsible() {
	}
	protected UserResponsible(String name,String lastName, String mail, String password, Location location){
		super(name, lastName, mail, password, location);
		setBlockedRanking(new HashSet<Ranking>());
		setCommentsRestaurant(new ArrayList<Comment>());
		setRestaurants(new ArrayList<Restaurant>());
		setNotifications(new ArrayList<Notification>());
	}
	/**
	 * Adds a {@link Restaurant} to the system and
	 * sets a {@link UserResponsible} as its owner.
	 * @param name A {@link Restaurant} with the same name is not allowed. 
	 * @param openigDate
	 * @param location
	 * @param image
	 * @return returns NULL if the {@link Restaurant} hasn't been added.
	 */
	public Restaurant addRestaurant(String name, long openigDate, Location location, Blob image){
		Ratatoullie ratatoullie = Ratatoullie.getInstance();
		Restaurant restaurant = new Restaurant(name, openigDate, location, this, image);		
		restaurant=ratatoullie.addRestaurant(restaurant);
		if(restaurant!=null){
			getRestaurants().add(restaurant);
			return restaurant;
		}				
		return null;
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public void removeRestaurant(Restaurant restaurant){
		getRestaurants().remove(restaurant);
	}
	
	public List<Comment> getCommentsRestaurant() {
		List<Comment> comments= new ArrayList<Comment>();
		for (Restaurant restaurant  : getRestaurants()) {
			for (Comment comment : restaurant.getAllComments()) {
				if(!getBlockedRanking().contains(comment.getRanking())){
					comments.add(comment);
				}
			}
		}
		return comments;
	}
	public void setCommentsRestaurant(List<Comment> commentsRestaurant) {
	
	}
	/**
	 * Post a {@link Comment} of a {@link UserResponsible}
	 */
	public Comment comment(String text, long date, Commenting commenting, Vote vote){
		Location locationComment=commenting.getLocation();		
		ArrayList<Location> locations= myRestauratsLocations();
		if(!locations.contains(locationComment)){
			Set<Restaurant> restaurantsNearest= Ratatoullie.getInstance().search(locations, 1000);
			for (Restaurant restaurant : restaurantsNearest) {
				if(restaurant.getLocation().equals(locationComment)){
					return null;
				}
			}
		}
		return super.comment(text,date,commenting,vote);		
	}
	/**
	 * Returns a list of all the {@link Location} of my {@link Restaurant}
	 * @return
	 */
	private ArrayList<Location> myRestauratsLocations() {
		ArrayList<Location> locations =new ArrayList<Location>();
		for (Restaurant restaurant : getRestaurants()) {
			locations.add(restaurant.getLocation());
		}
		return locations;
	}
	/**
	 * The user responsible notifies the {@link Comment} of the {@link Restaurant} which he owns, 
	 * as long as its {@link Ranking} isn't blocked (getBlockedRanking())
	 * @param comment
	 */
	public void notify(Comment comment){
		if(!getBlockedRanking().contains(comment.getRanking())){
			Notification notification = new Notification(new Date().getTime(), comment);
			if(!getNotifications().contains(notification)){
				getNotifications().add(notification);
			}
		}		
	}

	public Set<Ranking> getBlockedRanking() {
		return blockedRanking;
	}
	public void setBlockedRanking(Set<Ranking> blockedRanking) {
		this.blockedRanking = blockedRanking;
	}
	/**
	 * Blocks a {@link Ranking}
	 * @param ranking
	 */
	public void addBlockedRanking(Ranking ranking){
		getBlockedRanking().add(ranking);
	}
	
	/**
	 * Evaluate whether the {@link Comment} is included.
	 * @param comment
	 * @return True if included, otherwise False .
	 */
	public boolean containsCommentInNotifications (Comment comment){
		for (Notification notification : this.getNotifications()){
			if(notification.getComment() == comment){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Counts the number of notifications not seen.
	 * @return Number of notifications not seen.
	 */
	public int getQuantityOfNotificationsNotSeen(){
		int quantity = 0;
		for (Notification notification : this.getNotifications()){
			if(!notification.isSeen()){
				quantity++;
			}
		}
		return quantity;
	}
}