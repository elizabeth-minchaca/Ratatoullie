package ar.edu.unlp.lifia.ratatoullie.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.lifia.ratatoullie.convert.Converter;
import ar.edu.unlp.lifia.ratatoullie.dao.RatatoullieDao;
import ar.edu.unlp.lifia.ratatoullie.dao.RestaurantDao;
import ar.edu.unlp.lifia.ratatoullie.dto.CommentDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;
import ar.edu.unlp.lifia.ratatoullie.model.Vote;
import ar.edu.unlp.lifia.ratatoullie.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private RatatoullieDao ratatoullieDao;
	@Autowired
	RestaurantDao restaurantDao;	
	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}
	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	public RatatoullieDao getRatatoullieDao() {
		return ratatoullieDao;
	}
	public void setRatatoullieDao(RatatoullieDao ratatoullieDao) {
		this.ratatoullieDao = ratatoullieDao;
	}

	@Override
	public CommentDto commentRestaurant(String userMail, String text, long date, String restaurantName,double latitude, double longitude, String vote) throws EntityNotExistsException {
		Ratatoullie ratatoullie=getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		Restaurant restaurant= ratatoullie.getRestaurant(restaurantName, new Location(latitude,longitude));
		if(restaurant!=null){
			User user= ratatoullie.getUser(userMail);
			if(user!=null && user.isEnable()){
				Comment comment;
				try {
				UserResponsible userResponsible =(UserResponsible)user;
					comment=userResponsible.comment(text, date, restaurant, Vote.valueOf(vote.toUpperCase()));
				} catch (ClassCastException e) {
					comment=user.comment(text, date, restaurant, Vote.valueOf(vote.toUpperCase()));
				}
				getRestaurantDao().save(restaurant);
				return Converter.convert(comment);					
			}
			throw new EntityNotExistsException("El Usuario no existe");
		}
		throw new EntityNotExistsException("El Restaurante no existe");
	}

	@Override
	public CommentDto commentMenu(String userMail, String text, long date, String restaurantName,double latitude, double longitude, String vote) throws EntityNotExistsException {
		Ratatoullie ratatoullie=getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		Restaurant restaurant= ratatoullie.getRestaurant(restaurantName, new Location(latitude,longitude));
		if(restaurant!=null){
			User user= ratatoullie.getUser(userMail);
			if(user!=null && user.isEnable()){
				Comment comment;
				try {
				UserResponsible userResponsible =(UserResponsible)user;
					comment=userResponsible.comment(text, date, restaurant.getCourentMenu(), Vote.valueOf(vote.toUpperCase()));
				} catch (ClassCastException e) {
					comment=user.comment(text, date, restaurant.getCourentMenu(), Vote.valueOf(vote.toUpperCase()));
				}
				getRestaurantDao().save(restaurant);
				return Converter.convert(comment);					
			}
			throw new EntityNotExistsException("El Usuario no existe");
		}
		throw new EntityNotExistsException("El Restaurante no existe");
	}
	@Override
	public List<CommentDto> getRestaurantComments(String restaurantName, double latitude, double longitude)
			throws EntityNotExistsException {
		Ratatoullie ratatoullie=getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		Restaurant restaurant= ratatoullie.getRestaurant(restaurantName, new Location(latitude,longitude));
		if(restaurant!=null){
			return Converter.convertListComment(restaurant.getComments());
		}
		throw new EntityNotExistsException("El Restaurante no existe");
	}
	@Override
	public List<CommentDto> getRestaurantCurrentMenuComments(String restaurantName, double latitude, double longitude)
			throws EntityNotExistsException {
		Ratatoullie ratatoullie=getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		Restaurant restaurant= ratatoullie.getRestaurant(restaurantName, new Location(latitude,longitude));
		if(restaurant!=null){
			return Converter.convertListComment(restaurant.getCourentMenu().getComments());
		}
		throw new EntityNotExistsException("El Restaurante no existe");
	}
}