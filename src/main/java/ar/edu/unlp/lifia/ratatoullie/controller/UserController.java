package ar.edu.unlp.lifia.ratatoullie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller()
public class UserController {

	@RequestMapping(value={"/core/register"}, method=RequestMethod.GET)
	public String  registerGet(){
		return "registerUser";

	}
	@RequestMapping(value={"/core/registerResponsible"}, method=RequestMethod.GET)
	public String  registerResponsibleGet(){
		return "registerResponsible";

	}
	@RequestMapping(value={"/core/user/showPerfil"}, method=RequestMethod.GET)
	public String  showPerfilGet(){
		return "showUserPerfil";

	}
	@RequestMapping(value={"/core/user/editUser"}, method=RequestMethod.GET)
	public String  editUserGet(){
		return "editUser";

	}
	@RequestMapping(value={"/core/user/friends"}, method=RequestMethod.GET)
	public String friends(){
		return "friends";
	}
	
	@RequestMapping(value={"/core/user/notifications"}, method=RequestMethod.GET)
	public String notifications(){
		return "notifications";
	}
	@RequestMapping(value={"/core/user/viewNotification"}, method=RequestMethod.GET)
	public String showNotification(){
		return "viewNotification";
	}
	@RequestMapping(value={"/core/user/setUpNotification"}, method=RequestMethod.GET)
	public String setUpNotification(){
		return "setUpNotification";
	}

}
