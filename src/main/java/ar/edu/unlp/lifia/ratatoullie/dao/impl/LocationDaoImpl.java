package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.LocationDao;
import ar.edu.unlp.lifia.ratatoullie.model.Location;

@Repository
public class LocationDaoImpl extends GenericDaoImpl<Location> implements LocationDao {

	public LocationDaoImpl() {
		super(Location.class);		
	}
}
