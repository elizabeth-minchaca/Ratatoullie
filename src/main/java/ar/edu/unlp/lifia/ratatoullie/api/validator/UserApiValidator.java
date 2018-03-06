package ar.edu.unlp.lifia.ratatoullie.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserApiValidator extends GenericValidator{
	@Autowired
	RestaurantApiValidator restaurantApiValidator;	
	public RestaurantApiValidator getRestaurantApiValidator() {
		return restaurantApiValidator;
	}
	public void setRestaurantApiValidator(RestaurantApiValidator restaurantApiValidator) {
		this.restaurantApiValidator = restaurantApiValidator;
	}
	protected boolean email(String email){
		return isNotEmpty(email)&noWhite(email)&isNotNull(email)&isEmail(email);
	}
	protected boolean password(String password){
		return isNotEmpty(password)&noWhite(password)&isNotNull(password);
	}
	protected boolean name(String name){
		return isNotEmpty(name)&isNotNull(name);
	}
	protected boolean text(String text){
		return isNotEmpty(text)&isNotNull(text);
	}
	protected boolean lastName(String lastName){
		return isNotEmpty(lastName)&isNotNull(lastName);
	}
	public boolean login(String mail, String pass) {		
		return email(mail)&password(pass);
	}	
	public boolean submitUserRegister(String name, String lastName, Double latitude, Double longitude, String email, String password, String password2){
		if(password.compareTo(password2)==0){
			return name(name)&lastName(lastName)&latitude(latitude)&longitude(longitude)&password(password2)&password(password)&email(email);
		}
		return false;
	}
	public boolean submitResponsibleRegister(String name, String lastName, String email,  String password, String password2, Double latitude, Double longitude, String restName, long restDate,  Double restLatitude, Double restLongitude,  String menuName, String description){
		return submitUserRegister(name, lastName, latitude, longitude, email, password, password2)&getRestaurantApiValidator().createRestaurant(restName, restDate, restLatitude, restLongitude, menuName, description);
	}
	public boolean changePassword(String oldPassword, String newPassword, String newPassword2){
		return password(newPassword2)&password(newPassword)&password(oldPassword)&newPassword.compareTo(newPassword2)==0;				
	}
	public boolean submitEditUser(String name, String lastName, Double latitude, Double longitude){
		return name(name)&lastName(lastName)&latitude(latitude)&longitude(longitude);
	}
	public boolean follow(String mail){
		return email(mail);
	}
	public boolean topUsersComments(int quantity){
		return quantity(quantity);
	}
	public boolean recommendFriend(long idMenu, String email, String text){
		return id(idMenu)&email(email)&text(text);
	}
	public boolean recommendFriends(long idMenu, String text){
		return id(idMenu)&text(text);
	}
	public boolean markAsSeen(long idMenu){
		return id(idMenu);
	}
	public boolean setUpNotification(Boolean visitor, Boolean commensal, Boolean gourmet){
		return isNotNull(visitor)&isNotNull(commensal)&isNotNull(gourmet);
	}

}