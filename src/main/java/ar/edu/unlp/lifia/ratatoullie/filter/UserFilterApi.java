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

import org.springframework.http.HttpStatus;

import ar.edu.unlp.lifia.ratatoullie.model.User;

@WebFilter(urlPatterns={"/api/user/enableDisableUser","/api/user/editUser","/api/user/getMy",})
public class UserFilterApi implements Filter {
	
	private static final int UNAUTHORIZED= HttpStatus.UNAUTHORIZED.value();
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession sesion = ((HttpServletRequest)request).getSession(true);
		if ((sesion.getAttribute("user") != null)){
			User user = ((User)sesion.getAttribute("user"));
			if (user == null){
				((HttpServletResponse)response).sendError(UNAUTHORIZED);
			    return;
			}else {
				chain.doFilter(request, response);
			}				
		}else {
			((HttpServletResponse)response).sendError(UNAUTHORIZED);
		    return;
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}