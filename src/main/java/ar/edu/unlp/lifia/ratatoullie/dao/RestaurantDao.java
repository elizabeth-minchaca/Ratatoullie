package ar.edu.unlp.lifia.ratatoullie.dao;

import java.util.List;

import ar.edu.unlp.lifia.ratatoullie.exception.EntityNotExistsException;
import ar.edu.unlp.lifia.ratatoullie.model.Restaurant;

public interface RestaurantDao extends GenericDao<Restaurant> {
	
	public Restaurant get(String name) throws EntityNotExistsException;

	public List<Restaurant> list(String name);

}
