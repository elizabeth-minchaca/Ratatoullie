package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * {@link Ranking} Commensal
 * @author NATA
 *
 */

@Entity
@DiscriminatorValue("COMMENSAL")
public class Commensal extends Ranking {
	
	@Transient
	public static final String name= "COMMENSAL";
	
	@Override
	public void update(User user) {
		int size=user.myCommentsSize();
		if(size<20){
			user.setRanking(new Visitor());
			user.updateRanking();
		}else{
			if (size>40) {
				user.setRanking(new Gourmet());
				user.updateRanking();
			}			
		}
		
	}
	@Override
	public String getName(){
		return name;
	}
}