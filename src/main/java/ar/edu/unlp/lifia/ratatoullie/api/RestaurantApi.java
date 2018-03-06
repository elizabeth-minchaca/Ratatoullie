package ar.edu.unlp.lifia.ratatoullie.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

import ar.edu.unlp.lifia.ratatoullie.api.validator.RestaurantApiValidator;
import ar.edu.unlp.lifia.ratatoullie.dto.MenuDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantDto;
import ar.edu.unlp.lifia.ratatoullie.dto.RestaurantMinDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.exception.IncorrectParameterException;
import ar.edu.unlp.lifia.ratatoullie.exception.MyException;
import ar.edu.unlp.lifia.ratatoullie.exception.NotPermissionException;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.service.RestaurantService;

@RestController
@RequestMapping(value="/api/restaurant")
public class RestaurantApi {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RestaurantApiValidator validator;	
	public RestaurantApiValidator getValidator() {
		return validator;
	}
	public void setValidator(RestaurantApiValidator validator) {
		this.validator = validator;
	}
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseEntity<List<RestaurantMinDto>> listAll() throws UnsupportedEncodingException, SQLException {		
		return new ResponseEntity<List<RestaurantMinDto>>( getRestaurantService().list(), HttpStatus.OK);			
	}
	@RequestMapping(value="/searchDistance",method=RequestMethod.GET)
	public ResponseEntity<List<RestaurantDto>> searchDistance(@RequestParam double latitude,@RequestParam double longitude,@RequestParam double distance) throws UnsupportedEncodingException, SQLException, IncorrectParameterException{
		if(getValidator().searchDistance(latitude, longitude, distance)){
			return new ResponseEntity<List<RestaurantDto>>(getRestaurantService().search(latitude, longitude, distance),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/create", method=RequestMethod.POST)
	public ResponseEntity<RestaurantDto> createRestaurant(HttpServletRequest  request, @RequestParam String restaurant,@RequestParam long date, @RequestParam double latitude, @RequestParam double longitude, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, @RequestParam String menuName,@RequestParam String description ,@RequestParam(value = "uploadFileMenu", required = false) MultipartFile uploadFileMenu  ) throws EntityNotExistsException, ImpossibleToCreateEntityException, NotPermissionException, SQLException, IOException, IncorrectParameterException{
		if(getValidator().createRestaurant(restaurant, date, latitude, longitude, menuName, description)){
			User user= (User)request.getSession().getAttribute("user");
			long id= getRestaurantService().createRestaurant(user.getMail(), restaurant, date, latitude, longitude,uploadFile, menuName, description, uploadFileMenu);
			return new ResponseEntity<RestaurantDto>(getRestaurantService().getMyRestaurant(id,user.getMail()),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}	
	@RequestMapping(value="/showRestaurant/{id}", method=RequestMethod.GET)
	public ResponseEntity<RestaurantDto> showRestaurant(HttpServletRequest request, @PathVariable ("id") long idRest) throws EntityNotExistsException, UnsupportedEncodingException, SQLException, IncorrectParameterException {
		if(getValidator().showRestaurant(idRest)){
			return new ResponseEntity<RestaurantDto>(getRestaurantService().getRestaurant(idRest), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/edit", method=RequestMethod.POST)
	public ResponseEntity<RestaurantDto> editRestaurant(HttpServletRequest  request,@RequestParam long idRest, @RequestParam String restaurant,@RequestParam long date, @RequestParam double latitude, @RequestParam double longitude, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile ) throws EntityNotExistsException, ImpossibleToCreateEntityException, NotPermissionException, SQLException, IOException, IncorrectParameterException{
		if(getValidator().editRestaurant(idRest, restaurant, date, latitude, longitude)){
			User user= (User)request.getSession().getAttribute("user");
			long id= getRestaurantService().editRestaurant(user.getMail(),idRest, restaurant, date, latitude, longitude,uploadFile);
			return new ResponseEntity<RestaurantDto>(getRestaurantService().getMyRestaurant(id,user.getMail()),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/addmenu",method=RequestMethod.POST)
	public ResponseEntity<MenuDto> addMenu(HttpServletRequest request,@RequestParam long idRest, @RequestParam String name,@RequestParam String description, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) throws EntityNotExistsException, NotPermissionException, IOException, IncorrectParameterException{
		if(getValidator().addMenu(idRest, name, description)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<MenuDto>(getRestaurantService().addMenu(user.getMail(), idRest, name, description, uploadFile),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/editmenu",method=RequestMethod.POST)
	public ResponseEntity<MenuDto> editMenu(HttpServletRequest request,@RequestParam long idRest,@RequestParam long idMenu, @RequestParam String name,@RequestParam String description, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) throws EntityNotExistsException, NotPermissionException, IOException, IncorrectParameterException{
		if(getValidator().editMenu(idRest, idMenu, name, description)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<MenuDto>(getRestaurantService().editMenu(user.getMail(), idRest, idMenu, name, description, uploadFile),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/myrestaurants",method=RequestMethod.GET)
	public ResponseEntity<List<RestaurantDto>> myRestaurants(HttpServletRequest request) throws EntityNotExistsException, UnsupportedEncodingException, SQLException {		
		User user= (User)request.getSession().getAttribute("user");
		return new ResponseEntity<List<RestaurantDto>>(getRestaurantService().getRestaurants(user.getMail()), HttpStatus.OK);
	}
	@RequestMapping(value="/userresponsible/getrestaurant/{id}",method=RequestMethod.GET)
	public ResponseEntity<RestaurantDto> getRestaurant(HttpServletRequest request, @PathVariable ("id") long id) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException, IncorrectParameterException {		
		if(getValidator().getRestaurant(id)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<RestaurantDto>(getRestaurantService().getMyRestaurant(id, user.getMail()), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/userresponsible/getmenu/{idRest}/{idMenu}",method=RequestMethod.GET)
	public ResponseEntity<MenuDto> getMenu(HttpServletRequest request, @PathVariable ("idRest") long idRest,  @PathVariable ("idMenu") long idMenu) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException, IncorrectParameterException {		
		if(getValidator().getMenu(idRest, idMenu)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<MenuDto>(getRestaurantService().getMyMenu( user.getMail(),idRest,idMenu), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/showMenu/{idRe}/{idMe}",method=RequestMethod.GET)
	public ResponseEntity<MenuDto> showMenu(HttpServletRequest request, @PathVariable ("idRe") long idRe,  @PathVariable ("idMe") long idMe) throws EntityNotExistsException, UnsupportedEncodingException, SQLException, IncorrectParameterException {		
		if(getValidator().getMenu(idRe, idMe)){
			return new ResponseEntity<MenuDto>(getRestaurantService().getMenu(idRe,idMe), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/topComments",method=RequestMethod.GET)
	public ResponseEntity<List<RestaurantMinDto>> getRestaurantsTopComments(@RequestParam long initialDate,@RequestParam long finalDate,@RequestParam int quantity) throws IncorrectParameterException{
		if(getValidator().getRestaurantsTopComments(initialDate, finalDate, quantity)){
			return new ResponseEntity<List<RestaurantMinDto>>(getRestaurantService().getRestaurantsTopComments(initialDate, finalDate, quantity),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	
	//FALTA VALIDAR
	@RequestMapping(value="/getMenu/{id}",method=RequestMethod.GET)
	public ResponseEntity<MenuDto> getMenu2(HttpServletRequest request,  @PathVariable ("id") long id) throws EntityNotExistsException, NotPermissionException, UnsupportedEncodingException, SQLException, IncorrectParameterException {		
		if(getValidator().getMenu(id)){
			return new ResponseEntity<MenuDto>(getRestaurantService().getMenuPublic(id), HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ResponseEntity<List<RestaurantMinDto>> search(String name) throws IncorrectParameterException{
		if(getValidator().search(name)){
		return new ResponseEntity<List<RestaurantMinDto>>( getRestaurantService().searchRestaurants(name), HttpStatus.OK);
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
}
