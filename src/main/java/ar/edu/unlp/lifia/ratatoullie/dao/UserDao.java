package ar.edu.unlp.lifia.ratatoullie.dao;

import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.User;

public interface UserDao extends GenericDao<User> {
	
	public User get(String name) throws EntityNotExistsException;

}
