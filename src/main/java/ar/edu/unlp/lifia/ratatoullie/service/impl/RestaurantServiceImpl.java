package ar.edu.unlp.lifia.ratatoullie.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlp.lifia.ratatoullie.convert.Converter;
import ar.edu.unlp.lifia.ratatoullie.dao.RatatoullieDao;
import ar.edu.unlp.lifia.ratatoullie.dao.RestaurantDao;
import ar.edu.unlp.lifia.ratatoullie.dao.MenuDao;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.MenuDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.NotPermissionException;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Menu;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;
import ar.edu.unlp.lifia.ratatoullie.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RatatoullieDao ratatoullieDao;
	@Autowired
	RestaurantDao restaurantDao;
	@Autowired
	MenuDao menuDao;

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

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public long createRestaurant(String userMail, String name, long openigDate, double latitude,
			double longitude, MultipartFile imageRest, String menuName, String description, MultipartFile imageMenu) throws EntityNotExistsException,
			ImpossibleToCreateEntityException, NotPermissionException, SQLException, IOException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible userResponsible = (UserResponsible) ratatoullie.getUser(userMail);
		if (userResponsible != null && userResponsible.isEnable()) {
			if (userResponsible.getMail().equals(userMail)) {
				Blob blobRest = getRestaurantDao().createBlob(imageRest);
				Restaurant restaurant = userResponsible.addRestaurant(name, openigDate,	new Location(latitude, longitude), blobRest);
				if (restaurant != null) {
					Blob blobMenu = getRestaurantDao().createBlob(imageMenu);
					restaurant.addMenu(menuName, description, new Date().getTime(), blobMenu);
					getRestaurantDao().save(restaurant);
					return restaurant.getId();
				}
				throw new ImpossibleToCreateEntityException("El restaurante Existe");
			}
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}

	@Override
	public long editRestaurant(String userMail, long idRest, String name, long date, double latitude,
			double longitude, MultipartFile uploadFile) throws EntityNotExistsException,
			ImpossibleToCreateEntityException, NotPermissionException, SQLException, IOException {

		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible userResponsible = (UserResponsible) ratatoullie.getUser(userMail);
		
			if (userResponsible.getMail().equals(userMail) && userResponsible != null && userResponsible.isEnable()) {
				Restaurant restaurant = ratatoullie.getRestaurant(idRest);
				if (restaurant != null) {
					if (restaurant.getOwner().getMail().equals(userMail)) {
							restaurant.setName(name);
							restaurant.setOpeningDate(date);
							restaurant.getLocation().setLatitude(latitude);
							restaurant.getLocation().setLongitude(longitude);
							if (uploadFile != null) {
								Blob blob = getRestaurantDao().createBlob(uploadFile);
								restaurant.setImage(blob);
							}
							getRestaurantDao().update(restaurant);
							return idRest;
					}
					throw new ImpossibleToCreateEntityException("No es el dueño.");
				}
				throw new ImpossibleToCreateEntityException("El restaurante no existe.");				
			}
			throw new NotPermissionException("No tiene permisos.");
		
	}

	@Override
	public List<RestaurantMinDto> list() throws UnsupportedEncodingException, SQLException {
		return Converter.convertListRestaurantMin(Ratatoullie.getInstance().order(getRestaurantDao().list()));
	}

	@Override
	public List<RestaurantDto> search(double latitude, double longitude, double distance)
			throws UnsupportedEncodingException, SQLException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		return Converter.convertListRestaurant(ratatoullie.search(new Location(latitude, longitude), distance));
	}

	@Override
	public MenuDto addMenu(String userMail, long idRest, String name, String description, MultipartFile uploadFile)
			throws EntityNotExistsException, NotPermissionException, IOException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(userMail);		
		Restaurant restaurant = ratatoullie.getRestaurant(idRest);
		if (restaurant != null) {
			if (restaurant.getOwner().getMail().equals(responsible.getMail()) && responsible.isEnable()) {
				Blob blob = getRestaurantDao().createBlob(uploadFile);
				Menu actMenu=restaurant.getCourentMenu();
				Menu menu = restaurant.addMenu(name, description, new Date().getTime(), blob);
				getMenuDao().save(actMenu);
				getMenuDao().save(menu);
				return Converter.convert(menu);
			}
			throw new NotPermissionException("El Restaurante no es suyo");
		}
		throw new EntityNotExistsException("El Restaurante no existe");
	}

	@Override
	public MenuDto editMenu(String userMail, long idRest, long idMenu, String name, String description, MultipartFile uploadFile)
			throws EntityNotExistsException, NotPermissionException, IOException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(userMail);				
		Restaurant restaurant = ratatoullie.getRestaurant(idRest);
		Menu menu = getMenuDao().get(idMenu);
		if (restaurant != null && menu !=null && responsible.isEnable() && responsible.getMail().equals(restaurant.getOwner().getMail())) {
			if (menu.getRestaurant().getOwner().getMail().equals(userMail)) {
				menu.setText(name);
				menu.setDescription(description);
				if (uploadFile != null) {
					Blob blob = getMenuDao().createBlob(uploadFile);
					menu.setImage(blob);
				}
				getMenuDao().update(menu);
				return Converter.convert(menu);
			}
			throw new NotPermissionException("El Restaurante no es suyo");
		}
		throw new EntityNotExistsException("El Menu no existe");
	}
	
	@Override
	public List<RestaurantDto> getRestaurants(String mail)
			throws EntityNotExistsException, UnsupportedEncodingException, SQLException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible userResponsible = (UserResponsible) ratatoullie.getUser(mail);
		if (userResponsible != null) {
			return Converter.convertListRestaurant(userResponsible.getRestaurants());
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}

	@Override
	public RestaurantDto getMyRestaurant(long id, String mail)
			throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible userResponsible = (UserResponsible) ratatoullie.getUser(mail);
		if (userResponsible != null) {
			Restaurant restaurant = getRestaurantDao().get(id);
			if (restaurant != null) {
				if (restaurant.getOwner().getMail().equals(mail)) {
					return Converter.convert(restaurant);
				}
				throw new NotPermissionException("El Restaurante no es suyo");
			}
			throw new EntityNotExistsException("El restaurante no existe");
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}
	
	@Override
	public RestaurantDto getRestaurant(long id) throws EntityNotExistsException, UnsupportedEncodingException, SQLException {
		Restaurant restaurant = getRestaurantDao().get(id);
		if (restaurant != null) {
			return Converter.convert(restaurant);
		}
		throw new EntityNotExistsException("El restaurante no existe");
	}
	@Override
	public MenuDto getMenuPublic(long id) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException {
		Menu menu = getMenuDao().get(id);
		if (menu != null){
				return Converter.convert(menu);
		}
		throw new EntityNotExistsException("El menu no existe");
	}	
	@Override
	public MenuDto getMyMenu( String mail, long idRest,long idMenu)
			throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible userResponsible = (UserResponsible) ratatoullie.getUser(mail);
		if (userResponsible != null) {
			Restaurant restaurant = getRestaurantDao().get(idRest);
			Menu menu = getMenuDao().get(idMenu);
			if (menu != null && restaurant != null) {
				if (menu.getRestaurant().getOwner().getMail().equals(mail) && menu.getRestaurant().getId() == idRest) {
					return Converter.convert(menu);
				}
				throw new NotPermissionException("El Restaurante no es suyo");
			}
			throw new EntityNotExistsException("El restaurante no existe");
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}
	
	@Override
	public MenuDto getMenu(long idRest, long id) throws EntityNotExistsException, UnsupportedEncodingException, SQLException {
		Restaurant restaurant = getRestaurantDao().get(idRest);
		Menu menu = getMenuDao().get(id);
		if (menu != null && restaurant != null) {
			if (menu.getRestaurant().getId() == idRest) {
				return Converter.convert(menu);
			}
		}
		throw new EntityNotExistsException("El restaurante no existe");
	}
	
	@Override
	public List<RestaurantMinDto> getRestaurantsTopComments(long initialDate,long finalDate, int quantity){
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		return Converter.convertListRestaurantMinDate(ratatoullie.getRestaurantsTopComments(initialDate, finalDate, quantity),initialDate,finalDate);
	}
	@Override
	public List<RestaurantMinDto> searchRestaurants(String name){
		return Converter.convertListRestaurantMin(Ratatoullie.getInstance().order(getRestaurantDao().list(name)));
	}
}