package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * {@link Ranking} Gourmet
 * @author NATA
 *
 */

@Entity
@DiscriminatorValue("GOURMET")
public class Gourmet extends Ranking {
	
	@Transient
	public static final String name= "GOURMET";
	
	@Override
	public void update(User user) {
		if(user.myCommentsSize()<=40){
			user.setRanking(new Commensal());
			user.updateRanking();
		}
	}
	@Override
	public String getName(){
		return name;
	}
}