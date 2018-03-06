package ar.edu.unlp.lifia.ratatoullie.api;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ar.edu.unlp.lifia.ratatoullie.api.validator.UserApiValidator;
import ar.edu.unlp.lifia.ratatoullie.dto.NotificationDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RankingQuantityDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserMinDto;
import ar.edu.unlp.lifia.ratatoullie.dto.UserResponsibleDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.IncorrectParameterException;
import ar.edu.unlp.lifia.ratatoullie.exception.LoginFailedException;
import ar.edu.unlp.lifia.ratatoullie.exception.MyException;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.model.UserResponsible;
import ar.edu.unlp.lifia.ratatoullie.service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserApi {
	@Autowired
	UserApiValidator validator;	
	public UserApiValidator getValidator() {
		return validator;
	}
	public void setValidator(UserApiValidator validator) {
		this.validator = validator;
	}
	@Autowired
	private UserService userService;	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(HttpServletRequest request,@RequestParam String email, @RequestParam String password) throws LoginFailedException, IncorrectParameterException{
		if(getValidator().login(email, password)){			
			HttpSession sesion = request.getSession(true);
			if(sesion.getAttribute("user")==null){
				User user = getUserService().login(email, password);
				sesion.setAttribute("user", user);
				try {
					sesion.setAttribute("userResponsible",(UserResponsible) user);
				} catch (ClassCastException e) {
				}
			}
		}else{
			throw new IncorrectParameterException("Parametros incorrectos");
		}		
	}	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request){
		request.getSession().invalidate();
	}
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ResponseEntity<UserDto> submitUserRegister(@RequestParam String name, @RequestParam String lastName, @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String email, @RequestParam String password, @RequestParam String password2) throws ImpossibleToCreateEntityException, IncorrectParameterException{
		if(getValidator().submitUserRegister(name, lastName, latitude, longitude, email, password, password2)){	
			return new ResponseEntity<UserDto>(getUserService().createUser(name, lastName, email, password, password2, latitude, longitude),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}	
	@RequestMapping(value="/registerResponsible",method=RequestMethod.POST)
	public ResponseEntity<UserResponsibleDto> submitResponsibleRegister(HttpServletRequest request, @RequestParam String name,@RequestParam String lastName,@RequestParam String email, @RequestParam String password, String password2,@RequestParam Double latitude,@RequestParam Double longitude, @RequestParam String restName, @RequestParam Long restDate, @RequestParam Double restLatitude,@RequestParam Double restLongitude, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, @RequestParam String menuName, @RequestParam String description ,@RequestParam(value = "uploadFileMenu", required = false) MultipartFile uploadFileMenu  ) throws ImpossibleToCreateEntityException, EntityNotExistsException, Exception{	
		if (getValidator().submitResponsibleRegister(name, lastName, email, password, password2, latitude, longitude, restName, restDate, restLatitude, restLongitude, menuName, description)) {
			return new ResponseEntity<UserResponsibleDto>(getUserService().createUserResponsible( name, lastName, email, password, password2, latitude, longitude, restName, restDate, restLatitude, restLongitude, uploadFile, menuName, description, uploadFileMenu),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");	
	}	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public ResponseEntity<UserDto> changePassword(HttpServletRequest request,@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam  String newPassword2) throws ImpossibleToCreateEntityException, EntityNotExistsException, IncorrectParameterException{
		if (getValidator().changePassword(oldPassword, newPassword, newPassword2)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<UserDto>(getUserService().changePass(user.getMail(), oldPassword, newPassword, newPassword2),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/enableDisableUser",method=RequestMethod.POST)
	public ResponseEntity<UserDto> enableDisableUser(HttpServletRequest request, @RequestParam Boolean state) throws EntityNotExistsException{				
		User user= (User)request.getSession().getAttribute("user");				
		if(user != null){
			User user2 = getUserService().enableDisableUser(user.getMail(), state);
			request.getSession().setAttribute("user", user2);
			try {
				request.getSession().setAttribute("userResponsible",(UserResponsible) user2);
			} catch (ClassCastException e) {
			}
			return getMy(request);
		}
		return new ResponseEntity<UserDto>(new UserDto(),HttpStatus.OK);				
	}
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	public ResponseEntity<UserDto> submitEditUser(HttpServletRequest request, @RequestParam String name, @RequestParam String lastName, @RequestParam Double latitude, @RequestParam Double longitude) throws ImpossibleToCreateEntityException, EntityNotExistsException, IncorrectParameterException{
		if (getValidator().submitEditUser(name, lastName, latitude, longitude)){
			User user = (User) request.getSession().getAttribute("user");
			User newUser = getUserService().editUser(user.getMail(), name, lastName, latitude, longitude);
			request.getSession().setAttribute("user", newUser);
			try {
				request.getSession().setAttribute("userResponsible", (UserResponsible) newUser);
			} catch (ClassCastException e) {
			}
			return getMy(request);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}	
	@RequestMapping(value="/getMy", method=RequestMethod.GET)
	public ResponseEntity<UserDto> getMy(HttpServletRequest request) throws EntityNotExistsException{			
		User user= (User)request.getSession().getAttribute("user");		
		if(request.getSession().getAttribute("userResponsible") != null){
			return new ResponseEntity<UserDto>(getUserService().getUserResponsible(user.getMail()),HttpStatus.OK);
		}else{
			return new ResponseEntity<UserDto>(getUserService().getUser(user.getMail()),HttpStatus.OK);
		}
	}
	@RequestMapping(value="/addFollow", method=RequestMethod.POST)
	public ResponseEntity<UserDto> addFollow(HttpServletRequest request, @RequestParam String mail) throws EntityNotExistsException, IncorrectParameterException{		
		if (getValidator().follow(mail)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<UserDto>(getUserService().addFollow(user.getMail(), mail),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/stopFollow", method=RequestMethod.POST)
	public ResponseEntity<UserDto> stopFollow(HttpServletRequest request, @RequestParam String mail) throws EntityNotExistsException, IncorrectParameterException{
		if (getValidator().follow(mail)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<UserDto>(getUserService().stopFollow(user.getMail(), mail),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/quantityUsersRank",method=RequestMethod.GET)
	public ResponseEntity<List<RankingQuantityDto>> listAll() throws UnsupportedEncodingException, SQLException {		
		return new ResponseEntity<List<RankingQuantityDto>>(getUserService().getQuantityUsersRank(), HttpStatus.OK);			
	}
	@RequestMapping(value="/topUsersComments", method=RequestMethod.GET)
	public ResponseEntity<List<UserMinDto>> topUsersComments(HttpServletRequest request, @RequestParam int quantity) throws IncorrectParameterException{
		if(getValidator().topUsersComments(quantity)){
			return new ResponseEntity<List<UserMinDto>>(getUserService().getTopUsersComments(quantity),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
		
	}
	@ExceptionHandler(IncorrectParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleIncorrectParameterException(MyException ex){	 
		return ex.getMessage();	 
	}
	@ExceptionHandler(MyException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleMyException(MyException ex){	 
		return ex.getMessage();	 
	}
	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleIoException(IOException ex){	 
		return "Error Interno";	 
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception ex){	 
		ex.printStackTrace();
		return "Error Interno";	 
	}
	
	@RequestMapping(value="/markAsSeen/{id}", method=RequestMethod.GET)
	public ResponseEntity<NotificationDto> markAsSeen(HttpServletRequest request, @PathVariable ("id") long id) throws EntityNotExistsException{
		User user= (User)request.getSession().getAttribute("user");
		return new ResponseEntity<NotificationDto>(getUserService().markAsSeen(user.getMail(), id),HttpStatus.OK);
	}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ResponseEntity<List<UserMinDto>> list() throws IncorrectParameterException{
		
			return new ResponseEntity<List<UserMinDto>>(getUserService().ListUser(),HttpStatus.OK);
		
		
	}
	@RequestMapping(value="/setUpNotification",method=RequestMethod.POST)
	public ResponseEntity<UserResponsibleDto> setUpNotification(HttpServletRequest request, @RequestParam Boolean visitor, @RequestParam Boolean commensal, @RequestParam Boolean gourmet) throws ImpossibleToCreateEntityException, EntityNotExistsException, IncorrectParameterException{
		if (getValidator().setUpNotification(visitor, commensal, gourmet)){
			User user = (User) request.getSession().getAttribute("user");
			return new ResponseEntity<UserResponsibleDto>(getUserService().setUpNotification(user.getMail(), visitor, commensal, gourmet), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}	
	@RequestMapping(value="/recommendFriend", method=RequestMethod.POST)
	public ResponseEntity<UserDto> recommendFriend(HttpServletRequest request, @RequestParam long idMenu, @RequestParam String email, @RequestParam String text) throws EntityNotExistsException, IncorrectParameterException{
		if (getValidator().recommendFriend(idMenu, email, text)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<UserDto>(getUserService().recommendFriend(user.getMail(), idMenu, email, text),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/recommendFriends", method=RequestMethod.POST)
	public ResponseEntity<UserDto> recommendFriends(HttpServletRequest request, @RequestParam long idMenu, @RequestParam String text) throws EntityNotExistsException, IncorrectParameterException{
		if (getValidator().recommendFriends(idMenu, text)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<UserDto>(getUserService().recommendFriends(user.getMail(), idMenu, text),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
}