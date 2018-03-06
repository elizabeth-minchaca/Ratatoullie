package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.RatatoullieDao;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;
@Repository
public class RatatoullieDaoImpl extends GenericDaoImpl<Ratatoullie> implements RatatoullieDao {

	public RatatoullieDaoImpl() {
		super(Ratatoullie.class);		
	}
}
