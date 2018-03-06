package ar.edu.unlp.lifia.ratatoullie.api.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.edu.unlp.lifia.ratatoullie.model.Vote;

@Component
public class CommentApiValidator extends GenericValidator{
	@Autowired
	RestaurantApiValidator restaurantApiValidator;	
	public RestaurantApiValidator getRestaurantApiValidator() {
		return restaurantApiValidator;
	}
	public void setRestaurantApiValidator(RestaurantApiValidator restaurantApiValidator) {
		this.restaurantApiValidator = restaurantApiValidator;
	}
	protected boolean text(String text){
		return isNotEmpty(text)&isNotNull(text);
	}
	public boolean getRestaurant(double latitude,double longitude,String restaurant){
		return latitude(latitude)&longitude(longitude)&getRestaurantApiValidator().name(restaurant);
	}
	public boolean comment(String restaurant, String text, Double latitude, Double longitude, String vote, long date){
		return getRestaurant(latitude, longitude, restaurant)&text(text)&date(date)&vote(vote);
	}
	private boolean vote(String vote) {
		return isNotNull(vote)&isNotEmpty(vote)&(vote.toUpperCase().compareTo(Vote.NEGATIVE.toString())==0
				|vote.toUpperCase().compareTo(Vote.POSITIVE.toString())==0
				|vote.toUpperCase().compareTo(Vote.NEUTRAL.toString())==0);
	}
}