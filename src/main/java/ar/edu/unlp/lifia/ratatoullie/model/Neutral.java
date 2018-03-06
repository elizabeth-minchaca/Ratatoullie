package ar.edu.unlp.lifia.ratatoullie.model;

import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * This {@link Category} belongs to a {@link Restaurant} that has a coefficient between -10 and 10 (Positive votes add up and negative ones subtract points)
 * @author NATA
 *
 */
@Entity
@DiscriminatorValue("NEUTRAL")
public class Neutral extends Category {

	@Override
	public void update(Restaurant restaurant) {
		int value=0;
		ArrayList<Comment> comments= (ArrayList<Comment>) restaurant.getAllComments();
		for (Comment comment : comments) {
			switch (comment.getVote()) {
			case NEGATIVE: value--;
				break;
			case POSITIVE:value++;				
				break;
			default:
				break;
			}
		}
		if(value>10){
			restaurant.setCategory(new Popular());
			restaurant.updateCategory();
		}else {
			if (value<-10) {
				restaurant.setCategory(new NotPopular());
				restaurant.updateCategory();
			}
		}
	}
	public Neutral() {
	}
}