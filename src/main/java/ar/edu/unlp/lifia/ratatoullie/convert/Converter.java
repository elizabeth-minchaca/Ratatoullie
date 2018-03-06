package ar.edu.unlp.lifia.ratatoullie.convert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unlp.lifia.ratatoullie.dto.CategoryDto;
import ar.edu.unlp.lifia.ratatoullie.dto.CommentDto;
import ar.edu.unlp.lifia.ratatoullie.dto.CommentingDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.LocationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.MenuDto;
import ar.edu.unlp.lifia.ratatoullie.dto.NotificationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RankingQuantityDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RecommendationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserResponsibleDto;
import ar.edu.unlp.lifia.ratatoullie.model.Benefits;
import ar.edu.unlp.lifia.ratatoullie.model.Category;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;
import ar.edu.unlp.lifia.ratatoullie.model.Commenting;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Menu;
import ar.edu.unlp.lifia.ratatoullie.model.Notification;
import ar.edu.unlp.lifia.ratatoullie.model.Ranking;
import ar.edu.unlp.lifia.ratatoullie.model.Recommendation;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;

public class Converter {

	public static UserDto convert(User user) {
		return new UserDto(user.getName(), user.getLastName(), user.getMail(), user.isEnable(),
				convert(user.getLocation()), user.getRanking().getName(),
				convertListUserMin(user.getFollowers()), convertListUserMin(user.getFollowings()),
				convertListRecommendation(user.getRecommendations()));
	}

	public static UserMinDto convertUserMin(User user) {
		return new UserMinDto(user.getName(), user.getLastName(), user.getMail(), user.isEnable(),
				convert(user.getLocation()), user.getRanking().getName(), user.getMyComments().size());
	}

	public static List<UserMinDto> convertListUserMin(Set<User> users) {
		ArrayList<UserMinDto> followDtos = new ArrayList<UserMinDto>();
		for (User user : users) {
			followDtos.add(convertUserMin(user));
		}
		return followDtos;
	}

	public static List<UserMinDto> convertListUserMin(List<User> users) {
		ArrayList<UserMinDto> followDtos = new ArrayList<UserMinDto>();
		for (User user : users) {
			followDtos.add(convertUserMin(user));
		}
		return followDtos;
	}

	public static UserResponsibleDto convert(UserResponsible user) {
		ArrayList<String> rankings = new ArrayList<String>();
		for (Ranking ranking : user.getBlockedRanking()) {
			rankings.add(ranking.getName());
		}
		ArrayList<RestaurantDto> restaurants = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : user.getRestaurants()) {
			restaurants.add(new RestaurantDto(restaurant.getName(), restaurant.getOpeningDate(),
					convert(restaurant.getLocation()), convert(restaurant.getCategory())));
		}
		return new UserResponsibleDto(user.getName(), user.getLastName(), user.getMail(), user.isEnable(),
				convert(user.getLocation()), user.getRanking().getName(),
				convertListUserMin(user.getFollowers()), convertListUserMin(user.getFollowings()),
				convertListRecommendation(user.getRecommendations()), restaurants, rankings,
				convertListNotification(user.getNotifications()),user.getQuantityOfNotificationsNotSeen());
	}

	private static List<NotificationDto> convertListNotification(List<Notification> notifications) {
		ArrayList<NotificationDto> notificationDtos = new ArrayList<NotificationDto>();
		for (Notification notification : notifications) {
			notificationDtos.add(convert(notification));
		}
		return notificationDtos;
	}

	public static NotificationDto convert(Notification notification) {
		return new NotificationDto(notification.getId(), notification.getDate(), convert(notification.getComment()), notification.isSeen());
	}

	public static RestaurantDto convert(Restaurant restaurant) {
		return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getOpeningDate(),
				restaurant.getImageBase64(), convert(restaurant.getLocation()), convert(restaurant.getCategory()),
				convertListComment(restaurant.getComments()), convertListMenu(restaurant.getMenus()),
				convertUserMin(restaurant.getOwner()), convert(restaurant.getCourentMenu()));
	}

	public static MenuDto convert(Menu menu) {
		if (menu == null) {
			return new MenuDto();
		}
		return new MenuDto(menu.getId(), convertRestaurantMin(menu.getRestaurant()), menu.getText(),
				menu.getDescription(), menu.getImageBase64(), menu.getStartDate(), menu.getEndDate(),
				convertListComment(menu.getComments()));

	}

	public static RestaurantMinDto convertRestaurantMin(Restaurant restaurant) {
		return new RestaurantMinDto(restaurant.getId(), restaurant.getName(), convert(restaurant.getLocation()),
				restaurant.getImageBase64(),convertUserMin(restaurant.getOwner()),restaurant.getOpeningDate(),convert(restaurant.getCategory()),restaurant.getCourentMenu().getId());
	}

	public static List<RecommendationDto> convertListRecommendation(List<Recommendation> recommendations) {
		ArrayList<RecommendationDto> recommendationDtos = new ArrayList<RecommendationDto>();
		for (Recommendation recommendation : recommendations) {
			recommendationDtos.add(convert(recommendation));
		}
		return recommendationDtos;
	}

	public static RecommendationDto convert(Recommendation recommendation) {
		return new RecommendationDto(convertUserMin(recommendation.getAdviser()),
				convertUserMin(recommendation.getRecommended()), convert(recommendation.getMenu()),
				recommendation.getDate(), recommendation.getText());

	}

	public static List<MenuDto> convertListMenu(List<Menu> menus) {
		ArrayList<MenuDto> menuDtos = new ArrayList<MenuDto>();
		for (Menu menu : menus) {
			menuDtos.add(convert(menu));
		}
		return menuDtos;
	}

	public static List<CommentDto> convertListComment(List<Comment> comments) {
		ArrayList<Comment> comments2 = new ArrayList<Comment>();
		comments2.addAll(comments);
		Comparator<Comment> comparator = new Comparator<Comment>(){
			@Override
			public int compare(Comment comment, Comment comment2)
			{

				if (comment.getDate() == comment2.getDate()) {
					return 0;
				}else{
					if (comment.getDate()>comment2.getDate()) {
						return -1;
					}else {
						return 1;
					}
				}
			}             
		};
		comments2.sort(comparator);
		ArrayList<CommentDto> commentDtos = new ArrayList<CommentDto>();
		for (Comment comment : comments2) {
			commentDtos.add(convert(comment));
		}
		return commentDtos;
	}

	public static CommentDto convert(Comment comment) {
		if (comment != null) {
			return new CommentDto(convertUserMin(comment.getUser()), comment.getText(), comment.getDate(),
					convert(comment.getCommenting()), comment.getVote());
		}
		return new CommentDto();
	}

	private static CommentingDto convert(Commenting commenting) {
		if(commenting.getClass().getSimpleName().contains("Menu")){
			return new CommentingDto("Menu",commenting.getId());
		}
		return new CommentingDto("Restaurant",commenting.getId());
	}

	public static CategoryDto convert(Category category) {
		ArrayList<String> strings = new ArrayList<String>();
		for (Benefits benefits : category.getBenefices()) {
			strings.add(benefits.name());
		}
		return new CategoryDto(category.getClass().getSimpleName(), strings);
	}

	public static LocationDto convert(Location location) {
		return new LocationDto(location.getLatitude(), location.getLongitude());
	}

	public static List<RestaurantDto> convertListRestaurant(List<Restaurant> restaurants) {
		ArrayList<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : restaurants) {
			restaurantDtos.add(convert(restaurant));
		}
		return restaurantDtos;
	}

	public static List<UserDto> convertListUser(Set<User> users) {
		ArrayList<UserDto> userDtos = new ArrayList<UserDto>();
		for (User user : users) {
			userDtos.add(convert(user));
		}
		return userDtos;
	}

	public static List<UserResponsibleDto> convertListUserResponsible(Set<UserResponsible> userResponsibles) {
		ArrayList<UserResponsibleDto> userResponsibleDtos = new ArrayList<UserResponsibleDto>();
		for (UserResponsible userResponsible : userResponsibles) {
			userResponsibleDtos.add(convert(userResponsible));
		}
		return userResponsibleDtos;
	}

	public static List<RankingQuantityDto> convertListRankingQuantity(Map<Ranking, Integer> quantityUsersRank) {
		ArrayList<RankingQuantityDto> quantityDtos = new ArrayList<RankingQuantityDto>();
		quantityUsersRank.keySet();
		for (Ranking ranking : quantityUsersRank.keySet()) {
			quantityDtos.add(new RankingQuantityDto(ranking.getName(), quantityUsersRank.get(ranking)));
		}
		return quantityDtos;
	}

	public static List<RestaurantMinDto> convertListRestaurantMinDate(List<Restaurant> restaurants, long initialDate,
			long finalDate) {
		ArrayList<RestaurantMinDto> restaurantMinDtos = new ArrayList<RestaurantMinDto>();
		for (Restaurant restaurant : restaurants) {
			RestaurantMinDto restaurantMinDto = convertRestaurantMin(restaurant);
			restaurantMinDto.setCommenteSize(restaurant.getAllComments(initialDate, finalDate).size());
			restaurantMinDtos.add(restaurantMinDto);
		}
		return restaurantMinDtos;
	}

	public static List<RestaurantMinDto> convertListRestaurantMin(List<Restaurant> restaurants) {
		 ArrayList<RestaurantMinDto> restaurantMinDtos = new ArrayList<RestaurantMinDto>();
		for (Restaurant restaurant : restaurants) {
			restaurantMinDtos.add(convertRestaurantMin(restaurant));
		}
		return restaurantMinDtos;
	}
}
