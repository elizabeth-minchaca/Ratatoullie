package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.MenuDao;
import ar.edu.unlp.lifia.ratatoullie.model.Menu;
@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu> implements MenuDao {

	public MenuDaoImpl() {
		super(Menu.class);		
	}
}
