package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlp.lifia.ratatoullie.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T>{
	private Class<T> persistentClass;
	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl(Class<T> c){
		persistentClass = c;
	}
	public Class<T> getPersistentClass(){
		return persistentClass;
	}
	public void setPersistentClass(Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return getSessionFactory().getCurrentSession();
	}
	@Override
	public void save(T entity){
		getSession().save(entity);				
	}
	@Override
	public T get(long idEntity){
		return (T)getSession().get(getPersistentClass(), idEntity);
	}
	@Override
	public void update(T entity){
		getSession().update(entity);
	}
	@Override
	public void delete(T entity){
		getSession().delete(entity);
	}
	@Override
	public void delete(long idEntity){		
		getSession().delete(get(idEntity));		
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(){
		return getSession().createQuery("from "+getPersistentClass().getSimpleName()).list();		
	}	
	@Override
	public Blob createBlob(MultipartFile image) throws IOException {
		Blob  blob = null;

			blob = Hibernate.getLobCreator(getSession()).createBlob(image.getInputStream(), image.getSize());
		
		return blob;
	}

}
