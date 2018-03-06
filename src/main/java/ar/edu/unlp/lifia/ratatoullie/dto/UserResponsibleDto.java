package ar.edu.unlp.lifia.ratatoullie.dto;

import java.util.List;

public class UserResponsibleDto extends UserDto{
	private int quantityOfNotificationsNotSeen;
	private List<RestaurantDto> restaurants;
	private List<String> blockedRanking;	
	private List<NotificationDto> notifications;
	public UserResponsibleDto() {
	}
	public UserResponsibleDto(String name, String lastName, String mail, boolean enable, LocationDto location,	
			 String ranking, List<UserMinDto> followers, List<UserMinDto> followings, List<RecommendationDto> recommendations, List<RestaurantDto> restaurants, List<String> blockedRanking, List<NotificationDto> notifications, int quantityOfNotificationsNotSeen){
		super(name, lastName, mail, enable, location, ranking, followers, followings, recommendations);
		setRestaurants(restaurants);
		setBlockedRanking(blockedRanking);
		setNotifications(notifications);
		setQuantityOfNotificationsNotSeen(quantityOfNotificationsNotSeen);
	}
	public List<RestaurantDto> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantDto> restaurants) {
		this.restaurants = restaurants;
	}
	public List<String> getBlockedRanking() {
		return blockedRanking;
	}
	public void setBlockedRanking(List<String> blockedRanking) {
		this.blockedRanking = blockedRanking;
	}
	public List<NotificationDto> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<NotificationDto> notifications) {
		this.notifications = notifications;
	}
	public int getQuantityOfNotificationsNotSeen() {
		return quantityOfNotificationsNotSeen;
	}
	public void setQuantityOfNotificationsNotSeen(int quantityOfNotificationsNotSeen) {
		this.quantityOfNotificationsNotSeen = quantityOfNotificationsNotSeen;
	}
	
}
