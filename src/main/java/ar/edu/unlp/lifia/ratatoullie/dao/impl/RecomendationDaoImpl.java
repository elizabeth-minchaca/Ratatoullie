package ar.edu.unlp.lifia.ratatoullie.dao.impl;


import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.RecommendationDao;
import ar.edu.unlp.lifia.ratatoullie.model.Recommendation;
@Repository
public class RecomendationDaoImpl extends GenericDaoImpl<Recommendation> implements RecommendationDao {

	public RecomendationDaoImpl() {
		super(Recommendation.class);		
	}

}
