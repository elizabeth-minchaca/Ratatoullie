package ar.edu.unlp.lifia.ratatoullie.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlp.lifia.ratatoullie.dto.NotificationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RankingQuantityDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserResponsibleDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.LoginFailedException;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;
import ar.edu.unlp.lifia.ratatoullie.model.Menu;
import ar.edu.unlp.lifia.ratatoullie.model.User;

public interface UserService {
	
	public UserDto createUser(String name, String lastName, String email, String password, String password2, Double latitude, Double longitude) throws ImpossibleToCreateEntityException;
	public User editUser(String email, String name, String lastName, Double latitude, Double longitude)throws ImpossibleToCreateEntityException, EntityNotExistsException;
	public UserDto getUser(String email) throws EntityNotExistsException;
	public UserDto changePass(String email, String oldPassword, String newPassword, String newPassword2) throws ImpossibleToCreateEntityException, EntityNotExistsException;
	
	/**
	 * Returns a list of {@link User}
	 */
	public List<UserMinDto> ListUser();	
	public User login(String mail, String pass) throws LoginFailedException;
	
	public UserResponsibleDto getUserResponsible(String mail);
	public UserResponsibleDto createUserResponsible(String name, String lastName, String email, String password, String password2, Double latitude, Double longitude, String restName, Long restDate, Double restLatitude, Double restLongitude, MultipartFile image, String menuName, String description , MultipartFile uploadFileMenu) throws ImpossibleToCreateEntityException, EntityNotExistsException, Exception;

	/**
	 * Adds a {@link User} to the followers list of this user.
	 * @param userMail
	 * @param followedMail
	 * @return user
	 */
	public UserDto addFollow(String userMail, String followedMail) throws EntityNotExistsException;
	
	/**
	 * Remove a {@link User} from the followers list of this user.
	 * @param userMail
	 * @param followedMail
	 * @return user
	 */
	public UserDto stopFollow(String userMail, String followedwMail) throws EntityNotExistsException;	
	
	/**
	 * Returns a list of the number of {@link User} per {@link Ranking}.
	 */
	public List<RankingQuantityDto> getQuantityUsersRank();
	
	/**
	 * Returns a list of {@link User} who have more {@link Comment}
	 * @param quantity limits the number of results.
	 */
	public List<UserMinDto> getTopUsersComments(int quantity);
	
	/**
	 * Enable or Disable a {@link User} according to his state.
	 * @param userMail
	 * @param state
	 */
	public User enableDisableUser(String userMail, Boolean state);
	
	
	public NotificationDto markAsSeen(String userMail, long id) throws EntityNotExistsException;
	
	/**
	 * Sets up whether a {@link UserResponsible} wants to receive the {@link Notification} of certain users {@link Ranking} or not.
	 * @param userMail
	 * @param visitor
	 * @param commensal
	 * @param gourmet
	 */
	public UserResponsibleDto setUpNotification(String userMail, Boolean visitor, Boolean commensal, Boolean gourmet) throws EntityNotExistsException;
	
	/**
	 * Recommends a {@link Menu} to one of his friends.
	 * @param userMail
	 * @param idMenu
	 * @param email
	 * @param text
	 */
	public UserDto recommendFriend(String userMail, long idMenu, String email, String text) throws EntityNotExistsException;
	
	/**
	 * Recommends a {@link Menu} to all his friends.
	 * @param userMail
	 * @param idMenu
	 * @param text
	 */
	public UserDto recommendFriends(String userMail, long idMenu, String text) throws EntityNotExistsException;

}
