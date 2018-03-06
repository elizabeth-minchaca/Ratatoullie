package ar.edu.unlp.lifia.ratatoullie.dao.impl;


import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.NotificationDao;
import ar.edu.unlp.lifia.ratatoullie.model.Notification;
@Repository
public class NotificationDaoImpl extends GenericDaoImpl<Notification> implements NotificationDao {

	public NotificationDaoImpl() {
		super(Notification.class);		
	}
}
