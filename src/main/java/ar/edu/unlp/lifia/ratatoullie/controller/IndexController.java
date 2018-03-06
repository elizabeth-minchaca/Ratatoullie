package ar.edu.unlp.lifia.ratatoullie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class IndexController {

	@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView model = new ModelAndView();
		model.setViewName("templates/template");
		model.addObject("title", "Inicio");
		model.addObject("site", "Ratatoullie");
		return model;
	}		
	@RequestMapping(value={"/core/index"}, method=RequestMethod.GET)
	public String indexCore(HttpServletRequest request){
		if(request.getSession().getAttribute("user") != null){
			return "home";
		}
		return "index";
	}
}