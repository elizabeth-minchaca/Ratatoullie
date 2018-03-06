package ar.edu.unlp.lifia.ratatoullie.service;

import java.util.List;

import ar.edu.unlp.lifia.ratatoullie.dto.CommentDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;


public interface CommentService {
	/**
	 * Makes a {@link Comment} about a {@link Restaurant}
	 * @param userMail
	 * @param text
	 * @param date
	 * @param restaurantName
	 * @param latitude
	 * @param longitude
	 * @param vote
	 */
	public CommentDto commentRestaurant(String userMail,String text, long date, String restaurantName,double latitude, double longitude, String vote) throws EntityNotExistsException;
	
	/**
	 * Makes a {@link Comment} about a {@link Menu}
	 * @param userMail
	 * @param text
	 * @param date
	 * @param restaurantName
	 * @param latitude
	 * @param longitude
	 * @param vote
	 */
	public CommentDto commentMenu(String userMail,String text, long date, String restaurantName,double latitude, double longitude, String vote) throws EntityNotExistsException;
	
	/**
	 * Returns a list of {@link Comment} of a given {@link Restaurant}
	 * @param restaurantName
	 * @param latitude
	 * @param longitude
	 */
	public List<CommentDto> getRestaurantComments(String restaurantName,double latitude, double longitude)throws EntityNotExistsException;
	
	/**
	 * Returns a list of {@link Comment} of a given {@link Menu}
	 * @param restaurantName
	 * @param latitude
	 * @param longitude
	 */
	public List<CommentDto> getRestaurantCurrentMenuComments(String restaurantName,double latitude, double longitude)throws EntityNotExistsException;
}
