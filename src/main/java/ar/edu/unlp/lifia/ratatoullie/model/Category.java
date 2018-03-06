package ar.edu.unlp.lifia.ratatoullie.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
/**
 * Category of a {@link Restaurant}
 * @author NATA
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category_type")
@Table(name = "category")
public abstract class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;	

	@ElementCollection(targetClass = Benefits.class,fetch= FetchType.EAGER)
	private List<Benefits> benefits= new ArrayList<Benefits>();
	/**
	 * Updates the {@link Category} of a {@link Restaurant} according to a certain criterion.
	 * @param restaurant
	 */
	public abstract void update(Restaurant restaurant);
	
	public List<Benefits> getBenefices() {
		return benefits;
	}
	public void setBenefices(List<Benefits> benefits) {
		this.benefits = benefits;
	}

	public void addBenefice(Benefits benefits){
		if(!getBenefices().contains(benefits)){
			getBenefices().add(benefits);
		}
		
	}
}