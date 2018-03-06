package ar.edu.unlp.lifia.ratatoullie.api;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.lifia.ratatoullie.api.validator.CommentApiValidator;
import ar.edu.unlp.lifia.ratatoullie.dto.CommentDto;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.exception.IncorrectParameterException;
import ar.edu.unlp.lifia.ratatoullie.exception.MyException;
import ar.edu.unlp.lifia.ratatoullie.model.User;
import ar.edu.unlp.lifia.ratatoullie.service.CommentService;

@RestController
@RequestMapping(value="/api/comment")
public class CommentApi {
	@Autowired
	CommentService commentService;	
	@Autowired
	CommentApiValidator validator;

	public CommentApiValidator getValidator() {
		return validator;
	}
	public void setValidator(CommentApiValidator validator) {
		this.validator = validator;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	@RequestMapping(value="/commentrestaurant",method=RequestMethod.POST)
	public ResponseEntity<CommentDto> commentRestaurant(HttpServletRequest request,@RequestParam String restaurant, @RequestParam String text, @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String vote, @RequestParam long date) throws EntityNotExistsException, IncorrectParameterException{
		if(getValidator().comment(restaurant, text, latitude, longitude, vote, date)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<CommentDto>(getCommentService().commentRestaurant(user.getMail(), text, date, restaurant, latitude, longitude, vote),HttpStatus.OK);
		}
		throw new IncorrectParameterException("Parametros incorrectos");
	}
	@RequestMapping(value="/commentmenu",method=RequestMethod.POST)
	public ResponseEntity<CommentDto> commentMenu(HttpServletRequest request,@RequestParam String restaurant, @RequestParam String text, @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String vote, @RequestParam long date) throws EntityNotExistsException, IncorrectParameterException{	
		if(getValidator().comment(restaurant, text, latitude, longitude, vote, date)){
			User user= (User)request.getSession().getAttribute("user");
			return new ResponseEntity<CommentDto>(getCommentService().commentMenu(user.getMail(), text, date, restaurant, latitude, longitude, vote),HttpStatus.OK);
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
		return "Error Interno";	 
	}	
}