package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;


/**
 * {@link Ranking} Visitor
 * @author NATA
 *
 */

@Entity
@DiscriminatorValue("VISITOR")
public class Visitor extends Ranking {
	
	@Transient
	public static final String name= "VISITOR";
	
	@Override
	public void update(User user) {
		if(user.myCommentsSize()>=20){
			user.setRanking(new Commensal());
			user.updateRanking();
		}
	}	
	@Override
	public String getName(){
		return name;
	}
}
