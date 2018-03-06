package ar.edu.unlp.lifia.ratatoullie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlp.lifia.ratatoullie.dao.RatatoullieDao;
import ar.edu.unlp.lifia.ratatoullie.model.Ratatoullie;


@Service
public class InitContexService {
	@Autowired
	private RatatoullieDao ratatoullieDao;
	public RatatoullieDao getRatatoullieDao() {
		return ratatoullieDao;
	}
	public void setRatatoullieDao(RatatoullieDao ratatoullieDao) {
		this.ratatoullieDao = ratatoullieDao;
	}	
	public void initRaratoullie(){
		Ratatoullie ratatoullie=getRatatoullieDao().get(1);
		if(ratatoullie==null){
			ratatoullie= Ratatoullie.getInstance();
			getRatatoullieDao().save(ratatoullie);
		}
		Ratatoullie.setRatatoullie(ratatoullie);
	}

}
