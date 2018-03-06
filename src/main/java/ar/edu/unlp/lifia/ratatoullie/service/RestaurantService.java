package ar.edu.unlp.lifia.ratatoullie.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlp.lifia.ratatoullie.dto.MenuDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantMinDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.NotPermissionException;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;

public interface RestaurantService {
	/**
	 * Returns a list of {@link Restaurant} ordered by those that have the TOP {@link Benefit}
	 *
	 */
	public List<RestaurantMinDto> list() throws UnsupportedEncodingException, SQLException;
	
	/**
	 * Returns a list of {@link Restaurant} within a certain distance of a {@link Location} 
	 * @param latitude
	 * @param longitude
	 * @param distance
	 */
	public List<RestaurantDto> search(double latitude, double longitude, double distance) throws UnsupportedEncodingException, SQLException;
	
	/**
	 * Returns all the {@link Restaurant} of a {@link UserResponsible}.
	 * @param userMail
	 */
	public List<RestaurantDto> getRestaurants(String userMail) throws EntityNotExistsException, UnsupportedEncodingException, SQLException;
	
	/**
	 * Returns the {@link Restaurant} of a {@link UserResponsible} by its id.
	 * @param id
	 * @param userMail
	 */
	public RestaurantDto getMyRestaurant(long id, String userMail)throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException;
	
	/**
	 * Returns a {@link Restaurant} by its id.
	 * @param id
	 */
	public RestaurantDto getRestaurant(long id)throws EntityNotExistsException, UnsupportedEncodingException, SQLException;
	
	
	public long createRestaurant(String userMail, String name, long openigDate, double latitude, double longitude,
			MultipartFile imageRest, String menuName, String description, MultipartFile imageMenu)throws EntityNotExistsException, ImpossibleToCreateEntityException, NotPermissionException, SQLException,
			IOException;
	public long editRestaurant(String userMail, long idRest, String name, long openigDate, double latitude,
			double longitude, MultipartFile uploadFile)throws  EntityNotExistsException,ImpossibleToCreateEntityException, NotPermissionException, SQLException, IOException;
	public MenuDto addMenu(String userMail,  long idRest, String name, String description, MultipartFile uploadFile) throws EntityNotExistsException, NotPermissionException, IOException;
	public MenuDto getMyMenu(String userMail, long idRest, long idMenu) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException;
	public MenuDto editMenu(String userMail, long idRest,long idMenu, String name, String description, MultipartFile uploadFile)	throws EntityNotExistsException, NotPermissionException,IOException;
	
	/**
	 * Returns a list of a certain number of {@link Restaurant} within an initial and final date.
	 * @param initialDate
	 * @param finalDate
	 * @param quantity
	 */
	public List<RestaurantMinDto> getRestaurantsTopComments(long initialDate, long finalDate, int quantity);
	public MenuDto getMenu(long idRest, long id) throws EntityNotExistsException, UnsupportedEncodingException, SQLException;
	
	public MenuDto getMenuPublic(long id) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException;
	
	/**
	 * Returns a list of {@link Restaurant} that have a given name.
	 * @param name
	 */
	public List<RestaurantMinDto> searchRestaurants(String name);

}
