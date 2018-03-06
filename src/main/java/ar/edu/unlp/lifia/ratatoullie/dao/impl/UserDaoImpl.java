package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.UserDao;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.User;
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
		
	}
	@Override
	public User get(String name) throws EntityNotExistsException{
		User object = (User) getSession().createQuery("from User where name = :name").setParameter("name", name).uniqueResult();
		if (object == null) {
			throw new EntityNotExistsException(name + " not exists");
		}
		return object;
	}	
}
