package ar.edu.unlp.lifia.ratatoullie.service.impl;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ar.edu.unlp.lifia.ratatoullie.convert.Converter;
import ar.edu.unlp.lifia.ratatoullie.dao.MenuDao;
import ar.edu.unlp.lifia.ratatoullie.dao.NotificationDao;
import ar.edu.unlp.lifia.ratatoullie.dao.RatatoullieDao;
import ar.edu.unlp.lifia.ratatoullie.dao.UserDao;
import ar.edu.unlp.lifia.ratatoullie.dto.NotificationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RankingQuantityDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserResponsibleDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.LoginFailedException;
import ar.edu.unlp.lifia.ratatoullie.model.Commensal;
import ar.edu.unlp.lifia.ratatoullie.model.Gourmet;
import ar.edu.unlp.lifia.ratatoullie.model.Location;
import ar.edu.unlp.lifia.ratatoullie.model.Menu;
import ar.edu.unlp.lifia.ratatoullie.model.Notification;
import ar.edu.unlp.lifia.ratatoullie.model.Ranking;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;
import ar.edu.unlp.lifia.ratatoullie.model.Visitor;
import ar.edu.unlp.lifia.ratatoullie.service.UserService;
@Service
public class UserServiceImpl implements UserService { 
	@Autowired
	private RatatoullieDao ratatoullieDao;
	@Autowired
	UserDao userDao;	
	@Autowired
	NotificationDao notificationDao;	
	@Autowired
	MenuDao menuDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public NotificationDao getNotificationDao() {
		return notificationDao;
	}
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	public RatatoullieDao getRatatoullieDao() {
		return ratatoullieDao;
	}
	public void setRatatoullieDao(RatatoullieDao ratatoullieDao) {
		this.ratatoullieDao = ratatoullieDao;
	}

	@Override
	public UserDto createUser(String name, String lastName,  String email, String password, String password2, Double latitude, Double longitude) throws ImpossibleToCreateEntityException{
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(email);
		if (user == null) {
			if (password.equals(password2)) {
				user = ratatoullie.addUser(name, lastName, email, password, new Location(latitude, longitude));
				getUserDao().save(user);
				return Converter.convert(user);
			}
			throw new ImpossibleToCreateEntityException("Las contraseñas no coinciden");
		}
		throw new ImpossibleToCreateEntityException("El Usuario ya existe");
	}	

	@Override
	public User editUser(String email, String name, String lastName, Double latitude, Double longitude)
			throws ImpossibleToCreateEntityException, EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user =  ratatoullie.getUser(email);
		if (user != null) {
			user.setName(name);
			user.setLastName(lastName);
			user.getLocation().setLatitude(latitude);
			user.getLocation().setLongitude(longitude);
			getUserDao().update(user);
			return user;
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}

	@Override
	public UserDto getUser(String email) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(email);
		if(user != null){
			return Converter.convert(user);
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}

	@Override
	public UserDto changePass(String email, String oldPassword, String newPassword, String newPassword2) throws ImpossibleToCreateEntityException, EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(email);
//		if(user != null && user.isEnable()){
		if(user != null){		
			if ( user.isYourPassword(oldPassword) ) {
				if (newPassword.equals(newPassword2)) {
					user.setPassword(newPassword);
					getUserDao().update(user);
					return Converter.convert(user);
				}
				throw new ImpossibleToCreateEntityException("Las nuevas contraseñas no coinciden");
			}
			throw new ImpossibleToCreateEntityException("La contraseña actual ingresada es incorrecta");
		}
		throw new EntityNotExistsException("El Usuario no existe");			
	}

	@Override
	public List<UserMinDto> ListUser() {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		return Converter.convertListUserMin(ratatoullie.getUsers());
	}

	@Override
	public User login(String mail, String pass) throws LoginFailedException {	
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(mail);
		if (user!=null) {				
			if(user.isYourPassword(pass)){	
				return user;
			}	
		}			
		throw new LoginFailedException(" Login "+mail+" failed");
	}
	@Override
	public UserResponsibleDto getUserResponsible(String email) {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(email);
		return Converter.convert(responsible);
	}
	@Override
	public UserResponsibleDto createUserResponsible(String name, String lastName, String email, String password,
			String password2, Double latitude, Double longitude, String restName, Long restDate, Double restLatitude,
			Double restLongitude, MultipartFile image, String menuName, String description , MultipartFile uploadFileMenu ) throws Exception {

		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(email);
		if (responsible == null) {
			if (password.equals(password2)) {
				responsible = ratatoullie.addUserResponsible(name, lastName, email, password, new Location(latitude,longitude));
				Blob blob = getUserDao().createBlob(image);
				Restaurant restaurant = (Restaurant) responsible.addRestaurant(restName, restDate, new Location(restLatitude, restLongitude), blob);
				if (restaurant != null) {
					Blob blobMenu = getUserDao().createBlob(uploadFileMenu);
					restaurant.addMenu(menuName, description, new Date().getTime(), blobMenu);					
					getUserDao().save(responsible);
					return Converter.convert(responsible);
				}
				throw new EntityNotExistsException("El restaurante ya existe");
			}
			throw new ImpossibleToCreateEntityException("Las contraseñas no coinciden");
		}
		throw new EntityNotExistsException("El Usuario Responsable ya existe");

	}
	@Override
	public UserDto addFollow(String userMail, String followedMail) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(userMail);
		User userFollowed = (User) ratatoullie.getUser(followedMail);
		if(user != null &&userFollowed != null&& user.isEnable()&& userFollowed.isEnable()){
			user.follow(userFollowed);
			getUserDao().save(user);
			return Converter.convert(user);
		}
		throw new EntityNotExistsException("El Usuario no existe");			
	}
	@Override
	public UserDto stopFollow(String userMail, String followedMail) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(userMail);
		User userFollowed = (User) ratatoullie.getUser(followedMail);
		if(user != null &&userFollowed != null&& user.isEnable()){
			user.stopFollowing(userFollowed);
			getUserDao().save(user);
			return Converter.convert(user);
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}
	@Override
	public List<RankingQuantityDto> getQuantityUsersRank() {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		return Converter.convertListRankingQuantity(ratatoullie.getQuantityUsersRank());
	}
	@Override
	public User enableDisableUser(String userMail, Boolean state) {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(userMail);
		user.setEnable(state);
		getUserDao().update(user);
		return user;
	}

	@Override
	public List<UserMinDto> getTopUsersComments(int quantity){
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		return Converter.convertListUserMin( ratatoullie.getTopUsersComments(quantity));
	}
	@Override
	public NotificationDto markAsSeen(String userMail, long id) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(userMail);
		if (responsible != null) {
			Notification notification = getNotificationDao().get(id);
			if (notification != null) {
				if (!notification.isSeen()) {
					notification.setSeen(true);
					getNotificationDao().update(notification);
				}
				return Converter.convert(notification);
			}
			throw new EntityNotExistsException("La notificacion no existe");
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}
	@Override
	public UserResponsibleDto setUpNotification(String userMail, Boolean visitor, Boolean commensal, Boolean gourmet) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		UserResponsible responsible = (UserResponsible) ratatoullie.getUser(userMail);
		if (responsible != null) {
			Set<Ranking> blockedRanking = new HashSet<Ranking>() ;
			if (visitor) {
				blockedRanking.add(new Visitor());
			}
			if (commensal) {
				blockedRanking.add(new Commensal());
			}
			if (gourmet) {
				blockedRanking.add(new Gourmet());
			}
			responsible.setBlockedRanking(blockedRanking);
			getUserDao().update(responsible);
			return Converter.convert(responsible);
		}
		throw new EntityNotExistsException("El Usuario no existe");
	}
	@Override
	public UserDto recommendFriend(String userMail, long idMenu, String email, String text) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(userMail);
		User userRecommend = (User) ratatoullie.getUser(email);
		if(user != null && userRecommend != null && !userMail.equals(email) &&user.isEnable()&& userRecommend.isEnable()){
			Menu menu = getMenuDao().get(idMenu);
			user.recommend(menu, userRecommend, text, new Date().getTime());
			getUserDao().save(user);
			return Converter.convert(user);
		}
		throw new EntityNotExistsException("El Usuario no existe");			
	}
	@Override
	public UserDto recommendFriends(String userMail, long idMenu, String text) throws EntityNotExistsException {
		Ratatoullie ratatoullie = getRatatoullieDao().get(Ratatoullie.getInstance().getId());
		User user = (User) ratatoullie.getUser(userMail);
		if(user != null &&  user.isEnable()){
			Menu menu = getMenuDao().get(idMenu);
			user.recommendFollowers(menu, text, new Date().getTime());
			getUserDao().save(user);
			return Converter.convert(user);
		}
		throw new EntityNotExistsException("El Usuario no existe");			
	}

}
