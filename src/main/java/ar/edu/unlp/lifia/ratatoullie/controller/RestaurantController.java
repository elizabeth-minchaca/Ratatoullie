package ar.edu.unlp.lifia.ratatoullie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller()
public class RestaurantController {
	
	@RequestMapping(value={"/core/restaurant/list"}, method=RequestMethod.GET)
	public String restaurantList(){
		return "restaurantList";
	}
	@RequestMapping(value={"/core/restaurant/showRestaurant"}, method=RequestMethod.GET)
	public String showRestaurant(){
		return "showRestaurant";
	}
	@RequestMapping(value={"/core/userresponsible/myrestaurants"}, method=RequestMethod.GET)
	public String  restaurantsResponsibleGet(){
		return "myRestaurants";
	}
	@RequestMapping(value={"/core/userresponsible/registerRestaurant"}, method=RequestMethod.GET)
	public String  registerRestaurantGet(){
		return "registerRestaurant";
	}
	@RequestMapping(value={"/core/userresponsible/editRestaurant"}, method=RequestMethod.GET)
	public String  editRestaurantGet(){		
		return "editRestaurant";
	}
	@RequestMapping(value={"/core/userresponsible/viewRestaurant"}, method=RequestMethod.GET)
	public String  showRestaurantGet(){
		return "viewRestaurant";
	}
	@RequestMapping(value={"/core/userresponsible/registerMenu"}, method=RequestMethod.GET)
	public String  registerGet(){
		return "registerMenu";

	}
	@RequestMapping(value={"/core/userresponsible/editMenu"}, method=RequestMethod.GET)
	public String  registerResponsibleGet(){
		return "editMenu";
	}
	@RequestMapping(value={"/core/userresponsible/viewMenu"}, method=RequestMethod.GET)
	public String  showPerfilGet(){
		return "viewMenu";
	}
	@RequestMapping(value={"/core/restaurant/searchLocation"}, method=RequestMethod.GET)
	public String restaurantSearchLocation(){
		return "restaurantSearchLocation";
	}
	@RequestMapping(value={"/core/restaurant/searchComments"}, method=RequestMethod.GET)
	public String restaurantSearchComments(){
		return "restaurantSearchComments";
	}
	@RequestMapping(value={"/core/restaurant/search"}, method=RequestMethod.GET)
	public String restaurantSearchName(){
		return "searchName";
	}
	@RequestMapping(value={"/core/restaurant/showMenu"}, method=RequestMethod.GET)
	public String showMenu(){
		return "showMenu";
	}
}
