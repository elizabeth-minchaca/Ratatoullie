package ar.edu.unlp.lifia.ratatoullie.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import ar.edu.unlp.lifia.ratatoullie.model.User;

@WebFilter(urlPatterns={"/core/user/setUpNotification","/core/userresponsible/registerRestaurant","/core/userresponsible/editRestaurant",
		"/core/userresponsible/registerMenu","/core/userresponsible/editMenu"})
public class UserEnableFilter implements Filter {
	
	private static final String INDEX = "/core/index";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession sesion = ((HttpServletRequest)request).getSession(true);
		if ((sesion.getAttribute("user") != null)){
			User user = ((User)sesion.getAttribute("user"));
			if (user == null){
				request.getRequestDispatcher(INDEX).forward(request, response);
			}else {
				if(user.isEnable()){
					chain.doFilter(request, response);
				}else{
					request.getRequestDispatcher(INDEX).forward(request, response);
				}				
			}						
		}else {
			request.getRequestDispatcher(INDEX).forward(request, response);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}