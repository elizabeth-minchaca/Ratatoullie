package ar.edu.unlp.lifia.ratatoullie.dao;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface GenericDao<T> {
	
	public void save(T entity);
	
	public T get(long idEntity);
	
	public void delete(T entity);
	
	public List<T> list();

	public void delete(long idEntity);

	public void update(T entity);
	
	public Blob createBlob(MultipartFile image) throws IOException;


}
