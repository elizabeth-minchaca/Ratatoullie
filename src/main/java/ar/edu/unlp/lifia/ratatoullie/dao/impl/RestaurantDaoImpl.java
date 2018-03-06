package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.RestaurantDao;
import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;

@Repository
public class RestaurantDaoImpl extends GenericDaoImpl<Restaurant> implements RestaurantDao {

	public RestaurantDaoImpl() {
		super(Restaurant.class);
		
	}
	@Override
	public Restaurant get(String name) throws EntityNotExistsException{
		Restaurant object = (Restaurant) getSession().createQuery("from Restaurant where name = :name").setParameter("name", name).uniqueResult();
		if (object == null) {
			throw new EntityNotExistsException(name + " not exists");
		}
		return object;
	}
	@Override
	public List<Restaurant> list(String name) {
		Query<Restaurant> query = getSession().createQuery("from Restaurant where name LIKE :name",getPersistentClass());
		query.setParameter("name", "%"+name+"%");
		return query.list();
	}	
}
