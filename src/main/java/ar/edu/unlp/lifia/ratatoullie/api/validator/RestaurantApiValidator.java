package ar.edu.unlp.lifia.ratatoullie.api.validator;

import org.springframework.stereotype.Component;
@Component
public class RestaurantApiValidator extends GenericValidator{
	
	protected boolean name(String name){
		return isNotEmpty(name)&isNotNull(name);
	}
	public boolean searchDistance(double latitude,double longitude,double distance){
		return latitude(latitude)&longitude(longitude)&distance>0;
	}
	protected boolean menuName(String name){
		return isNotEmpty(name)&isNotNull(name);
	}
	protected boolean menuDescription(String name){
		return isNotEmpty(name)&isNotNull(name);
	}
	public boolean createRestaurant(String restaurant,long date, double latitude, double longitude, String menuName,String description ){
		return name(restaurant)&date(date)&latitude(latitude)&longitude(longitude)&menuName(menuName)&menuDescription(description);
	}	
	public boolean showRestaurant(long idRest){
		return id(idRest);
	}
	public boolean editRestaurant(long idRest, String restaurant, long date, double latitude, double longitude){
		return id(idRest)&name(restaurant)&date(date)&latitude(latitude)&longitude(longitude);
	}
	public boolean addMenu(long idRest, String name,String description){
		return id(idRest)&menuName(name)&menuDescription(description);
	}
	public boolean editMenu(long idRest,long idMenu, String name,String description){
		return id(idMenu)&id(idRest)&menuName(name)&menuDescription(description);
	}
	public boolean getRestaurant(long id){
		return id(id);
	}
	public boolean getMenu(long idRest,long idMenu){
		return id(idMenu)&id(idRest);
	}
	public boolean getRestaurantsTopComments(long initialDate, long finalDate, int quantity){
		return date(finalDate)&date(initialDate)&quantity(quantity)&initialDate<finalDate;
	}
	public boolean search(String name) {
		return name(name);
	}
	public boolean getMenu(long id) {
		return id(id);
	}
	
}